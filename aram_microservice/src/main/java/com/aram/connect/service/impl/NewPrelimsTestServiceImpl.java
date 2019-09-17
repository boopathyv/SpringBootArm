package com.aram.connect.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.aram.connect.persistence.NewOnlineTestService;
import com.aram.connect.persistence.dao.NewOnlineTest;
import com.aram.connect.persistence.dao.NewOnlineTestAnswerSummary;
import com.aram.connect.persistence.dao.NewOnlineTestQuestion;
import com.aram.connect.service.NewPrelimsTestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NewPrelimsTestServiceImpl implements NewPrelimsTestService {

    @Autowired
    public NewOnlineTestService newOnlineTestService;
    
    @Override 
    public void saveNewOnlineTest(NewOnlineTest newOnlineTest) {
        newOnlineTestService.saveNewOnlineTest(newOnlineTest);
    }

    @Override
    public void saveNewOnlineTestQuestion(NewOnlineTestQuestion newOnlineTestQuestion) {
        newOnlineTestService.saveNewOnlineTestQuestion(newOnlineTestQuestion);
    }

    @Override
    public List<NewOnlineTest> getNewPrelimsTest() {
        return newOnlineTestService.getNewPrelimsTest();
    }

    @Override
    public List<NewOnlineTestQuestion> getPrelimsTestQuestions(Long id) {
        return newOnlineTestService.getPrelimsTestQuestions(id);
    }

    @Override
    public NewOnlineTestAnswerSummary getPrelimsTestSummaryByStudentAndTest(Long studentId, Long testId) {
        return newOnlineTestService.getPrelimsTestSummaryByStudentAndTest(studentId, testId);
    }

    @Override
    public void savePrelimsTestAnswer(NewOnlineTestAnswerSummary newOnlineTestAnswerSummary) {
        newOnlineTestService.savePrelimsTestAnswer(newOnlineTestAnswerSummary);
    }

    @Override
    public NewOnlineTest findPrelimsTestById(Long id) {
        return newOnlineTestService.findPrelimsTestById(id);
    }
}