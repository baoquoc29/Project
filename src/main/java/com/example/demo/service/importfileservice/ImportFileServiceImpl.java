package com.example.demo.service.importfileservice;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportFileServiceImpl implements ImportFileService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    ListeningRepository listeningRepository;

    @Autowired
    ListeningQuestionRepository listeningQuestionRepository;

    @Autowired
    MultipleChoiceRepository multipleChoiceRepository;

    @Autowired
    MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;

    @Autowired
    ReadingQuestionRepository readingQuestionRepository;


    @Autowired
    ReadingRepository readingRepository;

    @Transactional
    public void readExcelFile(String filePath, QuizDTO quizDTO) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        quizDTO.setIdQuiz(null);
        Sheet sheet = workbook.getSheetAt(0);
        Quiz quiz = modelMapper.map(quizDTO, Quiz.class);
        Quiz savedQuiz = quizRepository.save(quiz);
        Long idQuiz = savedQuiz.getIdQuiz();
        System.out.println("ID của Quiz đã lưu: " + savedQuiz.getIdQuiz());
        if (idQuiz == null) {
            throw new IllegalArgumentException("Failed to save quiz, idQuiz is null.");
        }
        Long currentContentId = null;
        int questionCountForCurrentContent = 0;

        for (Row row : sheet) {
            if (row.getRowNum() < 31) {
                ListeningAnswersDTO answer = new ListeningAnswersDTO();
                populateAnswerFromRow(answer, row);

                ListeningContentDTO listeningContentDTO = new ListeningContentDTO(
                        answer.getImage(), answer.getAudio(), answer.getPartListening()
                );
                ListeningQuestions listeningQuestions = modelMapper.map(listeningContentDTO, ListeningQuestions.class);
                ListeningQuestions savedListeningQuestions = listeningQuestionRepository.save(listeningQuestions);

                currentContentId = savedListeningQuestions.getIdContent();
                answer.setIdContent(currentContentId);
                answer.setIdQuiz(idQuiz);
                ListeningAnswers listeningAnswers = modelMapper.map(answer, ListeningAnswers.class);
                listeningRepository.save(listeningAnswers);
                if (answer.getIdQuiz() == null) {
                    throw new IllegalArgumentException("QuizDTO cannot be null");
                }
                System.out.println("ID" + listeningAnswers.getIdQuiz());
            } else if(row.getRowNum() > 30 && row.getRowNum() < 100){
            
                ListeningAnswersDTO answer = new ListeningAnswersDTO();
                populateAnswerFromRow(answer, row);

                if (questionCountForCurrentContent % 3 == 0) {

                    ListeningContentDTO listeningContentDTO = new ListeningContentDTO(
                            answer.getImage(), answer.getAudio(), answer.getPartListening()
                    );
                    ListeningQuestions listeningQuestions = modelMapper.map(listeningContentDTO, ListeningQuestions.class);
                    ListeningQuestions savedListeningQuestions = listeningQuestionRepository.save(listeningQuestions);

                    currentContentId = savedListeningQuestions.getIdContent();
                }

                answer.setIdContent(currentContentId);
                answer.setIdQuiz(idQuiz);

                ListeningAnswers listeningAnswers = modelMapper.map(answer, ListeningAnswers.class);
                listeningRepository.save(listeningAnswers);

                questionCountForCurrentContent++; // Tăng đếm số câu hỏi cho idContent hiện tại

                // Nếu đã đủ 3 câu hỏi cho một idContent thì reset lại
                if (questionCountForCurrentContent == 3) {
                    questionCountForCurrentContent = 0; // Reset sau khi đủ 3 câu
                }
            }
            else if(row.getRowNum() > 99 && row.getRowNum() < 130){
                MultipleChoiceAnswerDTO multipleChoiceAnswerDTO = new MultipleChoiceAnswerDTO();
                populateAnswerMultipleFromRow(multipleChoiceAnswerDTO,row);
                MultipleChoiceQuestionDTO questionDTO = new MultipleChoiceQuestionDTO(multipleChoiceAnswerDTO.getQuestion(),multipleChoiceAnswerDTO.getPart());
                MultipleChoiceQuestions questions = modelMapper.map(questionDTO,MultipleChoiceQuestions.class);
                MultipleChoiceQuestions multipleChoiceQuestions = multipleChoiceQuestionRepository.save(questions);
                currentContentId = multipleChoiceQuestions.getIdMultipleQuestion();

                multipleChoiceAnswerDTO.setIdQuestion(currentContentId);
                multipleChoiceAnswerDTO.setIdQuiz(idQuiz);

                MultipleChoiceAnswers multipleChoiceAnswers = modelMapper.map(multipleChoiceAnswerDTO,MultipleChoiceAnswers.class);

                multipleChoiceRepository.save(multipleChoiceAnswers);
            }
            else if(row.getRowNum() > 129 && row.getRowNum() < 146){
                ReadingAnswerDTO readingAnswerDTO = new ReadingAnswerDTO();
                populateAnswerReadingFromRow(readingAnswerDTO,row);
                if(questionCountForCurrentContent % 4 == 0){
                    ReadingContentDTO readingContentDTO = new ReadingContentDTO(readingAnswerDTO.getImage(), readingAnswerDTO.getPart());
                    ReadingQuestions readingQuestions = modelMapper.map(readingContentDTO, ReadingQuestions.class);
                    ReadingQuestions save = readingQuestionRepository.save(readingQuestions);

                    currentContentId = save.getIdReading();
                }
                readingAnswerDTO.setIdReadingContent(currentContentId);
                readingAnswerDTO.setIdQuiz(idQuiz);
                ReadingAnswers readingAnswers = modelMapper.map(readingAnswerDTO, ReadingAnswers.class);
                readingRepository.save(readingAnswers);

                questionCountForCurrentContent++;

                if (questionCountForCurrentContent == 4) {
                    questionCountForCurrentContent = 0;
                }

            }
            else if(row.getRowNum() > 145 && row.getRowNum() < 154 ){
                ReadingAnswerDTO readingAnswerDTO = new ReadingAnswerDTO();
                populateAnswerReadingFromRow(readingAnswerDTO,row);
                if(questionCountForCurrentContent % 2 == 0){
                    ReadingContentDTO readingContentDTO = new ReadingContentDTO(readingAnswerDTO.getImage(), readingAnswerDTO.getPart());
                    ReadingQuestions readingQuestions = modelMapper.map(readingContentDTO, ReadingQuestions.class);
                    ReadingQuestions save = readingQuestionRepository.save(readingQuestions);

                    currentContentId = save.getIdReading();
                }
                readingAnswerDTO.setIdReadingContent(currentContentId);
                readingAnswerDTO.setIdQuiz(idQuiz);
                ReadingAnswers readingAnswers = modelMapper.map(readingAnswerDTO, ReadingAnswers.class);
                readingRepository.save(readingAnswers);

                questionCountForCurrentContent++;

                if (questionCountForCurrentContent == 2) {
                    questionCountForCurrentContent = 0;
                }
            }
            else if(row.getRowNum() > 153 && row.getRowNum() < 163 ){
                ReadingAnswerDTO readingAnswerDTO = new ReadingAnswerDTO();
                populateAnswerReadingFromRow(readingAnswerDTO,row);
                if(questionCountForCurrentContent % 3 == 0){
                    ReadingContentDTO readingContentDTO = new ReadingContentDTO(readingAnswerDTO.getImage(), readingAnswerDTO.getPart());
                    ReadingQuestions readingQuestions = modelMapper.map(readingContentDTO, ReadingQuestions.class);
                    ReadingQuestions save = readingQuestionRepository.save(readingQuestions);

                    currentContentId = save.getIdReading();
                }
                readingAnswerDTO.setIdReadingContent(currentContentId);
                readingAnswerDTO.setIdQuiz(idQuiz);
                ReadingAnswers readingAnswers = modelMapper.map(readingAnswerDTO, ReadingAnswers.class);
                readingRepository.save(readingAnswers);

                questionCountForCurrentContent++;

                if (questionCountForCurrentContent == 3) {
                    questionCountForCurrentContent = 0;
                }
            }
            else if(row.getRowNum() > 162 && row.getRowNum() < 175 ){
                ReadingAnswerDTO readingAnswerDTO = new ReadingAnswerDTO();
                populateAnswerReadingFromRow(readingAnswerDTO,row);
                if(questionCountForCurrentContent % 4 == 0){
                    ReadingContentDTO readingContentDTO = new ReadingContentDTO(readingAnswerDTO.getImage(), readingAnswerDTO.getPart());
                    ReadingQuestions readingQuestions = modelMapper.map(readingContentDTO, ReadingQuestions.class);
                    ReadingQuestions save = readingQuestionRepository.save(readingQuestions);

                    currentContentId = save.getIdReading();
                }
                readingAnswerDTO.setIdReadingContent(currentContentId);
                readingAnswerDTO.setIdQuiz(idQuiz);
                ReadingAnswers readingAnswers = modelMapper.map(readingAnswerDTO, ReadingAnswers.class);
                readingRepository.save(readingAnswers);

                questionCountForCurrentContent++;

                if (questionCountForCurrentContent == 4) {
                    questionCountForCurrentContent = 0;
                }
            }
            else if(row.getRowNum() > 174 && row.getRowNum() < 200){
                ReadingAnswerDTO readingAnswerDTO = new ReadingAnswerDTO();
                populateAnswerReadingFromRow(readingAnswerDTO,row);
                if(questionCountForCurrentContent % 5 == 0){
                    ReadingContentDTO readingContentDTO = new ReadingContentDTO(readingAnswerDTO.getImage(), readingAnswerDTO.getPart());
                    ReadingQuestions readingQuestions = modelMapper.map(readingContentDTO, ReadingQuestions.class);
                    ReadingQuestions save = readingQuestionRepository.save(readingQuestions);

                    currentContentId = save.getIdReading();
                }
                readingAnswerDTO.setIdReadingContent(currentContentId);
                readingAnswerDTO.setIdQuiz(idQuiz);
                ReadingAnswers readingAnswers = modelMapper.map(readingAnswerDTO, ReadingAnswers.class);
                readingRepository.save(readingAnswers);

                questionCountForCurrentContent++;

                if (questionCountForCurrentContent == 5) {
                    questionCountForCurrentContent = 0;
                }
            }
        }

        workbook.close();
        file.close();
    }

    private void populateAnswerFromRow(ListeningAnswersDTO answer, Row row) {
        answer.setOptionA(getCellValue(row, 0).trim());
        answer.setOptionB(getCellValue(row, 1).trim());
        answer.setOptionC(getCellValue(row, 2).trim());
        answer.setOptionD(getCellValue(row, 3).trim());
        answer.setAnswerCorrect(getCellValue(row, 4).trim());
        answer.setQuestion(getCellValue(row, 5).trim());
        answer.setImage(getCellValue(row, 6).trim());
        answer.setAudio(getCellValue(row, 7).trim());
        answer.setPartListening(getCellValue(row, 8).trim());
        answer.setDescriptionAnswer(getCellValue(row, 9).trim());
    }
    private void populateAnswerReadingFromRow(ReadingAnswerDTO answer, Row row) {
        answer.setOptionA(getCellValue(row, 0).trim());
        answer.setOptionB(getCellValue(row, 1).trim());
        answer.setOptionC(getCellValue(row, 2).trim());
        answer.setOptionD(getCellValue(row, 3).trim());
        answer.setAnswerCorrect(getCellValue(row, 4).trim());
        answer.setQuestion(getCellValue(row, 5).trim());
        answer.setImage(getCellValue(row, 6).trim());
        answer.setPart(getCellValue(row, 8).trim());
        answer.setAnswerDescription(getCellValue(row, 9).trim());
    }
    private void populateAnswerMultipleFromRow(MultipleChoiceAnswerDTO answer, Row row) {
        answer.setOptionA(getCellValue(row, 0).trim());
        answer.setOptionB(getCellValue(row, 1).trim());
        answer.setOptionC(getCellValue(row, 2).trim());
        answer.setOptionD(getCellValue(row, 3).trim());
        answer.setAnswerCorrect(getCellValue(row, 4).trim());
        answer.setQuestion(getCellValue(row, 5).trim());
        answer.setPart(getCellValue(row, 8).trim());
        answer.setAnswerDescription(getCellValue(row, 9).trim());
    }

    // Helper function để lấy giá trị của ô an toàn
    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }

}

