package com.aram.connect.dataObjects.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aram.connect.dataObjects.QandA;
import com.aram.connect.dataObjects.TestAttempt;
import com.aram.connect.dataObjects.TestAttemptDetailVO;
import com.aram.connect.persistence.dao.TestAttemptDetail;

public class TestAttemptMapper {

	public List<TestAttemptDetail> convertVOToEntity(List<TestAttemptDetailVO> attemptVOList , List<TestAttemptDetail> attemptList) {

		List<TestAttemptDetail> newAttemptList = new ArrayList<>();
		Map<Long,TestAttemptDetail> existingAttemptMap = new HashMap<>();
		
		for(TestAttemptDetail testDetail : attemptList) {
			existingAttemptMap.put(testDetail.getAttendedQuestionId(), testDetail);
		}
		//existingAttemptMap = attemptList.stream().collect(Collectors.toMap(x->x.getAttendedQuestionId(), x->x));

		for(TestAttemptDetailVO vo : attemptVOList) {
			if( null == existingAttemptMap.get(vo.getAttendedQuestionId())) {
				newAttemptList.add(mapVOToEntity(vo));
				continue;
			}

			TestAttemptDetail exisitingVO = existingAttemptMap.get(vo.getAttendedQuestionId());
			exisitingVO.setAttemptId(vo.getAttemptId());
			exisitingVO.setAttendedAnswer(vo.getAttendedAnswer());
			exisitingVO.setAttendedQuestionId(vo.getAttendedQuestionId());
			exisitingVO.setTestId(vo.getTestId());
			exisitingVO.setUserLoginId(vo.getUserLoginId());
			newAttemptList.add(mapVOToEntity(vo));
		}

		return newAttemptList;
	}


	public TestAttemptDetail mapVOToEntity(TestAttemptDetailVO detailVO) {

		TestAttemptDetail attempt = new TestAttemptDetail();
		attempt.setAttemptId(detailVO.getAttemptId());
		attempt.setAttendedAnswer(detailVO.getAttendedAnswer());
		attempt.setAttendedQuestionId(detailVO.getAttendedQuestionId());
		attempt.setTestAttemptDetailId(detailVO.getTestAttemptDetailId());
		attempt.setTestId(detailVO.getTestId());
		attempt.setUserLoginId(detailVO.getUserLoginId());

		return attempt;
	}

	public TestAttemptDetail mapVOToEntity(TestAttemptDetail existing , TestAttemptDetailVO detailVO) {

		existing.setAttemptId(detailVO.getAttemptId());
		existing.setAttendedAnswer(detailVO.getAttendedAnswer());
		existing.setAttendedQuestionId(detailVO.getAttendedQuestionId());
		existing.setTestId(detailVO.getTestId());
		existing.setUserLoginId(detailVO.getUserLoginId());

		return existing;
	}
	
	public List<TestAttemptDetailVO> mapFromTestAttempt(TestAttempt testAttempt){
		
		List<TestAttemptDetailVO> voList = new ArrayList<>();
		
		for(QandA qa : testAttempt.getQalist()) {
			TestAttemptDetailVO vo = new TestAttemptDetailVO();
			vo.setAttemptId(testAttempt.getAttemptid());
			vo.setTestId(testAttempt.getTestid());
			vo.setUserLoginId(testAttempt.getUserloginid());
			vo.setAttendedQuestionId(qa.getAttendedquestionid());
			vo.setAttendedAnswer(qa.getAttendedanswer());
			voList.add(vo);
			
		}
		
		return voList;
	}

}
