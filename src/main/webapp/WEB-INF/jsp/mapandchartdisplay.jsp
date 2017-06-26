<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title>INTERACTIVE MAPS</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />

   
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">      
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Chart.bundle.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
</head> 

<body>

<div class="HypothesisScreen">   

	<table>

	<tr>
		<td><label for="yearLookUpId">Optional Year : </label> </td>
		<td>
		<select id="yearLookUpId" name="yearLookUpId">
			<option value="">Select</option>
			<c:forEach items="${yearLookups}" var="yearLookUp">
			<option value="${yearLookUp.id}" ${yearLookUp.id == yearLookUpId ? 'selected="selected"' : ''}>${yearLookUp.yearName}</option>
			</c:forEach>
		</select>
		</td>
	</tr>

	<tr>
		<td><label for="reportingOptionLookupId">Reporting Option : </label> </td>
		<td>
		<select id="reportingOptionLookupId" name="reportingOptionLookupId">
			<option value="">Select</option>
			<c:forEach items="${reportingOptionLookups}" var="reportingOptionLookup">
			<option value="${reportingOptionLookup.id}" ${reportingOptionLookup.id == reportingOptionLookupId ? 'selected="selected"' : ''}>${reportingOptionLookup.reportingOptionName}</option>
			</c:forEach>
		</select>
		</td>
	</tr>
	
	<tr>
		<td><label for="parameterLookupId">Parameter Name : </label> </td>
		<td>
		<select id="parameterLookupId" name="parameterLookupId">
			<option value="">Select</option>
			<c:forEach items="${parameterLookups}" var="parameterLookup">
			<option value="${parameterLookup.id}" ${parameterLookup.id == parameterLookupId ? 'selected="selected"' : ''}>${parameterLookup.parameterName}</option>
			</c:forEach>
		</select>
		</td>
	</tr>

	<tr>
		<td><label for="reportTypeId">Report Type :</label> </td>
		<td>
		<select id="reportTypeId" name="reportTypeId">
			<option value="">Select</option>
			<c:forEach items="${reportTypes}" var="reportType">
				<option value="${reportType}">${reportType}</option>
			</c:forEach>
		</select>
		</td>
	</tr>

	<tr>
		<td></td>
		<td>
		<input type="submit" id="displayreport" />
		</td>
	</tr>

	</table>

</div>

    <div id="container" style="width: 75%;">
        <canvas id="canvas"></canvas>
    </div>
    
    <div id ="barChartDisplay"></div>
    
    <div id ="lineChartDisplay"></div>
    

<script>
	var btn = document.getElementById("displayreport");
	var barChartData =null;
	var lineChartData =null;
	
	btn.addEventListener("click", function(){
		
	    var reportTypeSelectedVal = $("#reportTypeId option:selected").text();
	    var yearLookUpSelectedVal = $("#yearLookUpId option:selected").text();
	    var reportingOptionLookupSelectedVal = $("#reportingOptionLookupId option:selected").text();
	    var parameterLookupSelectedVal = $("#parameterLookupId option:selected").text();
	    
	    if (reportTypeSelectedVal == "Bar Chart"){
	    	var url = 'http://localhost:8080/barChart/year/'+yearLookUpSelectedVal+'/reportingOption/'+reportingOptionLookupSelectedVal;
	    } 
	    if (reportTypeSelectedVal == "Line Chart"){
	    	var url = 'http://localhost:8080/lineChart/parameter/'+parameterLookupSelectedVal;
	    } 
	    if (reportTypeSelectedVal == "Map"){
	    	<!-- TODO -->
	    } 
		
	    var ourRequest = new XMLHttpRequest();
	    ourRequest.open('GET', url);
	    ourRequest.onload = function(){
	    	
	    	
		    if (reportTypeSelectedVal == "Bar Chart")
		    {
		    	barChartData = JSON.parse(ourRequest.responseText);
		        console.log(barChartData);
		        var barChartDataAvail = barChartData.dataAvailable;
		        
		    	<!-- BAR CHART :: JAVA SCRIPT ###### START  -->
		    	var barChartData = {
		                labels: barChartData.parameters,
		                datasets: [{
		                    label: 'YES',
		    				backgroundColor: "rgba(0,0,128,.5)",
		    				borderColor: "rgba(0,0,128,.5)",
		                    borderWidth: 1,
		                    data: barChartData.yesPercents
		                }, {
		                    label: 'NO',
		    				backgroundColor: "rgba(255,0,0,.7)",
		    				borderColor: "rgba(255,0,0,.7)",
		                    borderWidth: 1,
		                    data: barChartData.noPercents
		                }]

		            };
		            
		            var optionsInfo = {
		                    responsive: true,
		                    title:{
		                        display:true,
		                        text:'Base Year(2012) to Option Year 3(2015) REGISTRY Reporting Option Eligible Professionals Summary'
		                    },
		                    legend: {
		                        position: 'bottom',
		                    },
		                    tooltips: {
		                        mode: 'index',
		                        intersect: true,
		                    },
		                    hover: {
		                        mode: 'nearest',
		                        intersect: true,
		                    },
		                    scales: {
		                        xAxes: [{
		                            display: true,
		                            scaleLabel: {
		                                display: true,
		                                labelString: 'REPORTING OPTION'
		                            },
		    	                    ticks: {
		    	                        display: true,
		    	                        beginAtZero:true
		    	                    }
		                        }],
		                        yAxes: [{
		                            display: true,
		                            scaleLabel: {
		                                display: true,
		                                labelString: 'PERCENT'
		                            },
		    	                    ticks: {
		    	                    	callback: function(label, index, labels) {
		    	                    		return label + ' %';
		    	                    	},
		    	                        display: true,
		    	                        beginAtZero:true
		    	                    }
		                        }]
		                    }
		            }

		            var barconfig = {
		            	type: 'bar',
		            	data: barChartData,
		                options: optionsInfo
		            };
		        <!-- BAR CHART :: JAVA SCRIPT ###### END  -->
		        
		    } <!-- Bar Chart If Logic Ends-->
		    
		    if (reportTypeSelectedVal == "Line Chart")
		    {
		    	lineChartData = JSON.parse(ourRequest.responseText);
		        console.log(lineChartData);
		        
		        <!-- LINE CHART :: JAVA SCRIPT ###### START  -->
		        var titletext = 'Base Year to Option Year 3 ' + 'Mental Health HPSA' + ' Percentage Summary'
		    	var yaxeslabelstring = 'Percent of EPs & GPROs in ' +'Mental Health HPSA'
		    
		        var lineconfig = {
		            type: 'line',
		            data: {
		                labels: lineChartData.uniqueYears,
		                datasets: [{
		                    label: "CLAIMS",
		                    fill: false,
		                    backgroundColor: window.chartColors.red,
		                    borderColor: window.chartColors.red,
		                    data: lineChartData.claimsPercents,
		                }, {
		                    label: "EHR",
		                    fill: false,
		                    backgroundColor: window.chartColors.green,
		                    borderColor: window.chartColors.green,
		                    data: lineChartData.ehrPercents,
		                }, {
		                    label: "Registry",
		                    fill: false,
		                    backgroundColor: window.chartColors.orange,
		                    borderColor: window.chartColors.orange,
		                    data: lineChartData.registryPercents,
		                }, {
		                    label: "GPROWI",
		                    fill: false,
		                    backgroundColor: window.chartColors.purple,
		                    borderColor: window.chartColors.purple,
		                    data: lineChartData.gprowiPercents,
		                }, {
		                    label: "QCDR",
		                    fill: false,
		                    backgroundColor: window.chartColors.brown,
		                    borderColor: window.chartColors.brown,
		                    data: lineChartData.qcdrPercents,
		                }]
		            },
		            options: {
		                responsive: true,
		                title:{
		                    display:true,
		                    text: titletext
		                },
		                legend: {
		                    position: 'bottom',
		                },
		                tooltips: {
		                    mode: 'index',
		                    intersect: false,
		                },
		                hover: {
		                    mode: 'nearest',
		                    intersect: true
		                },
		                scales: {
		                    xAxes: [{
		                        display: true,
		                        scaleLabel: {
		                            display: true,
		                            labelString: 'YEAR'
		                        }
		                    }],
		                    yAxes: [{
		                        display: true,
		                        scaleLabel: {
		                            display: true,
		                            labelString: yaxeslabelstring
		                        },
			                    ticks: {
			                    	callback: function(label, index, labels) {
			                    		return label + ' %';
			                    	},
			                        display: true
			                    }
		                    }]
		                }
		            }
		        };
		        <!-- LINE CHART :: JAVA SCRIPT ###### END  -->
		        
		    } <!-- Line Chart If Logic Ends-->
	        
	        
	        
            var ctx = document.getElementById("canvas").getContext("2d");
            
            <!-- Different Chart Display :: START -->
    	    if (reportTypeSelectedVal == "Bar Chart"){
    	    	document.getElementById("lineChartDisplay").innerHTML = "";
    	    	if (barChartDataAvail == "YES") {
    	    		document.getElementById("barChartDisplay").innerHTML = new Chart(ctx, barconfig);
    	    	}
    	    	if (barChartDataAvail == "NO") {
    	    		document.getElementById("barChartDisplay").innerHTML = "NO DATA AVAILABLE";
    	    	}
    	    	
    	    } 
    	    if (reportTypeSelectedVal == "Line Chart"){
    	    	document.getElementById("lineChartDisplay").innerHTML = new Chart(ctx, lineconfig);
    	    	document.getElementById("barChartDisplay").innerHTML = "";
    	    } 
    	    if (reportTypeSelectedVal == "Map"){
    	    	<!-- TODO for Map-->
    	    } 
            <!-- Different Chart Display :: END -->
	        
	    };
	    
	    ourRequest.send();
	
	});
	
	
	function renderHTML(data) {
	};
</script>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
