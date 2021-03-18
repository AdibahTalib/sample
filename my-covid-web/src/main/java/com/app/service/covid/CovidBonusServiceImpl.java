package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;

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
}
