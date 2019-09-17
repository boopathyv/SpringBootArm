package com.aram.connect.persistence;

import java.sql.SQLException;
import java.util.List;

import com.aram.connect.persistence.dao.StudentAnswer;
import com.aram.connect.persistence.dao.TestQuestion;

public interface TestQuestionPersistService {

    public void saveTestQuestion(TestQuestion testQuestion) throws SQLException;

    public TestQuestion getTestQuestion(Long id);

    public void saveStudentAnswer(StudentAnswer studentAnswer) throws SQLException;

    public StudentAnswer getStudentAnswer(Long id);

    public List<TestQuestion> getTestQuestions( String questionType);

    public List<StudentAnswer> getStudentAnswersForTestQuestion(Long testQuestionId);

    public List<Object> getTestQuestionsForStudent(String studentId, String questionType);
}