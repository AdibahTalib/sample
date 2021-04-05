package com.app.mining.service.covid.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.app.model.CovidCasesArea;

public interface CovidMiningAPITotalCases {

	public String doMining() throws IOException, ParseException;

	public List<CovidCasesArea> getLast5RecordsMY();

	public String getTotalfromDB();
}
