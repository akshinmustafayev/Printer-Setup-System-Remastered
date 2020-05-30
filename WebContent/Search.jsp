<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "Search.jsp", "/search");
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="shortcut icon" href="img/logo.png" type="image/x-icon">
		<!-- All CSS -->
		<link rel="stylesheet" href="css/printersetupsystem.css">
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/open-iconic-bootstrap.css">
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="js/jquery-3.2.1.min.js"></script>
		<script src="js/popper.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/printersetupsystem.js"></script>
		<title>Printer Setup System - Search</title>
	</head>
	<body>
		<nav class="navbar navbar-expand navbar-light bg-light">
			<a class="navbar-brand ml-4" href="${context}/home"><img src="img/logo.png" alt="Logo" class="printersetupsystem-logo"> PrintDesk</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="${context}/home">Home<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="${context}/search">Search<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${context}/help">Help<span class="sr-only">(current)</span></a>
					</li>
					<c:if test = "${isAdminEntered == true}">
						<li class="nav-item">
							<a class="nav-link" href="${context}/adminhome">Admin</a>
						</li>
					</c:if>
				</ul>
			</div>
			<c:if test = "${isAdminEntered == true}">
				<ul class="navbar-nav ">
				    <li class="nav-item dropdown">
				        <a href="#" class="nav-link dropdown-toggle" id="navDropDownLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><%=session.getAttribute("fullname")%> </a>
				        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navDropDownLink">
				        	<a class="dropdown-item" href="${context}/usersettings">Preferences</a>
				        	<div class="dropdown-divider"></div>
				        	<a class="dropdown-item" href="Logout.jsp">Logout</a>
						</div>
					</li>
				</ul>
			</c:if>
		</nav>
		<div class="card ml-4 mr-4 mt-2 mb-4">
			<div class="card-header">
				<div class="d-flex align-items-center">
					<span class="align-middle">Search</span>
				</div>
			</div>
			<div class="card-body">
				<div class="btn-group btn-group-toggle" data-toggle="buttons">
					<label class="btn btn-outline-primary active">
						<input type="radio" name="options" id="PSSPrintersRadio" onclick="SearchShowPrinters()" checked> Printers
					</label>
					<label class="btn btn-outline-primary">
						<input type="radio" name="options" id="PSSBranchesRadio" onclick="SearchShowBranches()"> Branches
					</label>
				</div>
				<br><br>
				<div id="PSSPrintersContent">
					<span class="align-middle mb-2">Search:</span>
					<br>
					<div class="d-flex align-middle">
						<input id="PSSPrinterName" type="text" class="form-control w-50" placeholder="Printer name" required="required">
						<button type="button" class="btn btn-outline-primary ml-2" onclick="SearchPrinter('PSSPrinterName', 'PSSResultPrinter')">Search</button>
					</div>
					<br>
					<div id="PSSResultPrinter" class="row row-cols-2 row-cols-md-3 m-3 mt-3">
						
					</div>
				</div>
				<div id="PSSBranchesContent" class="hidden">
					<span class="align-middle mb-2">Search:</span>
					<br>
					<div class="d-flex align-middle">
						<input id="PSSBranchName" type="text" class="form-control w-50" placeholder="Branch name" required="required">
						<button type="button" class="btn btn-outline-primary ml-2" onclick="BranchSearch('PSSBranchName', 'PSSResultBranch')">Search</button>
					</div>
					<br>
					<div id="PSSResultBranch" class="row row-cols-2 row-cols-md-3 m-3 mt-3">
						
					</div>
				</div>
			</div>
		</div>
	</body>
</html>