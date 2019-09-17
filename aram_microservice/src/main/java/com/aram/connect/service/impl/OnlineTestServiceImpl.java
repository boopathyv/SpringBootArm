package com.aram.connect.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aram.connect.Exception.AramException;
import com.aram.connect.dataObjects.OnlineTest;
import com.aram.connect.dataObjects.TestAttemptDetailVO;
import com.aram.connect.dataObjects.UserTestDetailVO;
import com.aram.connect.dataObjects.mapper.TestAttemptMapper;
import com.aram.connect.dataObjects.mapper.UserTestDetailMapper;
import com.aram.connect.persistence.OnlineTestService1;
import com.aram.connect.persistence.TestAttemptDetailService;
import com.aram.connect.persistence.UserTestDetailService;
import com.aram.connect.persistence.dao.LuTestName;
import com.aram.connect.persistence.dao.TestAttemptDetail;
import com.aram.connect.persistence.dao.UserTestDetail;
import com.aram.connect.service.OnlineTestService;
import com.aram.connect.util.AramUtil;
import com.aram.connect.util.GenericFileReader;

@Service
@Transactional
public class OnlineTestServiceImpl implements OnlineTestService {
	
	@Autowired
	private TestAttemptDetailService testAttemptService;
	
	@Autowired
	private UserTestDetailService userTestService;
	
	@Autowired
	private OnlineTestService1 testService;
	
	private static DecimalFormat df2 = new DecimalFormat(".##");

	@Override
	public UserTestDetailVO calculateTestResults(List<OnlineTest> testList, List<TestAttemptDetailVO> attemptList) {

		UserTestDetailVO testDetail = new UserTestDetailVO();
		Map<Long,OnlineTest> onlineTestMap = new HashMap<>();
		TestAttemptMapper mapper = new TestAttemptMapper();
		UserTestDetailMapper userMapper = new UserTestDetailMapper();
		
		onlineTestMap = testList.stream().collect(Collectors.toMap(x->x.getQuestionId(), x->x));
		int userLoginId = attemptList.get(0).getUserLoginId();
		int attemptId = attemptList.get(0).getAttemptId();
		int testIntId = attemptList.get(0).getTestId(); 
		int questionCount = testList.size();
		double mark = questionCount < 100 ? 2.5 : 2.0;
		double negativeMark = questionCount < 100 ? (0.83) : (0.67);
		int attemptedQuestion = attemptList.size();
		int unattendedQuestion = questionCount - attemptedQuestion;
		int correctAnswer = 0;
		int incorrectAnswer = 0;
		
		for(TestAttemptDetailVO testAttempt : attemptList) {
			
			if(null == onlineTestMap.get(testAttempt.getAttendedQuestionId())) continue;
			OnlineTest onlineTest = onlineTestMap.get( testAttempt.getAttendedQuestionId() );
			if(onlineTest.getAnswer().equalsIgnoreCase(testAttempt.getAttendedAnswer()) ) {
				correctAnswer ++;
			}else {
				incorrectAnswer ++ ;
			}
			
		}
		
		double positiveMarks = correctAnswer * mark;
		double negativeMarks = incorrectAnswer * negativeMark;
		
		double totalMarks = Double.parseDouble(df2.format(positiveMarks - negativeMarks));
		testDetail.setAttemptId(attemptId);
		testDetail.setCorrectAnswer(correctAnswer);
		testDetail.setWrongAnswer(incorrectAnswer);
		testDetail.setTestId(testIntId);
		testDetail.setAttemptQuestion(attemptedQuestion);
		testDetail.setUnattendedQuestion(unattendedQuestion);
		testDetail.setUserLoginId(userLoginId);
		testDetail.setTotalMarks(totalMarks);
		
		System.out.println(" Calculated Result " + testDetail.toString());
		
		List<com.aram.connect.persistence.dao.TestAttemptDetail> testDetailList = testAttemptService.getTestAttemptDetail(testIntId ,userLoginId , attemptId);
		List<TestAttemptDetail> newAttemptList = new ArrayList<>();
		
		if( !AramUtil.checkIfListIsEmpty(testDetailList)) {
			newAttemptList = mapper.convertVOToEntity(attemptList, testDetailList);
		}else {
			newAttemptList = attemptList.stream().map(x-> mapper.mapVOToEntity(x)).collect(Collectors.toList());
		}
		
		
		
		try {
			testAttemptService.storeTestAttemptPerUser(newAttemptList);
			userTestService.updateUserTestDetail(userMapper.mapToEntity(testDetail));
			
		} catch (AramException e) {
			e.printStackTrace();
		}
		
		return testDetail;
	}

	@Override
	public List<UserTestDetailVO> getTestAttemptDetails(int userLoginId, int testId) {
		// TODO Auto-generated method stub
		List<UserTestDetailVO> finalTestList = new ArrayList<>();
		List<UserTestDetail> testDetailList = userTestService.getTestDetailPerUser(userLoginId, testId);
		UserTestDetailMapper userMapper = new UserTestDetailMapper();
		List<LuTestName> nameList = testService.getAllTest();
		Map<Long,String> map = getTestNameList(nameList);
		for(UserTestDetail userTest : testDetailList) {
			UserTestDetailVO vo = userMapper.mapToVO(userTest);
			vo.setTestName(map.get(((long)vo.getTestId())));
			finalTestList.add(vo);
		}
		
		return finalTestList;
	}

	@SuppressWarnings("unused")
	@Override
	public String storeOnlineTest(MultipartFile file) {
		String status ="";
		List<String> errorList = new ArrayList<>();
		Path fileStorageLocation = Paths.get("");
		String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path targetLocation = fileStorageLocation.resolve(originalFileName);
		try {
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			List<OnlineTest> questionList = GenericFileReader.fileReader(targetLocation,OnlineTest.class, ",",errorList);
			//questionList.forEach(System.out::println);
			System.out.println(" Question size" + questionList.size());
			if(errorList.size() > 0) {
				errorList.forEach(System.out::println);
				return "Columns are not Matching";
			}
			status = testService.storeOnlineTest(questionList);
			
		} catch (IOException e) {
			e.printStackTrace();
			status = "Failure";
		}
		
		return status;
	}
	
	private Map<Long,String> getTestNameList(List<LuTestName> nameList){
		
		Map<Long,String> map = new HashMap<>();
		if(AramUtil.checkIfListIsEmpty(nameList)) return map;
		map = nameList.stream().collect(Collectors.toMap(x->x.getTestId(), x->x.getTestName()));
		return map;
	}

	@Override
	public List<LuTestName> getAllTestName() {
		
		return testService.getAllTest();
	}

}
