package com.aram.connect.dataObjects.mapper;

import com.aram.connect.dataObjects.UserTestDetailVO;
import com.aram.connect.persistence.dao.UserTestDetail;
import com.aram.connect.util.AramUtil;

public class UserTestDetailMapper {

	public UserTestDetail mapToEntity(UserTestDetailVO detailVO) {

		UserTestDetail testDetail = new UserTestDetail();
		testDetail.setAttemptId(detailVO.getAttemptId());
		testDetail.setAttemptQuestion(detailVO.getAttemptQuestion());
		testDetail.setCorrectAnswer(detailVO.getCorrectAnswer());
		testDetail.setTestId(detailVO.getTestId());
		testDetail.setTotalMarks(detailVO.getTotalMarks());
		testDetail.setUnattendedQuestion(detailVO.getUnattendedQuestion());
		testDetail.setUserLoginId(detailVO.getUserLoginId());
		testDetail.setUserTestId(detailVO.getUserTestId());
		testDetail.setWrongAnswer(detailVO.getWrongAnswer());

		return testDetail;
	}

	public void convertEntityToEntity(UserTestDetail testDetail, UserTestDetail detailVO ) {
		testDetail.setAttemptId(detailVO.getAttemptId());
		testDetail.setAttemptQuestion(detailVO.getAttemptQuestion());
		testDetail.setCorrectAnswer(detailVO.getCorrectAnswer());
		testDetail.setTestId(detailVO.getTestId());
		testDetail.setTotalMarks(detailVO.getTotalMarks());
		testDetail.setUnattendedQuestion(detailVO.getUnattendedQuestion());
		testDetail.setUserLoginId(detailVO.getUserLoginId());
		testDetail.setWrongAnswer(detailVO.getWrongAnswer());
	}

	public UserTestDetailVO mapToVO(UserTestDetail detailVO) {

		UserTestDetailVO testDetail = new UserTestDetailVO();
		testDetail.setAttemptId(detailVO.getAttemptId());
		testDetail.setAttemptQuestion(detailVO.getAttemptQuestion());
		testDetail.setCorrectAnswer(detailVO.getCorrectAnswer());
		testDetail.setTestId(detailVO.getTestId());
		testDetail.setTotalMarks(detailVO.getTotalMarks());
		testDetail.setUnattendedQuestion(detailVO.getUnattendedQuestion());
		testDetail.setUserLoginId(detailVO.getUserLoginId());
		testDetail.setUserTestId(detailVO.getUserTestId());
		testDetail.setWrongAnswer(detailVO.getWrongAnswer());
		testDetail.setAttemptDate(AramUtil.timestampToString(detailVO.getModifiedDate()));

		return testDetail;
	}

}
