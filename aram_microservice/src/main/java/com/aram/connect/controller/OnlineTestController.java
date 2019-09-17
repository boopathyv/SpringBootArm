package com.aram.connect.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aram.connect.AramConstants;
import com.aram.connect.Exception.FileStorageException;
import com.aram.connect.dataObjects.CurrentAffairsJson;
import com.aram.connect.dataObjects.GenericResponse;
import com.aram.connect.dataObjects.OnlineTest;
import com.aram.connect.dataObjects.TestAttempt;
import com.aram.connect.dataObjects.TestAttemptDetailVO;
import com.aram.connect.dataObjects.UserTestDetailVO;
import com.aram.connect.dataObjects.mapper.TestAttemptMapper;
import com.aram.connect.persistence.dao.LuTestName;
import com.aram.connect.service.CurrentAffairsService;
import com.aram.connect.service.OnlineTestService;
import com.aram.connect.util.AramUtil;

@RestController
public class OnlineTestController {
	
	@Autowired
	private CurrentAffairsService currentAffairsService;
	
	@Autowired
	private	OnlineTestService onlineTestService;
	
	
//	public ResponseEntity<GenericResponse> calculateUserTestResults(@RequestBody List<TestAttemptDetailVO> attemptDetailList) {
//	
//		GenericResponse genericResponse = new GenericResponse();
//		
//		UserTestDetailVO testDetail = new UserTestDetailVO();
//		testDetail.setAttemptId(1);
//		testDetail.setAttemptQuestion(25);
//		testDetail.setCorrectAnswer(20);
//		testDetail.setTestId(1);
//		testDetail.setTotalMarks(35);
//		testDetail.setUnattendedQuestion(180);
//		testDetail.setUserLoginId(20);
//		testDetail.setWrongAnswer(5);
//		
//		genericResponse.setStatus(AramConstants.SUCCESS);
//		genericResponse.setT(testDetail);
//		
//		return ResponseEntity.ok(genericResponse);
//	}
	
	@CrossOrigin
	@PostMapping("/calculateTestResults")
	public ResponseEntity<GenericResponse> calculateUserTestResult(@RequestBody TestAttempt attemptDetail) {
		
		if( AramUtil.checkIfObjectIsEmpty(attemptDetail)) return ResponseEntity.noContent().build();
		
		TestAttemptMapper mapper = new TestAttemptMapper();
		
		List<TestAttemptDetailVO> attemptDetailList = mapper.mapFromTestAttempt(attemptDetail);
		
		GenericResponse genericResponse = new GenericResponse();
		int testIntId = attemptDetailList.get(0).getTestId(); 
		int userLoginId = attemptDetailList.get(0).getUserLoginId();
		int attemptId = attemptDetailList.get(0).getAttemptId();
		
		if(0 == testIntId || 0 == userLoginId || 0 == attemptId) {
			genericResponse.setStatus(AramConstants.FAILURE);
			genericResponse.setMessage("Test Id , UserLoginId or Attempt Id is missing");
			return ResponseEntity.ok(genericResponse);
		}
		
		UserTestDetailVO testDetail = new UserTestDetailVO();
		List<OnlineTest> testList = new ArrayList<>();
		
		
		
		
		try {
			testList = currentAffairsService.getOnlineTest(testIntId);
			testDetail = onlineTestService.calculateTestResults(testList, attemptDetailList);	
			
			
		} catch (Exception e) {
			System.out.println("Exception in fetching all Online Test"+e);
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		
		
		genericResponse.setStatus(AramConstants.SUCCESS);
		genericResponse.setT(testDetail);
		return ResponseEntity.ok(genericResponse);
		
	}
	
	@CrossOrigin
	@GetMapping("/getTestAttemptDetails")
	public ResponseEntity<List<UserTestDetailVO>>  getTestAttemptDetails(@RequestParam(value = "userLoginId") int userLoginId ){
		
		
		if(0 == userLoginId) {
			return ResponseEntity.noContent().build();
		}
		
		int testId = 0;
		
		List<UserTestDetailVO> userTestList = onlineTestService.getTestAttemptDetails(userLoginId, testId);
		
		if(userTestList.isEmpty()) ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(userTestList);
	}
	
	@CrossOrigin
	@PostMapping("/uploadOnlineTest")
    public String uploadFile(@RequestParam("file") MultipartFile mfile,@RequestParam("heading") String heading) {
        String fileName = "";
        String reason = "";
        
        
        
		try {
			if(null == mfile) return "Please Attach the File";
			
			 String originalFileName = StringUtils.cleanPath(mfile.getOriginalFilename());
			 if( !(originalFileName.endsWith(".csv"))) return "Please Attach the File in correct format";
			
			 reason =  onlineTestService.storeOnlineTest(mfile);
			
			
			System.out.println("Filename " + fileName);
		} catch (FileStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        

        return reason;
    }
	
	@CrossOrigin
	@GetMapping("/getAllTests")
	public ResponseEntity<List<LuTestName>>  getAllTests(){
		
		List<LuTestName> userTestList = onlineTestService.getAllTestName();
		
		if(userTestList.isEmpty()) ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(userTestList);
	}

	
	@CrossOrigin
	@GetMapping("/getImage")
	@RequestMapping(method = RequestMethod.GET,
	produces = MediaType.IMAGE_JPEG_VALUE)
	public void getImage(@RequestParam("file") String fileName, HttpServletResponse response) throws IOException {

		ClassPathResource imgFile = new ClassPathResource("images/" + fileName);

		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	}
	
	
	
	

}
