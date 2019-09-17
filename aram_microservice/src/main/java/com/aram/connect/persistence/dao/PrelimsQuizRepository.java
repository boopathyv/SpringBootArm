package com.aram.connect.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrelimsQuizRepository extends JpaRepository<PrelimsQuiz, Long>{
	
	@Query(value = "SELECT * FROM prelims_quiz u WHERE prelims_quiz_date = ?1",nativeQuery = true)
	List<PrelimsQuiz> findPerlimsQuiz(String maxDate);

}
