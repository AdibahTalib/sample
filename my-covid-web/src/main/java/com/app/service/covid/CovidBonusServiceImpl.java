package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesBonusEntity;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaBonusMapper;
import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;

import fr.xebia.extras.selma.Selma;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CovidBonusServiceImpl implements CovidBonusService{
	@Autowired
	CovidCasesBonusRepository covidCasesBonusRepository;
	
	@Override
	public List<CovidCasesBonus> bonus() throws Exception {
		// TODO Auto-generated method stub
//		List<CovidCasesBonus> CovidCasesBonus = null;
		log.info("bonus() started");
		
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		List<CovidCasesBonus> CovidCasesBonus = new ArrayList<CovidCasesBonus>();
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				CovidCasesBonus.add(model);
				log.info("entity total desc={}", entity.getDescription());
			}
			log.info(" bonus() return Size={}", covidCaseBonusEntities.size());
		}
		log.info("bonus() ends");
		return CovidCasesBonus;
	}

	@Override
	public CovidCasesBonus addCovidBonus(String desc) {
		// TODO Auto-generated method stub
		log.info("addCovidBonus started");
		
		CovidCasesBonus covidCasesBonus = null;
		
		CovidCasesBonusEntity covidCasesBonusEntity = new CovidCasesBonusEntity();
	
		covidCasesBonusEntity.setDescription(desc);
	
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
	
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
	
		covidCasesBonus = mapper.asResource(savedEntity);
		
		return covidCasesBonus;	
	}

	@Override
	public int deleteCovidBonus(long id) {
		// TODO Auto-generated method stub
		log.info("deleteCovidBonus started");

		Optional<CovidCasesBonusEntity> entityOptional = covidCasesBonusRepository.findById(id);

		log.info("Entity found == " + entityOptional.isPresent());

		if (entityOptional.isPresent()) {
			CovidCasesBonusEntity covidCasesBonusEntity = entityOptional.get();
				
			covidCasesBonusRepository.delete(covidCasesBonusEntity);
				
			return 1;
		}
		return 0;	}

	@Override
	public CovidCasesBonus putCovidBonus(CovidCasesBonus covidCasesBonus) {
		// TODO Auto-generated method stub
		log.info("putCovid started");
		
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		
		CovidCasesBonusEntity covidCasesBonusEntity = mapper.asEntity(covidCasesBonus);
		
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
		
		covidCasesBonus = mapper.asResource(savedEntity);

		return covidCasesBonus;	
	}

	@Override
	public CovidCasesBonus postCovidBonus(CovidCasesBonus covidCasesBonus) {
		// TODO Auto-generated method stub
		log.info("postCovidBonus started");
		
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		
		CovidCasesBonusEntity covidCasesBonusEntity = mapper.asEntity(covidCasesBonus);
		
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
		
		covidCasesBonus = mapper.asResource(savedEntity);

		return covidCasesBonus;	
	}

	@Override
	public int deleteCovidSoapBonus(String desc) {
		// TODO Auto-generated method stub
		int deleted = covidCasesBonusRepository.deleteDescWithCondition(desc);

		return deleted;
	}
	
	public List<String> findDuplicateNdelete() {
		log.info("findDuplicateNdelete() started");
		
		List<String> e = covidCasesBonusRepository.findDuplicate();
		
		for (String s: e) {
			covidCasesBonusRepository.deleteDescWithCondition(s);
		}
		
		return e;
	}
}
