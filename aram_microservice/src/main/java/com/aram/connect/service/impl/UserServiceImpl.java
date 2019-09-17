package com.aram.connect.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aram.connect.AramConstants;
import com.aram.connect.dataObjects.EmailVO;
import com.aram.connect.dataObjects.GenericResponse;
import com.aram.connect.dataObjects.SmsResponse;
import com.aram.connect.dataObjects.UserLogin;
import com.aram.connect.persistence.UserDetailsService;
import com.aram.connect.persistence.dao.Staff;
import com.aram.connect.persistence.dao.Student;
import com.aram.connect.persistence.dao.UserRole;
import com.aram.connect.service.UserService;
import com.aram.connect.util.AramUtil;
import com.aram.connect.util.EmailNotice;
import com.aram.connect.util.SendSMS;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDetailsService userServiceDetails;

	EmailNotice emailNotice = new EmailNotice();
	
	@Override
	public UserLogin validateUserLogin(UserLogin userLogin) {
		// TODO Auto-generated method stub
		String token = AramUtil.generateToken(userLogin.getPassword(), userLogin.getUserLoginId(), userLogin.getRole());
		String userName = "";
		String phoneNumber = "";
		
		com.aram.connect.persistence.dao.UserLogin user = userServiceDetails.getUserLoginDetails(userLogin.getUserLoginId());
		
		userLogin.setAuthStatus(AramConstants.FAILURE);
		if(!AramUtil.checkIfObjectIsEmpty(user) && user.getUserIdentity().equalsIgnoreCase(token)) {
			userLogin.setAuthStatus(AramConstants.SUCCESS);
			if(3 == user.getRoleId()) {
				Student stud = userServiceDetails.getStudentDetails(user.getUserLoginId());
				if(! AramUtil.checkIfObjectIsEmpty(stud) ) {
					userName = stud.getStudentName();
					
				}
			}else if(2 == user.getRoleId()) {
				Staff staff = userServiceDetails.getStaffDetails(user.getUserLoginId());
				if(! AramUtil.checkIfObjectIsEmpty(staff)) {
					userName = staff.getStaffName();
				}
			}
			
		}else {
			userLogin.setMessage("Invalid Password");
			return userLogin;
		}
		
//		if(AramConstants.STUDENT.equalsIgnoreCase(userLogin.getRole())) {
//			Student stud = userServiceDetails.getStudentDetails(userLogin.getUserLoginId());
//			if(null != stud) phoneNumber = stud.getMobileNumber();
//		}else if(AramConstants.STAFF.equalsIgnoreCase(userLogin.getRole())) {
//			Staff staff = userServiceDetails.getStaffDetails(userLogin.getUserLoginId());
//			if(null != staff) phoneNumber = staff.getMobileNumber();
//		}
//		
//		if( !AramConstants.ADMIN.equalsIgnoreCase(userLogin.getRole()) && !userLogin.getMobileNumber().equalsIgnoreCase(phoneNumber)) {
//			userLogin.setAuthStatus(AramConstants.FAILURE);
//			userLogin.setMessage("Invalid Mobile Number");
//		}
			userLogin.setUserName(userName);
			userLogin.setToken(token);
		return userLogin;
	}

	@Override
	public GenericResponse registeration(int userLoginId, String role, String mobileNumber) {
		GenericResponse response = new GenericResponse();
		String status = AramConstants.FAILURE;
		String email = "";
		String userName = "";
		String password = "";
		String number = "";
		int roleId = 3;
		boolean isUser = false;
		
		com.aram.connect.persistence.dao.UserLogin user = userServiceDetails.getUserLoginDetails(userLoginId);
		
		if(null != user) {
			response.setMessage("User Registered Already");
			response.setStatus(AramConstants.FAILURE);
			return response;
		}
		
		try {
			if(AramConstants.STUDENT.equalsIgnoreCase(role)) {
				Student stud = userServiceDetails.getStudentDetails(userLoginId);
				if(! AramUtil.checkIfObjectIsEmpty(stud) ) {
					isUser = true;
					number = stud.getMobileNumber();
					email = stud.getEmailId();
					userName = stud.getStudentName();
					password = creatPassword(role);
					
				}
			}else if(AramConstants.STAFF.equalsIgnoreCase(role)) {
				Staff staff = userServiceDetails.getStaffDetails(userLoginId);
				if(! AramUtil.checkIfObjectIsEmpty(staff)) {
					isUser = true;
					number = staff.getMobileNumber();
					email = staff.getEmailId();
					userName = staff.getStaffName();
					password = creatPassword(role);
					roleId = 2;
				}
			}else {
				response.setStatus(status);
				response.setMessage("Role doesn't Match");
				response.setStatus(AramConstants.FAILURE);
				return response;
			}
			
			if( !mobileNumber.equalsIgnoreCase(number))  {
				response.setMessage("Please Enter Registered Mobile Number");
				response.setStatus(AramConstants.FAILURE);
				return response;
			}
			
			if(!isUser) {
				response.setMessage("User Id doesn't Exist");
				response.setStatus(AramConstants.FAILURE);
				return response;
			}else {
				SendSMS sms = new SendSMS();
				SmsResponse smsResponse = sms.sendSms(password, number);
				
				if(null == smsResponse ||  AramConstants.FAILURE.equalsIgnoreCase(smsResponse.getStatus())) {
					if(null != smsResponse && null != smsResponse.getErrors() && !smsResponse.getErrors().isEmpty()) {
						response.setMessage(smsResponse.getErrors().get(0).toString());
					}else if(null != smsResponse && null != smsResponse.getWarnings() && !smsResponse.getWarnings().isEmpty()) {
						response.setMessage(smsResponse.getWarnings().get(0).toString());
					}else {
						response.setMessage("Invalid Mobile Number or Couldn't able to send SMS");
					}
					response.setStatus(AramConstants.FAILURE);
					return response;
				}
				
				String token = AramUtil.generateToken(password, userLoginId, role);
				createUserLogin(userLoginId,roleId, userName, token);
				
				
			}
		}catch(Exception e) {
			response.setMessage(e.getMessage());
			e.printStackTrace();
			response.setStatus(AramConstants.FAILURE);
		} catch (Throwable e) {
			response.setMessage(e.getMessage());
			e.printStackTrace();
			response.setStatus(AramConstants.FAILURE);
		}
		response.setMessage("User Successfully Registered!");
		response.setStatus(AramConstants.SUCCESS);
		
		return response;
	}
	
	private com.aram.connect.persistence.dao.UserLogin createUserLogin(int userLoginId , int roleId , String userName , String token){
		
		com.aram.connect.persistence.dao.UserLogin user = new com.aram.connect.persistence.dao.UserLogin();
		user.setCreatedBy(userName);
		user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		user.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		user.setUserLoginId(userLoginId);
		user.setRoleId(roleId);
		user.setUserIdentity(token);
		
		userServiceDetails.createUserLogin(user);
		
		return user;
	}
	
	private void constructAndSendEmail(String emailId, String password) throws Throwable {
		EmailVO email = new EmailVO();
		String[] toArray = new String[1];
		
		email.setContent("<h4>Thank you for Registering in Aram IAS Academy!  Your Password is <h4><h5>"+ password+"</h5>");
		email.setFromAddress(AramConstants.ARAM_GMAIL);
		email.setPassword(AramConstants.ARAM_GMAIL_PWD);
		toArray[0] = emailId;
		email.setToAddress(toArray);
		email.setSubject("AramIasAcadamy Registration Email");
		
		EmailNotice emailNotice = new EmailNotice();
		emailNotice.sendTest(email);
		
		
	}
	
	private String creatPassword(String role) {
		String smallAlphabet = "abcdefghijklmnopqrstuvxyz";
		String capAlphabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
		String numbers = "0123456789";
		String puntuations = "@#$%&";
		String password = smallAlphabet+capAlphabet + numbers + puntuations;
		
		Random rand = new Random();
		
		char[] passChars = new char[4];
		for (int i = 0; i < 4; i++) {
			passChars[i] = password.charAt(rand.nextInt(password.length()));
		}
		
		
		return  "Aram" + role.toLowerCase() + String.valueOf(passChars) ;
		
		
	}

	@Override
	public UserLogin changePassword(UserLogin userLogin) {
		// TODO Auto-generated method stub
		
		UserLogin login = validateUserLogin(userLogin);
		if(AramConstants.FAILURE.equalsIgnoreCase(login.getAuthStatus()) ) return login;
		
		String token = AramUtil.generateToken(userLogin.getNewPassword(), userLogin.getUserLoginId(), userLogin.getRole());
		createUserLogin(userLogin.getUserLoginId(),findRole(userLogin.getRole()),userLogin.getUserName(),token);
		
		userLogin.setToken(token);
		
		return userLogin;
	}
	
	public int findRole(String role) {
		int roleId = 1;
		if(AramConstants.STUDENT.equalsIgnoreCase(role)) {
			roleId=3;
		}else if(AramConstants.STAFF.equalsIgnoreCase(role)) {
			roleId=2;
		}
		
		return roleId;
		
	}

	@Override
	public GenericResponse forgotPassword(int userLoginId, String role, String mobileNumber) {
		// TODO Auto-generated method stub

		GenericResponse response = new GenericResponse();
		String status = AramConstants.FAILURE;
		String email = "";
		String userName = "";
		String password = "";
		String number = "";
		int roleId = 3;
		boolean isUser = false;

		com.aram.connect.persistence.dao.UserLogin user = userServiceDetails.getUserLoginDetails(userLoginId);

		if (null == user) {
			response.setMessage("User Id Doesn't Exist");
			response.setStatus(AramConstants.FAILURE);
			return response;
		} else {
			try {
				if (AramConstants.STUDENT.equalsIgnoreCase(role)) {
					Student stud = userServiceDetails.getStudentDetails(userLoginId);
					if (!AramUtil.checkIfObjectIsEmpty(stud)) {
						isUser = true;
						number = stud.getMobileNumber();
						email = stud.getEmailId();
						userName = stud.getStudentName();
						password = creatPassword(role);
					}
				} else if (AramConstants.STAFF.equalsIgnoreCase(role)) {
					Staff staff = userServiceDetails.getStaffDetails(userLoginId);
					if (!AramUtil.checkIfObjectIsEmpty(staff)) {
						isUser = true;
						number = staff.getMobileNumber();
						email = staff.getEmailId();
						userName = staff.getStaffName();
						password = creatPassword(role);
						roleId = 2;
					}
				} else {
					response.setStatus(status);
					response.setMessage("Role doesn't Match");
					response.setStatus(AramConstants.FAILURE);
					return response;
				}
				
				if( !mobileNumber.equalsIgnoreCase(number))  {
					response.setMessage("Please Enter Registered Mobile Number");
					response.setStatus(AramConstants.FAILURE);
					return response;
				}

				if(!isUser) {
					response.setMessage("User Id doesn't Exist");
					response.setStatus(AramConstants.FAILURE);
					return response;
				}else {

					String token = AramUtil.generateToken(password, userLoginId, role);
					SendSMS sms = new SendSMS();
					SmsResponse smsResponse = sms.sendSms(password, number);
					
					if(null == smsResponse ||  AramConstants.FAILURE.equalsIgnoreCase(smsResponse.getStatus())) {
						if(null != smsResponse.getErrors() && !smsResponse.getErrors().isEmpty()) {
							response.setMessage(smsResponse.getErrors().get(0).toString());
						}else if(null != smsResponse.getWarnings() && !smsResponse.getWarnings().isEmpty()) {
							response.setMessage(smsResponse.getWarnings().get(0).toString());
						}else {
							response.setMessage("Invalid Mobile Number or Couldn't able to send SMS");
						}
						response.setStatus(AramConstants.FAILURE);
						return response;
					}
					
					createUserLogin(userLoginId, roleId, userName, token);
					// constructAndSendEmail(email, password);
					
				}
			} catch (Exception e) {
				response.setMessage(e.getMessage());
				e.printStackTrace();
				response.setStatus(AramConstants.FAILURE);
			} catch (Throwable e) {
				response.setMessage(e.getMessage());
				e.printStackTrace();
				response.setStatus(AramConstants.FAILURE);
			}
			response.setMessage("User successfully Reset");
			response.setStatus(AramConstants.SUCCESS);

			return response;
		}

	}

	@Override
	public UserRole getUserRole(int roleId) {
		return userServiceDetails.getUserRole(roleId);
	}

	@Override
	public Student isStudentExist(String emailId, String mobileNumber) {
		return userServiceDetails.isStudentExist(emailId, mobileNumber);
	}

	@Override
	public Student createStudent(Student student) {
		return userServiceDetails.createStudent(student);
	}

	@Override
	public void createUserLogin(com.aram.connect.persistence.dao.UserLogin userLogin) {
		userServiceDetails.createUserLogin(userLogin);
	}

	@Override
	public void sendOTPViaEmail(String toAddress, String subject, String textMessage) throws Throwable {
		emailNotice.sendEmail(toAddress, subject, textMessage);
	}

	@Override
	public void sendAnswerNotificationMail(String toAddress, String subject, String textMessage) throws Throwable {
		emailNotice.sendEmail(toAddress, subject, textMessage);
	}

	@Override
	public com.aram.connect.persistence.dao.UserLogin getUserLogin(Integer id) {
		return userServiceDetails.getUserLoginDetails(id);
	}

	@Override
	public Student getStudent(Integer studentId) {
		return userServiceDetails.getStudentDetails(studentId);
	}

	@Override
	public void sendUserIDViaEmail(String toAddress, String subject, String textMessage) throws Throwable {
		emailNotice.sendEmail(toAddress, subject, textMessage);	
	}
	
	@Override
	public List<Student> getStudents() {
		return userServiceDetails.getStudents();
	}

	@Override
	public List<Student> getActiveStudents() {
		return userServiceDetails.getActiveStudents();
	}

	@Override
	public List<Student> getInActiveStudents() {
		return userServiceDetails.getInActiveStudents();
	}

	@Override
	public Integer countActiveList(Boolean registeredWithUPSC) {
		return userServiceDetails.countActiveList(registeredWithUPSC);
	}

	@Override
	public Integer countInActiveList(Boolean registeredWithUPSC) {
		return userServiceDetails.countInActiveList(registeredWithUPSC);
	}

	@Override
	public List<Student> activeOffsetList(Integer from, Integer to, Boolean registeredWithUPSC) {
		return userServiceDetails.activeOffsetList(from, to, registeredWithUPSC);
	}

	@Override
	public List<Student> inActiveOffsetList(Integer from, Integer to, Boolean registeredWithUPSC) {
		return userServiceDetails.inActiveOffsetList(from, to, registeredWithUPSC);
	}

	@Override
	public List<Student> searchWithTxtActiveOffsetList(String searchTxt, Integer from, Integer to, Boolean registeredWithUPSC) {
		return userServiceDetails.searchWithTxtActiveOffsetList(searchTxt, from, to, registeredWithUPSC);
	}

	@Override
	public List<Student> searchWithTxtInActiveOffsetList(String searchTxt, Integer from, Integer to, Boolean registeredWithUPSC) {
		return userServiceDetails.searchWithTxtInActiveOffsetList(searchTxt, from, to, registeredWithUPSC);
	}

	@Override
	public Integer countOfSearchWithTxtActive(String searchTxt, Boolean registeredWithUPSC) {
		return userServiceDetails.countOfSearchWithTxtActive(searchTxt, registeredWithUPSC);
	}

	@Override
	public Integer countOfSearchWithTxtInActive(String searchTxt, Boolean registeredWithUPSC) {
		return userServiceDetails.countOfSearchWithTxtInActive(searchTxt, registeredWithUPSC);
	}


}
