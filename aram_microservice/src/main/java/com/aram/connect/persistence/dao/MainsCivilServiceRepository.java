package com.aram.connect.persistence.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MainsCivilServiceRepository extends JpaRepository<MainsCurrentAffairs, Long>{
	
	@Query(value = "SELECT * FROM mains_current_affiars u WHERE mains_date between ?1 and ?2 order by mains_date desc",nativeQuery = true)
	List<MainsCurrentAffairs>findMainsCurrentAffaris(String minDate, String maxDate);


}
