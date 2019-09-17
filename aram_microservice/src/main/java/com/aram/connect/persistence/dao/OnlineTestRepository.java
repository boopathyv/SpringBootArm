package com.aram.connect.persistence.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.aram.connect.dataObjects.OnlineTest;

public interface OnlineTestRepository extends JpaRepository<OnlineTest, Long> {
	
	@Query(value = "SELECT * FROM online_test u WHERE test_id = ?1 order by question_id",nativeQuery = true)
	List<OnlineTest> getTestById(long testId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM online_test WHERE test_id = ?1",nativeQuery = true)
	int deleteTestById(long testId);

}
