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
 
</head> 

<body>

<div class="HypothesisScreen">   

<form id="display_chart" name="select_program" method="post" action="${pageContext.request.contextPath}/mapandchartdisplay">
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
		<td><input type="submit" value="Display" /> </td>
	</tr>

	</table>
</form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
