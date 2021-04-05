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

import com.app.error.ControllerException;
import com.app.model.CovidCasesBonus;
import com.app.service.covid.CovidBonusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidBonusController {

	private static final String GET_MY_BONUS = "/covid/get/bonus";
	
	private static final String ADD_COVID_BONUS = "/covid/add/bonus";

	private static final String DELETE_COVID_BONUS = "/covid/delete/bonus";

	private static final String PUT_API_BONUS = "/covid/put/bonus";

	private static final String POST_API_BONUS = "/covid/post/bonus";

	private static final String DELETE_COVID_SOAPUI_BONUS = "/covid/delete/soap/bonus";
	
	private static final String FIND_DUPLICATE_DELETE_COVID = "/covid/delete/duplicate/bonus";

	@Autowired
	CovidBonusService covidBonusService;

	// CovidCasesBonus - Java POJO 
	// CovidCasesBonusEntity - DB Entity File
	// CovidAreaBonusMapper - Mapper from Java Entity file above to POJO 
	// CovidCasesBonusRepository - Spring JPA Repository or library to query DB. i.e. FindAll() method
	// CovidBonusService - Interface for the service below
	// CovidBonusServiceImpl - Implementation of the service between controller and repo
	
	
	@GetMapping(GET_MY_BONUS)
	public List<CovidCasesBonus> bonus() throws ControllerException {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");

		try {
			
			covidCasesBonus = covidBonusService.bonus();
			if (covidCasesBonus == null) {
				throw new com.app.error.ControllerException(GET_MY_BONUS, "No bonus yet");
			}
			
		} catch (Exception e) {
			log.error("bonus() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_MY_BONUS, e.getMessage());
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}
	
	@GetMapping(ADD_COVID_BONUS)
	public CovidCasesBonus addCovidBonus(@RequestParam(required = true) String desc) throws ControllerException {
		log.info("addCovidBonus() started={}", desc);

		CovidCasesBonus covidCasesBonus = null;
		try {

			if (desc == null || desc.equals("undefined") || desc.equals(""))  {
				throw new NullPointerException(ADD_COVID_BONUS + ", desc is null or empty");
			}
			covidCasesBonus = covidBonusService.addCovidBonus(desc);
			
		} catch (Exception e) {
			log.error("add() exception " + e.getMessage());
			throw new com.app.error.ControllerException(ADD_COVID_BONUS, e.getMessage());
		}

		return covidCasesBonus;
	}

	@DeleteMapping(DELETE_COVID_BONUS)
	public int deleteCovidBonus(@RequestParam(required = true) long id) throws ControllerException {
		log.info("deleteCovidBonus() started id={}", id);

		int num = 0;
		try {
			
			num = covidBonusService.deleteCovidBonus(id);
			if(num==1)
				return 1;

		} catch (Exception e) {
			log.error("deleteCovid() exception " + e.getMessage());
			throw new com.app.error.ControllerException(DELETE_COVID_BONUS, e.getMessage());
		}

		return 0;
	}
	
	@PutMapping(PUT_API_BONUS)
	public CovidCasesBonus putCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws ControllerException {
		log.info("putCovidBonus() started, covidCasesBonus={}", covidCasesBonus);

		return covidBonusService.putCovidBonus(covidCasesBonus);
	}
	
	@PostMapping(POST_API_BONUS)
	public CovidCasesBonus postCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws ControllerException {
		log.info("postCovidBonus() started, covidCasesBonus={}", covidCasesBonus);

		return covidBonusService.postCovidBonus(covidCasesBonus);

	}
	
	@DeleteMapping(DELETE_COVID_SOAPUI_BONUS)
	public int deleteCovidSoapBonus(@RequestParam(required = true) String desc) throws ControllerException {
		log.info("deleteCovidSoapBonus() started desc={}", desc);
		
		return covidBonusService.deleteCovidSoapBonus(desc);
	}
	
	@DeleteMapping(FIND_DUPLICATE_DELETE_COVID)
	public List<String> findDuplicateNdelete() throws ControllerException {
		log.info("findDuplicateNdelete() started");

		List<String> e = covidBonusService.findDuplicateNdelete();
		
		for (String s: e) {
			log.info ("Duplicate value found on Bonus Table ---> " + s);
						
			log.info ("Value Deleted ---> " + s);
		}
		return e;
	}
}
