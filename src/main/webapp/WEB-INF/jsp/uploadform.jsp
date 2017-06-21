<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>INTERACTIVE MAPS</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/docfile.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/es6-shim/0.33.3/es6-shim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/systemjs/0.19.20/system-polyfills.js"></script>
    <script src="https://code.angularjs.org/2.0.0-beta.6/angular2-polyfills.js"></script>
    <script src="https://code.angularjs.org/tools/system.js"></script>
    <script src="https://code.angularjs.org/tools/typescript.js"></script>
    <script src="https://code.angularjs.org/2.0.0-beta.6/Rx.js"></script>
    <script src="https://code.angularjs.org/2.0.0-beta.6/angular2.dev.js"></script>
    
	
	
</style>
 
</head>
<body>
<div id="header">

 <h3> <b> INTERACTIVE MAPS</b> <h3> 
    
    
  </div>
 
    <div id="updates" class="boxed">
      <h2 class="title">Upload excel data screen</h2>
      <div class="content">
      
	  <form:form action="/admin/documentupload/" modelAttribute="documentFileUpload"
			enctype="multipart/form-data" method="post">			
			<p>
				Document Provider :
				<form:input type="file" path="provider" size="40"/>
			</p>
			<div>
				<input type="submit" value="Upload">
				<input type="reset" value="Reset">
			</div>
			<p>
				Document Specialty :
				<form:input type="file" path="specialty" size="40"/>
			</p>
			<div>
				<input type="submit" value="Upload">
				<input type="reset" value="Reset">
			</div>
	 </form:form>

       </div>
    </div>
  </div>
   <div id="footer">
  <p id="legal">Copyright &copy; 2007 Ornamental. All Rights Reserved. Designed by <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>.</p>
  <p id="links"><a href="#">Home</a> | <a href="#">Terms of Use</a></p>
</div>
</body>
</html>
