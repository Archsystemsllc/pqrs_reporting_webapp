package com.archsystemsinc.pqrs.restcontroller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archsystemsinc.pqrs.model.ProviderHypothesis;
import com.archsystemsinc.pqrs.service.ProviderHypothesisService;

@RestController
@RequestMapping("/rest/api")
public class ProviderHypothesisRestController {
	@Autowired
	private ProviderHypothesisService providerHypothesisService;
	
	@RequestMapping("/barChart/year/{year}/reportingOption/{reportingOption}")
    public Map zipcodes(@PathVariable("year") String year, @PathVariable("reportingOption") String reportingOption, HttpServletRequest request, Principal currentUser, Model model) {
		
		Map<String,List<?>> barChartMap = new LinkedHashMap<>();		
		
		final List<ProviderHypothesis> providerHypothesisList = providerHypothesisService.findByYearLookupAndReportingOptionLookup(year, reportingOption);		
		
		List<String> parameters = new ArrayList<>();
		List<Double> yesPercents = new ArrayList<>();
		List<Double> noPercents = new ArrayList<>();
		
		for (ProviderHypothesis providerHypothesis : providerHypothesisList){
			parameters.add("\""+providerHypothesis.getParameterLookup().getParameterName()+"\"");
			yesPercents.add(providerHypothesis.getYesPercent());
			noPercents.add(providerHypothesis.getNoPercent());
		}
		
		barChartMap.put("parameters", parameters);
		barChartMap.put("yesPercents", yesPercents);
		barChartMap.put("noPercents", noPercents);
        return barChartMap;
    }
	
	@RequestMapping("/lineChart/parameter/{parameter}")
    public Map lineChart(@PathVariable("parameter") String parameterName, HttpServletRequest request, Principal currentUser, Model model) {

		//model.addAttribute("parameterName", parameterName);
		Map<String,List<?>> lineChartMap = new LinkedHashMap<>();
		
		final List<ProviderHypothesis> providerHypothesisList = providerHypothesisService.findByParameterLookup(parameterName);
		
		List<String> uniqueYears = providerHypothesisService.getUniqueYearsForLineChart();
		List<Double> claimsPercents = new ArrayList<Double>();
		List<Double> ehrPercents = new ArrayList<Double>();
		List<Double> registryPercents = new ArrayList<Double>();
		List<Double> gprowiPercents = new ArrayList<Double>();
		List<Double> qcdrPercents = new ArrayList<Double>();
		
		providerHypothesisService.setRPPercentValue(providerHypothesisList, claimsPercents, ehrPercents, registryPercents, gprowiPercents, qcdrPercents);
		
		lineChartMap.put("uniqueYears", uniqueYears);
		lineChartMap.put("claimsPercents", claimsPercents);
		lineChartMap.put("ehrPercents", ehrPercents);
		lineChartMap.put("registryPercents", registryPercents);
		lineChartMap.put("gprowiPercents", gprowiPercents);
		lineChartMap.put("qcdrPercents", qcdrPercents);
		
        return lineChartMap;
    }

}
