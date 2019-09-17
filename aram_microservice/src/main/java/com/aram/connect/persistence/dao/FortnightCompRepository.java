package com.aram.connect.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aram.connect.dataObjects.FortnightCompliation;

public interface FortnightCompRepository extends JpaRepository<FortnightCompliation, Long> {
	
	@Query(value = "SELECT * FROM fortnight_compilation u WHERE fortnight_date between ?1 and ?2 order by fortnight_date desc",nativeQuery = true)
	List<FortnightCompliation> getSomeFortnightCompliations(String minDate, String maxDate);
	

}
