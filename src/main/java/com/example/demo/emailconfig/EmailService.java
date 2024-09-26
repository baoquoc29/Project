package com.example.demo.emailconfig;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.accountservice.AccountService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private RandomCodeGenerator codeGenerator = new RandomCodeGenerator();

    public void sendEmail(Email email) {
        try {
            MimeMessage mimeMessage = createMimeMessage(email, getEmailBody(email));
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Log the error or throw a custom exception
        }
    }

    public void sendForgotEmail(Email email) {
        if (!isValidEmail(email.getEmail())) {
            throw new AppException(ErrorCode.INVALID_EMAIL);
        }
        try {
            MimeMessage mimeMessage = createMimeMessage(email, getForgotEmailBody(email));
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new AppException(ErrorCode.INVALID_EMAIL);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private MimeMessage createMimeMessage(Email email, String emailBody) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("quocta.gov@gmail.com");
        helper.setTo(email.getEmail().trim());
        helper.setSubject(email.getSubject());
        helper.setText(emailBody, true);
        return mimeMessage;
    }

    private String getForgotEmailBody(Email email) {
        UserDTO userDTO = accountService.getUserByEmail(email.getEmail().trim());
        String password = userDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <style>" +
                "        .email-container {" +
                "            font-family: Arial, sans-serif;" +
                "            border: 1px solid #dddddd;" +
                "            padding: 20px;" +
                "            max-width: 600px;" +
                "            margin: auto;" +
                "            background-color: #f9f9f9;" +
                "        }" +
                "        .header {" +
                "            background-color: #FF6347;" +
                "            color: white;" +
                "            padding: 10px;" +
                "            text-align: center;" +
                "        }" +
                "        .content {" +
                "            margin: 20px 0;" +
                "        }" +
                "        .content h2 {" +
                "            color: #333;" +
                "        }" +
                "        .content p {" +
                "            color: #666;" +
                "        }" +
                "        .footer {" +
                "            text-align: center;" +
                "            font-size: 12px;" +
                "            color: #999;" +
                "            padding-top: 10px;" +
                "            border-top: 1px solid #dddddd;" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "<div class=\"email-container\">" +
                "    <div class=\"header\">" +
                "        <h1>Yêu cầu đặt lại mật khẩu</h1>" +
                "    </div>" +
                "    <div class=\"content\">" +
                "        <h2>Đặt lại mật khẩu của bạn</h2>" +
                "        <p>Xin chào " + userDTO.getName() + ",</p>" +
                "        <p>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn. Nếu bạn không yêu cầu đặt lại mật khẩu, xin hãy bỏ qua email này.</p>" +
                "        <p>Mã đặt lại mật khẩu của bạn: " + codeGenerator.toString() + "</p>" +
                "    </div>" +
                "    <div class=\"footer\">" +
                "        <p>&copy; 2024 EnglishCompany. All rights reserved.</p>" +
                "    </div>" +
                "</div>" +
                "</body>" +
                "</html>";
    }

    private String getEmailBody(Email email) {
        UserDTO userDTO = accountService.getUserByEmail(email.getEmail().trim());
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);

        String confirmationCode = codeGenerator.generateRegistrationCode(); // Giả sử đây là mã xác nhận

        return String.format(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "    <style>" +
                        "        .email-container {" +
                        "            font-family: Arial, sans-serif;" +
                        "            border: 1px solid #dddddd;" +
                        "            padding: 20px;" +
                        "            max-width: 600px;" +
                        "            margin: auto;" +
                        "            background-color: #f9f9f9;" +
                        "        }" +
                        "        .header {" +
                        "            background-color: #87CEEB;" +
                        "            color: white;" +
                        "            padding: 10px;" +
                        "            text-align: center;" +
                        "        }" +
                        "        .content {" +
                        "            margin: 20px 0;" +
                        "        }" +
                        "        .content h2 {" +
                        "            color: #333;" +
                        "            font-size: 20px;" + // Tăng kích thước font chữ cho tiêu đề
                        "        }" +
                        "        .content p {" +
                        "            color: #666;" +
                        "            font-size: 16px;" + // Tăng kích thước font chữ cho đoạn văn
                        "        }" +
                        "        .footer {" +
                        "            text-align: center;" +
                        "            font-size: 12px;" +
                        "            color: #999;" +
                        "            padding-top: 10px;" +
                        "            border-top: 1px solid #dddddd;" +
                        "        }" +
                        "    </style>" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"email-container\">" +
                        "    <div class=\"header\">" +
                        "        <h1>Thông tin tài khoản của bạn</h1>" +
                        "    </div>" +
                        "    <div class=\"content\">" +
                        "        <h2>Thông tin chi tiết</h2>" +
                        "        <p><strong>Mã tài khoản:</strong> %s</p>" +
                        "        <p><strong>Mã thành viên:</strong> %s</p>" +
                        "        <p><strong>Tên tài khoản:</strong> %s</p>" +
                        "        <p><strong>Email:</strong> %s</p>" +
                        "        <p><strong>Họ và tên:</strong> %s</p>" +
                        "        <p><strong>Tuổi:</strong> %s</p>" +
                        "        <p><strong>Ngày đăng kí:</strong> %s</p>" +
                        "        <p><strong>Mã xác nhận tài khoản:</strong> <strong style=\"color: #FF4500;\">%s</strong></p>" + // Hiển thị mã xác nhận
                        "    </div>" +
                        "    <div class=\"footer\">" +
                        "        <p>&copy; 2024 WorkLishCompany. All rights reserved.</p>" +
                        "    </div>" +
                        "</div>" +
                        "</body>" +
                        "</html>",
                userDTO.getIduser(),
                userDTO.getIdcustomer(),
                userDTO.getUsername(),
                email.getEmail().trim(),
                userDTO.getName(),
                userDTO.getAge(),
                formattedDateTime,
                confirmationCode // Chèn mã xác nhận vào đây
        );
    }
}