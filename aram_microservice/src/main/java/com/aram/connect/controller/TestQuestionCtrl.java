package com.aram.connect.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.protocol.json.SdkJsonGenerator.JsonGenerationException;
import com.aram.connect.AramConstants;
import com.aram.connect.Exception.FileStorageException;
import com.aram.connect.persistence.UserDetailsService;
import com.aram.connect.persistence.dao.Student;
import com.aram.connect.persistence.dao.StudentAnswer;
import com.aram.connect.persistence.dao.TestQuestion;
import com.aram.connect.persistence.dao.UserLogin;
import com.aram.connect.persistence.dao.UserRole;
import com.aram.connect.service.TestQuestionService;
import com.aram.connect.service.UserService;
import com.aram.connect.util.AramUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestQuestionCtrl {

    @Autowired
    private TestQuestionService testQuestionService;

    @Autowired
    UserDetailsService userServiceDetails;

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/uploadTestQuestion")
    public ResponseEntity<Map> uploadTestQuestion(@RequestParam("file") MultipartFile mfile,
            @RequestParam("testQuestion") String testQuestionStr) {

        Map<String, String> response = new HashMap<String, String>();

        try {

            if (null == mfile) {
                response.put("error", "Please attach file");
                ResponseEntity.status(400);
                return ResponseEntity.badRequest().body(response);
            }

            String originalFileName = StringUtils.cleanPath(mfile.getOriginalFilename());
            if (!(originalFileName.endsWith(".pdf"))) {
                response.put("error", "Please attach pdf file");
                ResponseEntity.status(400);
                return ResponseEntity.badRequest().body(response);
            }

            // JSONObject.stringToValue("arg0");

            ObjectMapper om = new ObjectMapper();
            try {
                TestQuestion testQuestion = om.readValue(testQuestionStr, TestQuestion.class);
                Date testDate = AramUtil.stringToDate(testQuestion.testDateStr);
                testQuestion.setTestDate(testDate);

                String filePath = testQuestionService.saveTestQuestionFile(mfile, testDate, "question");
                testQuestion.setFilePath(filePath);

                UserLogin userLogin = userServiceDetails.getUserLoginDetails(testQuestion.testCreatedById);

                UserRole userRole = userServiceDetails.getUserRole(userLogin.getRoleId());
                if (!userRole.getRole().equalsIgnoreCase(AramConstants.ADMIN)) {
                    response.put("error", "you dont have privilge to this feature");
                    ResponseEntity.status(401);
                    return ResponseEntity.badRequest().body(response);
                }
                testQuestion.setTestCreatedBy(userLogin);
                testQuestion.setHasAnswer(false);
                try {
                    testQuestionService.saveTestQuestion(testQuestion);
                } catch (Exception e) {
                    System.out.println("Error type = " + e.getClass().getName());
                    // if(e instanceof SQLIntegrityConstraintViolationException) {
                    response.put("error", e.toString());
                    ResponseEntity.status(400);
                    return ResponseEntity.badRequest().body(response);
                    // }
                }
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileStorageException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.put("message", "Test question uploaded successfully");
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @PutMapping("/uploadAnswer/{test_question_id}")
    public ResponseEntity<Map> uploadAnswer(@RequestParam("file") MultipartFile mfile,
            @PathVariable String test_question_id) {

        Map<String, String> response = new HashMap<String, String>();

        if (null == mfile) {
            response.put("error", "Please attach file");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        String originalFileName = StringUtils.cleanPath(mfile.getOriginalFilename());
        if (!(originalFileName.endsWith(".pdf"))) {
            response.put("error", "Please attach pdf file");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        TestQuestion testQuestion = testQuestionService.getTestQuestion(Long.parseLong(test_question_id));
        if (testQuestion == null) {
            response.put("message", "Test question not found");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        String answerFilePath = testQuestionService.saveTestQuestionFile(mfile, testQuestion.getTestDate(), "answer");
        testQuestion.setAnswerFilePath(answerFilePath);

        testQuestion.setHasAnswer(true);
        Date today = new Date();
        testQuestion.setAnswerUploadedOn(AramUtil.stringToDate(AramUtil.dateToString(today, "dd-MM-yyyy")));
        try {
            testQuestionService.saveTestQuestion(testQuestion);
        } catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                response.put("error", e.toString());
                ResponseEntity.status(400);
                return ResponseEntity.badRequest().body(response);
            }
        }
        response.put("message", "Test question uploaded successfully");
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @PutMapping("/uploadDiscussion/{test_question_id}")
    public ResponseEntity<Map> uploadDiscussion(@RequestParam("file") MultipartFile mfile,
            @PathVariable String test_question_id) {

        Map<String, String> response = new HashMap<String, String>();

        if (null == mfile) {
            response.put("error", "Please attach file");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        // String originalFileName = StringUtils.cleanPath(mfile.getOriginalFilename());
        // if (!(originalFileName.endsWith(".pdf"))) {
        //     response.put("error", "Please attach pdf file");
        //     ResponseEntity.status(400);
        //     return ResponseEntity.badRequest().body(response);
        // }

        TestQuestion testQuestion = testQuestionService.getTestQuestion(Long.parseLong(test_question_id));
        if (testQuestion == null) {
            response.put("message", "Test question not found");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        String answerFilePath = testQuestionService.saveTestQuestionFile(mfile, testQuestion.getTestDate(), "discussion");
        testQuestion.setDiscussionFilePath(answerFilePath);

        try {
            testQuestionService.saveTestQuestion(testQuestion);
        } catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                response.put("error", e.toString());
                ResponseEntity.status(400);
                return ResponseEntity.badRequest().body(response);
            }
        }
        response.put("message", "Test question uploaded successfully");
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @PostMapping("/student/{student_id}/answer/{test_question_id}")
    public ResponseEntity<Map> uploadAnswerFromStudent(@RequestParam("file") MultipartFile mfile,
            @PathVariable String test_question_id, @PathVariable String student_id) {
        Map<String, String> response = new HashMap<String, String>();

        if (null == mfile) {
            response.put("error", "Please attach file");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        String originalFileName = StringUtils.cleanPath(mfile.getOriginalFilename());
        if (!((originalFileName.endsWith(".pdf")) || (originalFileName.endsWith(".png")) || (originalFileName.endsWith(".jpg")) || (originalFileName.endsWith(".jpeg")))) {
            response.put("error", "Please attach pdf/image file");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        Student student = userServiceDetails.getStudentDetails(Integer.parseInt(student_id));
        UserLogin userLogin = userServiceDetails.getUserLoginDetails(Integer.parseInt(student_id));
        if (student == null || userLogin == null) {
            response.put("error", "student is not valid");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        UserRole userRole = userServiceDetails.getUserRole(userLogin.getRoleId());
        if (!userRole.getRole().equalsIgnoreCase(AramConstants.STUDENT)) {
            response.put("error", "you dont have privilge to this feature");
            ResponseEntity.status(401);
            return ResponseEntity.badRequest().body(response);
        }

        TestQuestion testQuestion = testQuestionService.getTestQuestion(Long.parseLong(test_question_id));
        if (testQuestion == null) {
            response.put("message", "Test question not found");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        String answerFilePath = testQuestionService.saveTestQuestionFile(mfile, testQuestion.getTestDate(),
                ("answer/student/student_" + student.getStudentId()));
        testQuestion.setAnswerFilePath(answerFilePath);

        StudentAnswer sa = new StudentAnswer();
        sa.setStudentAnswerId(null);
        sa.setStudent(student);
        sa.setUser(userLogin);
        sa.setTestQuestion(testQuestion);
        sa.setFilePath(answerFilePath);
        sa.setIsCorrected(false);
        Date today = new Date();
        sa.setAnsweredOn(AramUtil.stringToDate(AramUtil.dateToString(today, "dd-MM-yyyy")));

        try {
            String message = "Hi, \n \t\t This is to notify you that, you have a response from an aspirant("+ sa.getStudent().getStudentName() + ") for the question(Seq No: "+ sa.getTestQuestion().getSequenceNo() + ") you have posted." ;
            userService.sendAnswerNotificationMail("aramiaschennai@gmail.com", "Aram IAS Academy - Student Answer", message);
            testQuestionService.saveStudentAnswer(sa);
        } catch (Exception e) {
            System.out.println("Error type = " + e.getClass().getName());
            // if(e instanceof SQLIntegrityConstraintViolationException) {
            response.put("error", e.toString());
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
            // }
        } catch (Throwable e) {
            System.out.println("Test question answer email notifiaction has error");
            e.printStackTrace();
        }

        response.put("message", "Answer uploaded successfully");

        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @PutMapping("/uploadFeedback/{id}")
    public ResponseEntity<Map> uploadFeedback(@RequestParam("file") MultipartFile mfile, @PathVariable String id,
            @RequestParam("feedback") String feedback) {
        Map<String, String> response = new HashMap<String, String>();

        if (null == mfile) {
            response.put("error", "Please attach file");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
        }

        StudentAnswer studentAnswer = testQuestionService.getStudentAnswer(Long.parseLong(id));
        if (studentAnswer == null) {
            response.put("error", "answer not found");
            ResponseEntity.status(400);
            return ResponseEntity.ok(response);

        }

        String feedbackFilePath = testQuestionService.saveTestQuestionFile(mfile, studentAnswer.getTestQuestion().getTestDate(),
                ("answer/student/student_" + studentAnswer.getStudent().getStudentId() + "/feedback"));
        studentAnswer.setFeedbackFilePath(feedbackFilePath);

        Date today = new Date();
        studentAnswer.setFeedbackOn(AramUtil.stringToDate(AramUtil.dateToString(today, "dd-MM-yyyy")));
        studentAnswer.setFeedback(feedback);
        studentAnswer.setIsCorrected(true);

        try {
            testQuestionService.saveStudentAnswer(studentAnswer);
        } catch (Exception e) {
            System.out.println("Error type = " + e.getClass().getName());
            // if(e instanceof SQLIntegrityConstraintViolationException) {
            response.put("error", e.toString());
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
            // }
        }
        response.put("message", "Feedback uploaded successfully");

        return ResponseEntity.ok(response);

    }

    @CrossOrigin
    @GetMapping("/testQuestions")
    public ResponseEntity<Map> getTestQuestions(@RequestParam("questionType") String questionType) {
        Map<String, List<TestQuestion>> response = new HashMap<String, List<TestQuestion>>();
        response.put("testQuestions", testQuestionService.getTestQuestions(questionType));
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @GetMapping("/testQuestions/{studentId}")
    public ResponseEntity<Map> getTestQuestionsForStudent(@PathVariable String studentId, @RequestParam("questionType") String questionType) {
        Map<String, List<Object>> response = new HashMap<String, List<Object>>();
        response.put("testQuestions", testQuestionService.getTestQuestionsForStudent(studentId, questionType));
        return ResponseEntity.ok(response);
    }

    
    @CrossOrigin
    @GetMapping("/testQuestion/{id}/answers")
    public ResponseEntity<Map> getAnswersForTestQuestion(@PathVariable String id) {
        Map<String, List<StudentAnswer>> response = new HashMap<String, List<StudentAnswer>>();
        response.put("studentAnswers", testQuestionService.getStudentAnswersForTestQuestion(Long.parseLong(id)));
        return ResponseEntity.ok(response);
    }
}