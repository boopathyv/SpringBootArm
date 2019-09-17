package com.aram.connect.persistence.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MainsQuizRepository extends JpaRepository<MainsQuiz, Long>{
	
	@Query(value = "SELECT * FROM mains_quiz u WHERE mains_quiz_date between ?1 and ?2 order by mains_quiz_date desc",nativeQuery = true)
	List<MainsQuiz> findMainsQuiz(String minDate, String maxDate);


}
