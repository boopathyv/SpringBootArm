package com.aram.connect.persistence;

import java.util.List;

import com.aram.connect.persistence.dao.NewOnlineTest;
import com.aram.connect.persistence.dao.NewOnlineTestAnswerSummary;
import com.aram.connect.persistence.dao.NewOnlineTestQuestion;

public interface NewOnlineTestService {

    public void saveNewOnlineTest(NewOnlineTest newOnlineTest);

    public void saveNewOnlineTestQuestion(NewOnlineTestQuestion newOnlineTestQuestion);

    public List<NewOnlineTest> getNewPrelimsTest();

    public List<NewOnlineTestQuestion> getPrelimsTestQuestions(Long id);

    public NewOnlineTestAnswerSummary getPrelimsTestSummaryByStudentAndTest(Long studentId, Long testId);

    public void savePrelimsTestAnswer(NewOnlineTestAnswerSummary newOnlineTestAnswerSummary);

    public NewOnlineTest findPrelimsTestById(Long id);

    public NewOnlineTestQuestion findPrelimsQuestionById(Long id);
}