package com.app.mining.service.covid.api;

import java.util.List;

import com.app.model.CovidCasesArea;

public interface CovidMiningAPITotalCases {

	String doMining() throws Exception;

	List<CovidCasesArea> getLast5RecordsMY() throws Exception;

	String getTotalfromDB() throws Exception;
}