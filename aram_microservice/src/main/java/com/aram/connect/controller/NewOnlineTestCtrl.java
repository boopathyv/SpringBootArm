package com.aram.connect.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aram.connect.dataObjects.PrelimsTestUploadReq;
import com.aram.connect.persistence.dao.NewOnlineTest;
import com.aram.connect.persistence.dao.NewOnlineTestAnswerSummary;
import com.aram.connect.persistence.dao.NewOnlineTestQuestion;
import com.aram.connect.persistence.dao.PrelimsAnswer;
import com.aram.connect.persistence.dao.PrelimsTestAnswer;
import com.aram.connect.service.NewPrelimsTestService;
import com.aram.connect.service.UserService;
import com.aram.connect.util.AramUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("")
public class NewOnlineTestCtrl {

    @Autowired
    public NewPrelimsTestService newOnlineTestService;

    @Autowired
    public UserService userDetailService;

    @CrossOrigin
    @PostMapping("/onlineTest/upload")
    public ResponseEntity<Map> uploadOnlineTest(@RequestParam("file") MultipartFile mFile,
            @RequestParam("date") String date, @RequestParam("testCategory") String testCategory, 
            @RequestParam("isOnline") Boolean isOnline, @RequestParam("testTime") Long testTime) {
        Map<String, String> response = new HashMap<String, String>();
        response.put("message", "Test successfully uploaded");

        NewOnlineTest prelimsTest = new NewOnlineTest();
        prelimsTest.setTestCategory(testCategory);
        prelimsTest.setTestDate(AramUtil.stringToDate_YYYY_MM_DD(date));
        prelimsTest.setIsOnline(isOnline);
        prelimsTest.setCreatedDate(new Date());
        prelimsTest.setTestTime(testTime);
        newOnlineTestService.saveNewOnlineTest(prelimsTest);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(mFile.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int i = 0; i < worksheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = worksheet.getRow(i);
                System.out.println("---------");

                if (row.getRowNum() == 0) {
                    continue;
                }

                NewOnlineTestQuestion prelimsTestQuestion = new NewOnlineTestQuestion();

                prelimsTestQuestion.setNewOnlineTest(prelimsTest);
                prelimsTestQuestion.setSeqNo((long) row.getCell(0).getNumericCellValue());
                prelimsTestQuestion.setQuestion(row.getCell(1).getStringCellValue());
                prelimsTestQuestion.setOptionA(row.getCell(2).getStringCellValue());
                prelimsTestQuestion.setOptionB(row.getCell(3).getStringCellValue());
                prelimsTestQuestion.setOptionC(row.getCell(4).getStringCellValue());
                prelimsTestQuestion.setOptionD(row.getCell(5).getStringCellValue());
                prelimsTestQuestion.setAnswer(row.getCell(6).getStringCellValue());
                prelimsTestQuestion.setMark(row.getCell(7).getNumericCellValue());
                prelimsTestQuestion.setNegativeMark(row.getCell(8).getNumericCellValue());
                prelimsTestQuestion.setExplanation(row.getCell(9).getStringCellValue());
                
                if(row.getCell(10) != null && row.getCell(10).getCellType() != Cell.CELL_TYPE_BLANK) {
                    prelimsTestQuestion.setImage(row.getCell(10).getStringCellValue());
                }
                if(row.getCell(11) != null && row.getCell(11).getCellType() != Cell.CELL_TYPE_BLANK) {
                    prelimsTestQuestion.setExplanationImage(row.getCell(11).getStringCellValue());
                }

                newOnlineTestService.saveNewOnlineTestQuestion(prelimsTestQuestion);
                // }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @GetMapping("/prelimsTest/list")
    public ResponseEntity<List<NewOnlineTest>> list() {
        return ResponseEntity.ok(newOnlineTestService.getNewPrelimsTest());
    }

    @CrossOrigin
    @GetMapping("/prelimsTest/{id}/questions")
    public ResponseEntity<NewOnlineTestAnswerSummary> getPrelimsTest(@PathVariable String id, @RequestParam("studentId") String studentId) {
        
        Long studentIdInLng = Long.parseLong(studentId);
        Long prelimsTestId = Long.parseLong(id);
        NewOnlineTestAnswerSummary prelimsTestSummary = newOnlineTestService.getPrelimsTestSummaryByStudentAndTest(studentIdInLng, prelimsTestId);
        if(prelimsTestSummary != null) {
            return ResponseEntity.ok(prelimsTestSummary);
        } 

        prelimsTestSummary = new NewOnlineTestAnswerSummary();
        prelimsTestSummary.timeStamp = (long) 0;
        prelimsTestSummary.newOnlineTest = newOnlineTestService.findPrelimsTestById(prelimsTestId);
        prelimsTestSummary.student = userDetailService.getStudent(Math.toIntExact(studentIdInLng));
        prelimsTestSummary.isCompleted = false;
        prelimsTestSummary.createdDate = new Date();

        List<NewOnlineTestQuestion> questions = newOnlineTestService.getPrelimsTestQuestions(Long.parseLong(id));
        PrelimsTestAnswer answer = new PrelimsTestAnswer();
        PrelimsAnswer answers[] = new PrelimsAnswer[questions.size()];
        
        for(int i = 0; i < questions.size(); i++) {
            NewOnlineTestQuestion question = questions.get(i);
            PrelimsAnswer prelimsAnswer = new PrelimsAnswer();
            prelimsAnswer.newOnlineTestQuestion = question;
            prelimsAnswer.isCorrect = false;
            answers[i] = prelimsAnswer;
        }
        answer.answer = answers;
        prelimsTestSummary.answers = answer;

        newOnlineTestService.savePrelimsTestAnswer(prelimsTestSummary);
        return ResponseEntity.ok(prelimsTestSummary);
    }

    @CrossOrigin
    @PostMapping("/prelimsTest/uploadAnswer")
    public ResponseEntity<Map> uploadAnswers(@RequestBody PrelimsTestUploadReq req) {

        Map<String, String> response = new HashMap<>();

        NewOnlineTestAnswerSummary prelimsTestSummary = newOnlineTestService.getPrelimsTestSummaryByStudentAndTest(req.studentId, req.prelimsTestId);

        if(prelimsTestSummary == null) {
            prelimsTestSummary = new NewOnlineTestAnswerSummary();
            prelimsTestSummary.timeStamp = req.timeStamp;
            prelimsTestSummary.newOnlineTest = newOnlineTestService.findPrelimsTestById(req.prelimsTestId);
            prelimsTestSummary.student = userDetailService.getStudent(Math.toIntExact(req.studentId));
            prelimsTestSummary.isCompleted = false;
            prelimsTestSummary.createdDate = new Date();
        } else {

        }






        return ResponseEntity.ok(response);
    }
}