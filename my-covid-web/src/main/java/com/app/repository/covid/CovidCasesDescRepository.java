package com.app.repository.covid;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesDescEntity;

public interface CovidCasesDescRepository  extends JpaRepository<CovidCasesDescEntity, Long>  {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CovidCasesDescEntity c WHERE c.description=:desc")
	int deleteDescWithCondition(String desc);	
}
