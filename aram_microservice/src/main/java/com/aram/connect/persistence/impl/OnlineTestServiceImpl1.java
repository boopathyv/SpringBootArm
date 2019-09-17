package com.aram.connect.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aram.connect.dataObjects.OnlineTest;
import com.aram.connect.persistence.OnlineTestService1;
import com.aram.connect.persistence.dao.LuTestName;
import com.aram.connect.persistence.dao.OnlineTestRepository;
import com.aram.connect.service.CurrentAffairsService;
import com.aram.connect.util.AramUtil;

@Repository
@Transactional
public class OnlineTestServiceImpl1 implements OnlineTestService1 {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CurrentAffairsService currentAffairs;

	@Autowired
	private OnlineTestRepository onlineTestRepo;


	@Override
	public String storeOnlineTest(List<OnlineTest> testList) {

		String status = "Success";
		if(AramUtil.checkIfListIsEmpty(testList)) return "No Data";

		try {
			Long testId = testList.get(0).getTestId();

			List<OnlineTest> existingList = currentAffairs.getOnlineTest(testId);

			if(!AramUtil.checkIfListIsEmpty(existingList)) {
				deleteOnlineTest(testId);
			}

			for(OnlineTest test : testList) {
				String question = AramUtil.addParaStyling(test.getOnlineTestQuestion());
				test.setOnlineTestQuestion(question);
				String explanation = AramUtil.addParaStyling(test.getExplanation());
				test.setExplanation(explanation);
				String para = AramUtil.addParaStyling(test.getParagraph());
				test.setParagraph(para);
				System.out.println("explanantion : "+test.getExplanation() );
				System.out.println("Paragraph :" + test.getParagraph());
				em.persist(test);
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "Failure";
		}




		return status;
	}

	public boolean deleteOnlineTest(Long testId) {
		try {

			//			TypedQuery<OnlineTest> q = em.createNamedQuery("OnlineTest.deleteByTestId", OnlineTest.class);
			//			q.setParameter("tId", testId);
			//			q.executeUpdate();

			int result = onlineTestRepo.deleteTestById(testId);
			if(result == 1) {
				System.out.println("Successfully Deleted All the question for Test Id :" + testId);
			}
		}catch(Exception e) {
			System.out.println("Error deleteing the online Test Id" + testId);
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<LuTestName> getAllTest() {

		List<LuTestName> list = new ArrayList<>();

		try {
			TypedQuery<LuTestName> q = em.createNamedQuery("LuTestName.getAll", LuTestName.class);
			list = q.getResultList();

		}catch(Exception e) {

			e.printStackTrace();
		}

		return list;
	}

	@Override
	public String getTestName(long testId) {
		
		String testName= "";
		LuTestName list = new LuTestName();

		try {
			TypedQuery<LuTestName> q = em.createNamedQuery("LuTestName.getAll", LuTestName.class);
			q.setParameter("tId", testId);
			list = q.getSingleResult();
			if(null != list) {
				testName = list.getTestName();
			}

		}catch(Exception e) {

			e.printStackTrace();
		}
		
		return testName;
	}

}
