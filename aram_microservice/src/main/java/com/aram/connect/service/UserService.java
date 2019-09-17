package com.aram.connect.service;

import java.util.List;

import com.aram.connect.dataObjects.GenericResponse;
import com.aram.connect.dataObjects.UserLogin;
import com.aram.connect.persistence.dao.Student;
import com.aram.connect.persistence.dao.UserRole;

public interface UserService {
	
	public UserLogin validateUserLogin(UserLogin userLogin);
	
	public GenericResponse registeration(int userLoginId, String role, String mobileNumber);
	
	public GenericResponse forgotPassword(int userLoginId, String role,String mobileNumber);
	
	public UserLogin changePassword(UserLogin userLogin);

	public UserRole getUserRole(int roleId);

	public Student isStudentExist(String emailId, String mobileNumber);

	public Student createStudent(Student student);

	public void createUserLogin(com.aram.connect.persistence.dao.UserLogin userLogin);

	public void sendOTPViaEmail(String toAddress, String subject, String textMessage) throws Throwable;

	public com.aram.connect.persistence.dao.UserLogin getUserLogin(Integer id); 

	public Student getStudent(Integer studentId);

	public void sendUserIDViaEmail(String toAddress, String subject, String textMessage) throws Throwable;

	public List<Student> getStudents();

	public List<Student> getActiveStudents();

	public List<Student> getInActiveStudents();

	public Integer countActiveList(Boolean registeredWithUPSC);

	public Integer countInActiveList(Boolean registeredWithUPSC);

	public List<Student> activeOffsetList(Integer from, Integer To, Boolean registeredWithUPSC);

	public List<Student> inActiveOffsetList(Integer from, Integer To, Boolean registeredWithUPSC);

	public List<Student> searchWithTxtActiveOffsetList(String searchTxt, Integer from, Integer To, Boolean registeredWithUPSC);

	public List<Student> searchWithTxtInActiveOffsetList(String searchTxt, Integer from, Integer To, Boolean registeredWithUPSC);

	public Integer countOfSearchWithTxtActive(String searchTxt, Boolean registeredWithUPSC);

	public Integer countOfSearchWithTxtInActive(String searchTxt, Boolean registeredWithUPSC);

	public void sendAnswerNotificationMail(String toAddress, String subject, String textMessage) throws Throwable;
	
}
