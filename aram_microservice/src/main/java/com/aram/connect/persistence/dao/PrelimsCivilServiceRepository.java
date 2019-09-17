package com.aram.connect.persistence.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrelimsCivilServiceRepository extends JpaRepository<PrelimsCurrentAffairs, Long>{
	
	@Query(value = "SELECT * FROM prelims_current_affiars u WHERE prelims_date between ?1 and ?2 order by prelims_date desc",nativeQuery = true)
	List<PrelimsCurrentAffairs>findPerlimsCurrentAffaris(String minDate, String maxDate);


}
