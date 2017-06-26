package com.archsystemsinc.pqrs.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.archsystemsinc.pqrs.model.ProviderHypothesis;
import com.archsystemsinc.pqrs.service.ProviderHypothesisService;

/**
 * This is the Spring Controller Class for Bar and Line Chart Implementation.
 * 
 * @author Murugaraj Kandaswamy
 * @since 6/19/2017
 */
@Controller
public class ProviderHypothesisController {
	
	@Autowired
	private ProviderHypothesisService providerHypothesisService;

	@RequestMapping("/barChart/year/{year}/reportingOption/{reportingOption}")
	@ResponseBody
    public Map barChartDisplay(@PathVariable("year") String year, @PathVariable("reportingOption") String reportingOption, HttpServletRequest request, Principal currentUser, Model model) {
		//model.addAttribute("filter", filter);
		model.addAttribute("year", year);
		model.addAttribute("reportingOption", reportingOption);
		String dataAvailable = "NO";
		
		Map barChartDataMap = new HashMap();
		ObjectMapper mapperObj = new ObjectMapper();
		
		final List<ProviderHypothesis> providerHypothesisList = providerHypothesisService.findByYearLookupAndReportingOptionLookup(year, reportingOption);
		
		// Preparing Parameter String Array
		List<String> parameters = new ArrayList<String>();
		List<Double> yesPercents = new ArrayList<Double>();
		List<Double> noPercents = new ArrayList<Double>();
		
		for (ProviderHypothesis providerHypothesis : providerHypothesisList){
			//parameters.add("\""+providerHypothesis.getParameterLookup().getParameterName()+"\"");
			parameters.add(providerHypothesis.getParameterLookup().getParameterName());
			yesPercents.add(providerHypothesis.getYesPercent());
			noPercents.add(providerHypothesis.getNoPercent());
			dataAvailable = "YES";
		}
		
/*		model.addAttribute("parameters", parameters);
		model.addAttribute("yesPercents", yesPercents);
		model.addAttribute("noPercents", noPercents);*/
		
		// Setting barChartData in the Map to be returned back to View....
		barChartDataMap.put("parameters", parameters);
		barChartDataMap.put("yesPercents", yesPercents);
		barChartDataMap.put("noPercents", noPercents);
		barChartDataMap.put("dataAvailable", dataAvailable);
		
/*		String jsonResp = null;
		
		try {
        	
            jsonResp = mapperObj.writeValueAsString(barChartDataMap);
            System.out.println(jsonResp);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
		
		//return jsonResp;
        return barChartDataMap;
    }
	
	
	@RequestMapping("/lineChart/parameter/{parameter}")
	@ResponseBody
    public Map lineChartDisplay(@PathVariable("parameter") String parameterName, HttpServletRequest request, Principal currentUser, Model model) {

		model.addAttribute("parameterName", parameterName);
		Map lineChartDataMap = new HashMap();
		String dataAvailable = "NO";
		
		final List<ProviderHypothesis> providerHypothesisList = providerHypothesisService.findByParameterLookup(parameterName);
		
		if (providerHypothesisList != null && providerHypothesisList.size()>0){
			dataAvailable = "YES";
		}
		
		List<String> uniqueYears = providerHypothesisService.getUniqueYearsForLineChart();
		List<Double> claimsPercents = new ArrayList<Double>();
		List<Double> ehrPercents = new ArrayList<Double>();
		List<Double> registryPercents = new ArrayList<Double>();
		List<Double> gprowiPercents = new ArrayList<Double>();
		List<Double> qcdrPercents = new ArrayList<Double>();
		
		providerHypothesisService.setRPPercentValue(providerHypothesisList, claimsPercents, ehrPercents, registryPercents, gprowiPercents, qcdrPercents);
		
/*		model.addAttribute("uniqueYears", uniqueYears);
		model.addAttribute("claimsPercents", claimsPercents);
		model.addAttribute("ehrPercents", ehrPercents);
		model.addAttribute("registryPercents", registryPercents);
		model.addAttribute("gprowiPercents", gprowiPercents);
		model.addAttribute("qcdrPercents", qcdrPercents);*/
		
		lineChartDataMap.put("uniqueYears", uniqueYears);
		lineChartDataMap.put("claimsPercents", claimsPercents);
		lineChartDataMap.put("ehrPercents", ehrPercents);
		lineChartDataMap.put("registryPercents", registryPercents);
		lineChartDataMap.put("gprowiPercents", gprowiPercents);
		lineChartDataMap.put("qcdrPercents", qcdrPercents);
		lineChartDataMap.put("dataAvailable", dataAvailable);
		
        //return "pqrsparticipatedlinechart";
		return lineChartDataMap;
    }
	

}