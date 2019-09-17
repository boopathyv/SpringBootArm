package com.aram.connect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.aram.connect.AramConstants;
import com.aram.connect.Exception.FileStorageException;
import com.aram.connect.persistence.dao.StudentAnswer;
import com.aram.connect.persistence.dao.TestQuestion;
import com.aram.connect.persistence.TestQuestionPersistService;
import com.aram.connect.service.TestQuestionService;;

@Service
@Transactional
public class TestQuestionServiceImpl implements TestQuestionService {

    @Autowired
    public TestQuestionPersistService testPersistService;
    Path fileStorageLocation = Paths.get("");

    @Override
    public String saveTestQuestionFile(MultipartFile file, Date testDate, String fileType) throws FileStorageException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String directoryName = createDirectories(testDate, fileType);

        try {
            if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            this.fileStorageLocation = Paths.get(AramConstants.serverPath + directoryName);
				// Copy file to the target location (Replacing existing file with the same name)

				Path targetLocation = this.fileStorageLocation.resolve(fileName);

				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
				File fileloc = targetLocation.toFile();
				fileloc.setExecutable(true, false);
				fileloc.setReadable(true, false);
				fileloc.setWritable(true, false);
				return directoryName + "/" + fileName;
        }
        catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
    }

    public String createDirectories(Date testDate, String fileType) {
        String directory = formDirectoryName(testDate);
        directory += fileType;

        File dirPublic = new File(AramConstants.serverPath + directory);
        dirPublic.setWritable(true);
        Boolean success = dirPublic.mkdirs();
        if (success) {
            System.out.println("directory created");
            dirPublic.getParentFile().setWritable(true, false);
            dirPublic.setReadable(true, false);
            dirPublic.setExecutable(true, false);
        }
        return directory;
    }

    public String formDirectoryName(Date testDate) {

        String directoryName = "/testQuestion/";

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(testDate);
        directoryName += calendar.get(Calendar.YEAR) + "/";
        directoryName += (calendar.get(Calendar.MONTH) + 1) + "/";
        directoryName += calendar.get(Calendar.DATE) + "/";

        return directoryName;
    }

    @Override
    public void saveTestQuestion(TestQuestion testQuestion) throws SQLException {
        try {
            testPersistService.saveTestQuestion(testQuestion);
        }
        catch(Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public TestQuestion getTestQuestion(Long id) {
        return testPersistService.getTestQuestion(id);
    }

    @Override
    public void saveStudentAnswer(StudentAnswer studentAnswer) throws SQLException {
        try {
            testPersistService.saveStudentAnswer(studentAnswer);
        }
        catch(Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public StudentAnswer getStudentAnswer(Long id) {
        return testPersistService.getStudentAnswer(id);
    }

    @Override
    public List<TestQuestion> getTestQuestions(String questionType) {
        return testPersistService.getTestQuestions(questionType);
    }

    @Override
    public List<StudentAnswer> getStudentAnswersForTestQuestion(Long testQuestionId) {
        return testPersistService.getStudentAnswersForTestQuestion(testQuestionId);
    }

    @Override
    public List<Object> getTestQuestionsForStudent(String studentId, String questionType) {
        return testPersistService.getTestQuestionsForStudent(studentId, questionType);
    }

}