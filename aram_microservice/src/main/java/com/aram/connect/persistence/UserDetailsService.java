package com.aram.connect.persistence;

import java.util.List;

import com.aram.connect.persistence.dao.Staff;
import com.aram.connect.persistence.dao.Student;
import com.aram.connect.persistence.dao.UserLogin;
import com.aram.connect.persistence.dao.UserRole;

public interface UserDetailsService {
		
	public UserLogin getUserLoginDetails(int userId);
	
	public Student getStudentDetails(int studentId);
	
	public Staff getStaffDetails(int studentId);
	
	public UserLogin createUserLogin(UserLogin userLogin);
	
	public UserLogin getUserByUsername(String username);

	public UserRole getUserRole(int roleId);

	public Student isStudentExist(String emailId, String mobileNumber);

	public Student createStudent(Student student);

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


	
}
