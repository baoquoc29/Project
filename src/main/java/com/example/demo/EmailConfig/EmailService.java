package com.example.demo.EmailConfig;

import com.example.demo.DTO.UserDTO;
import com.example.demo.DTO.UserResponDTO;
import com.example.demo.Service.AccountService.AccountService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AccountService accountService;

    public void sendEmail(Email email){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("quocta.gov@gmail.com");
            helper.setTo(email.getEmail());
            helper.setSubject(email.getSubject());
            helper.setText(getEmailBody(email), true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }

    private String getEmailBody(Email email) {
        UserDTO userDTO = accountService.getUserByEmail(email.getEmail());
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        String confirmationUrl = "http://localhost:9111/dev/api/v1/auth/comfirm/email/" + email.getEmail(); // Replace with your confirmation URL
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
                "            background-color: #87CEEB;" +  // Sky blue color
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
                "        .button-container {" +
                "            text-align: center;" +
                "            margin-top: 20px;" +
                "        }" +
                "        .button {" +
                "            background-color: #87CEEB;" + // Sky blue color
                "            color: white;" +
                "            padding: 10px 20px;" +
                "            text-align: center;" +
                "            text-decoration: none;" +
                "            display: inline-block;" +
                "            font-size: 16px;" +
                "            margin: 4px 2px;" +
                "            cursor: pointer;" +
                "            border: none;" +
                "            border-radius: 4px;" +
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
                "        <p><strong>Mã tài khoản:</strong> " + userDTO.getIduser() + "</p>" +
                "        <p><strong>Mã thành viên:</strong> " + userDTO.getIdcustomer() + "</p>" +
                "        <p><strong>Tên tài khoản:</strong> " + userDTO.getUsername() + "</p>" +
                "        <p><strong>Email:</strong> " + email.getEmail() + "</p>" +
                "        <p><strong>Họ và tên:</strong> " + userDTO.getName() + "</p>" +
                "        <p><strong>Tuổi:</strong> " + userDTO.getAge() + "</p>" +
                "        <p><strong>Ngày đăng kí:</strong> " + formattedDateTime + "</p>" +
                "        <div class=\"button-container\">" +
                "            <a href=\"" + confirmationUrl + "\" class=\"button\">Xác nhận tài khoản của bạn</a>" +
                "        </div>" +
                "    </div>" +
                "    <div class=\"footer\">" +
                "        <p>&copy; 2024 EnglishCompany. All rights reserved.</p>" +
                "    </div>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
