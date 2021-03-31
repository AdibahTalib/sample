package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.CovidCasesBonus;
import com.app.service.covid.CovidBonusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {

	private final static String GET_MY_BONUS = "/covid/get/bonus";
	
	private final static String ADD_COVID_BONUS = "/covid/add/bonus";

	private final static String DELETE_COVID_BONUS = "/covid/delete/bonus";

	private final static String PUT_API_BONUS = "/covid/put/bonus";

	private final static String POST_API_BONUS = "/covid/post/bonus";

	private final static String DELETE_COVID_SOAPUI_BONUS = "/covid/delete/soap/bonus";
	
	private final static String FIND_DUPLICATE_DELETE_COVID = "/covid/delete/duplicate/bonus";

	@Autowired
	CovidBonusService covidBonusService;

	// CovidCasesBonus - Java POJO 
	// CovidCasesBonusEntity - DB Entity File
	// CovidAreaBonusMapper - Mapper from Java Entity file above to POJO 
	// CovidCasesBonusRepository - Spring JPA Repository or library to query DB. i.e. FindAll() method
	// CovidBonusService - Interface for the service below
	// CovidBonusServiceImpl - Implementation of the service between controller and repo
	
	
	@GetMapping(GET_MY_BONUS)
	List<CovidCasesBonus> bonus() throws Exception {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");

		try {
			
			covidCasesBonus = covidBonusService.bonus();
			if (covidCasesBonus == null) {
				throw new Exception("No bonus yet");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("bonus() exception " + e.getMessage());
			throw new Exception(e);
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}
	
	@GetMapping(ADD_COVID_BONUS)
	CovidCasesBonus addCovidBonus(@RequestParam(required = true) String desc) throws Exception {
		log.info("addCovidBonus() started={}", desc);

		CovidCasesBonus covidCasesBonus = null;
		try {

			if (desc == null || desc.equals("undefined") || desc.equals(""))  {
				throw new NullPointerException(ADD_COVID_BONUS + ", desc is null or empty");
			}
			covidCasesBonus = covidBonusService.addCovidBonus(desc);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return covidCasesBonus;
	}

	@DeleteMapping(DELETE_COVID_BONUS)
	int deleteCovidBonus(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteCovidBonus() started id={}", id);

		int num = 0;
		try {
			
			num = covidBonusService.deleteCovidBonus(id);
			if(num==1)
				return num;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return num;
	}
	
	@PutMapping(PUT_API_BONUS)
	CovidCasesBonus putCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws Exception {
		log.info("putCovidBonus() started, covidCasesBonus={}", covidCasesBonus);

		// complete the implementation below
		return covidBonusService.putCovidBonus(covidCasesBonus);

		//log.info("putCovid() ends, covidCasesDescSaved={}", null);
		//return should be the Saved CovidCasesDesc with values
	}
	
	@PostMapping(POST_API_BONUS)
	CovidCasesBonus postCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws Exception {
		log.info("postCovidBonus() started, covidCasesBonus={}", covidCasesBonus);

		// complete the implementation below
		return covidBonusService.postCovidBonus(covidCasesBonus);

	}
	
	@DeleteMapping(DELETE_COVID_SOAPUI_BONUS)
	int deleteCovidSoapBonus(@RequestParam(required = true) String desc) throws Exception {
		log.info("deleteCovidSoapBonus() started desc={}", desc);
		
		// complete the implementation below
		return covidBonusService.deleteCovidSoapBonus(desc);
	}
	
	// TODO: Angular Practical 11 - Remove Duplicate values
	@DeleteMapping(FIND_DUPLICATE_DELETE_COVID)
	List<String> findDuplicateNdelete() throws Exception {
		log.info("findDuplicateNdelete() started");
		
		// complete the implementation below
		// ensure logic related to repo move to service implementation
		List<String> e = covidBonusService.findDuplicateNdelete();
		
		for (String s: e) {
			log.info ("Duplicate value found on Bonus Table ---> " + s);
						
			log.info ("Value Deleted ---> " + s);
		}
		return e;
	}
}
