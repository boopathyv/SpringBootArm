package com.aram.connect.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.aram.connect.AramConstants;
import com.aram.connect.dataObjects.GenericResponse;
import com.aram.connect.dataObjects.Signup;
import com.aram.connect.dataObjects.UserLogin;
import com.aram.connect.dataObjects.VerifyOTP;
import com.aram.connect.persistence.dao.Student;
import com.aram.connect.service.UserService;
import com.aram.connect.util.AramUtil;
import com.aram.connect.util.EmailNotice;
import com.sun.mail.iap.Response;

@RestController
@RequestMapping("/user/*")
public class LoginController {
	
	@Autowired
	UserService userService;

	@SuppressWarnings("unchecked")
	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<UserLogin> loginUserController(@RequestBody UserLogin userLogin) {
		
		if(AramUtil.checkIfObjectIsEmpty(userLogin)) return (ResponseEntity<UserLogin>) ResponseEntity.noContent();
		
		UserLogin login=userService.validateUserLogin(userLogin);
		
		return ResponseEntity.ok(login);		
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	@CrossOrigin
	public ResponseEntity<GenericResponse> loginRegister(@RequestBody UserLogin userLogin){
		GenericResponse response = new GenericResponse();
		
		response.setStatus(AramConstants.FAILURE);
		response.setMessage("User Id doesn't exist");
		if( AramUtil.checkIfStringIsEmpty(userLogin.getRole()) || userLogin.getUserLoginId() == 0 || AramUtil.checkIfStringIsEmpty(userLogin.getMobileNumber())) {
			response.setMessage("User Id or Role or Mobile Number is Invalid");
			response.setStatus(AramConstants.FAILURE);
			return ResponseEntity.ok(response);
		}
		
		response = userService.registeration(userLogin.getUserLoginId(), userLogin.getRole(), userLogin.getMobileNumber());
		
		return ResponseEntity.ok(response);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/changePassword")
	@CrossOrigin
	public ResponseEntity<UserLogin> changePassword(@RequestBody UserLogin userLogin){
		
		if(AramUtil.checkIfObjectIsEmpty(userLogin)) return (ResponseEntity<UserLogin>) ResponseEntity.noContent();
		
		UserLogin login = userService.changePassword(userLogin);
		
		return ResponseEntity.ok(login);
		
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/forgotPwd")
	@CrossOrigin
	public ResponseEntity<GenericResponse> forogtPassword(@RequestBody UserLogin userLogin){
		GenericResponse response = new GenericResponse();
		
		response.setStatus(AramConstants.FAILURE);
		response.setMessage("User Id doesn't exist");
		if( AramUtil.checkIfStringIsEmpty(userLogin.getRole()) || userLogin.getUserLoginId() == 0 || AramUtil.checkIfStringIsEmpty(userLogin.getMobileNumber())) {
			response.setMessage("User Id or Role or Mobile Number is Invalid");
			response.setStatus(AramConstants.FAILURE);
			return ResponseEntity.ok(response);
		}
		
		response = userService.forgotPassword(userLogin.getUserLoginId(), userLogin.getRole(), userLogin.getMobileNumber());
		
		
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/signup")
	@CrossOrigin
	public ResponseEntity<Map> signup(@RequestBody Signup signup) {
		Map<String, String> response = new HashMap<String, String>();
		
		Student student = userService.isStudentExist(signup.getEmailId(), signup.getMobileNumber());
		if(student != null) {
			response.put("error", "Email Id or Mobile Number already exist");
            ResponseEntity.status(400);
            return ResponseEntity.badRequest().body(response);
		}

		Student newStudent = new Student();
		newStudent.setEmailId(signup.getEmailId());
		newStudent.setMobileNumber(signup.getMobileNumber());
		newStudent.setStudentName(signup.getStudentName());
		newStudent.setActiveStatus(false);
		newStudent.setStudentId(null);
		newStudent.setRegisteredWithUPSC(signup.getRegisteredWithUPSC());
		newStudent.setRollNoUPSC(signup.getRollNoUPSC());		

		try {
			String otp = AramUtil.getAlphaNumericString(6);
			String message = "Hi " + newStudent.getStudentName() + "\n  Thanks for your interest in Aram IAS academy. \n  Kindly enter the below OTP to complete the verification process. \n  OTP: " + otp
					+ "\n \n \n      This is a system-generated email, do not reply this email.";
			// System.out.println("Message = " + message);
			// System.out.println("EmailID = " + newStudent.getEmailId());
			userService.sendOTPViaEmail(newStudent.getEmailId(), "Aram IAS Academy - Registration OTP", message);
			userService.createStudent(newStudent);

			com.aram.connect.persistence.dao.UserLogin userLogin = new com.aram.connect.persistence.dao.UserLogin();
			userLogin.setRoleId(3);
			// System.out.println("Student ID = " + newStudent.getStudentId());
			userLogin.setUserLoginId(newStudent.getStudentId());
			String userIdentity = AramUtil.generateToken(signup.password, userLogin.getUserLoginId(), "Student");
			userLogin.setUserIdentity(userIdentity);
			userLogin.setIsVerified(false);
			userLogin.setOTP(otp);
			userService.createUserLogin(userLogin);
	
			response.put("userID", ("" + userLogin.getUserLoginId()));
			response.put("message", "Signup successfully and email send to registered user");
			ResponseEntity.status(200);
			
			
		} catch(Throwable e) {
			System.out.println("Email is not verified..." + e);
			e.printStackTrace();
			ResponseEntity.status(400);
			response.put("Error", "Enter valid email ID");
            return ResponseEntity.badRequest().body(response);

		} 

		return ResponseEntity.ok(response);
	}

	@PostMapping("/verifyOTP")
	@CrossOrigin
	public ResponseEntity<Map> verifyOTP(@RequestBody VerifyOTP verifyOTP) {
		Map<String, String> response = new HashMap<String, String>();

		com.aram.connect.persistence.dao.UserLogin userLogin = userService.getUserLogin(Integer.parseInt(verifyOTP.userID));
		Student student = userService.getStudent(Integer.parseInt(verifyOTP.userID));
		if(userLogin == null || student == null) {
			response.put("error", "User not found");
			ResponseEntity.status(400);
			return ResponseEntity.badRequest().body(response);
		}

		if(!userLogin.getOTP().equals(verifyOTP.otp)) {
			response.put("error", "Enter valid OTP");
			ResponseEntity.status(400);
			return ResponseEntity.badRequest().body(response);

		}

		try {
			String message = "Hi " + student.getStudentName() + "\n  Thank you for registering with Aram IAS Academy. \n  Kindly use the user ID  given below for your login. \n  Your User ID: " + student.getStudentId() 
					+ "\n\n We welcome you to become a part of the change!!! \n \n \n      This is a System generated email, do not reply this email.";
			userService.sendUserIDViaEmail(student.getEmailId(), "Aram IAS Academy - Registration Confirmation", message);

			userLogin.setIsVerified(true);
			userLogin.setOTP("");
			userService.createUserLogin(userLogin);

			student.setActiveStatus(true);
			userService.createStudent(student);
	
			response.put("userID", ("" + userLogin.getUserLoginId()));
			response.put("message", "Signup successfully and email send to registered user");
			ResponseEntity.status(200);

		} catch(Throwable e) {

			System.out.println("Email is not verified..." + e);
			e.printStackTrace();
			ResponseEntity.status(400);
			response.put("Error", "Enter valid email ID");
            return ResponseEntity.badRequest().body(response);

		}

		return ResponseEntity.ok(response);
	}

	@PostMapping("/{userID}/resendOTP")
	@CrossOrigin
	public ResponseEntity<Map> resendOTP(@PathVariable String userID) {
		Map<String, String> response = new HashMap<String, String>();

		com.aram.connect.persistence.dao.UserLogin userLogin = userService.getUserLogin(Integer.parseInt(userID));
		Student student = userService.getStudent(Integer.parseInt(userID));
		if(userLogin == null || student == null) {
			response.put("error", "User not found");
			ResponseEntity.status(400);
			return ResponseEntity.badRequest().body(response);
		}

		try {

			String otp = AramUtil.getAlphaNumericString(6);
			String message = "Hi " + student.getStudentName() + "\n  Thanks for your interest in Aram IAS academy. \n  Kindly enter the below OTP to complete the verification process. \n  OTP: " + otp
			+ "\n \n \n      This is a system-generated email, do not reply this email.";
			userService.sendOTPViaEmail(student.getEmailId(), "Aram IAS Academy - Registration Resend OTP", message);

			userLogin.setIsVerified(false);
			userLogin.setOTP(otp);
			userService.createUserLogin(userLogin);
	
			response.put("userID", ("" + userLogin.getUserLoginId()));
			response.put("message", "Signup successfully and email send to registered user");
			ResponseEntity.status(200);

		} catch(Throwable e) {

			System.out.println("Email is not verified..." + e);
			e.printStackTrace();
			ResponseEntity.status(400);
			response.put("Error", "Enter valid email ID");
            return ResponseEntity.badRequest().body(response);

		}

		return ResponseEntity.ok(response);
	}

	
	
	
}
