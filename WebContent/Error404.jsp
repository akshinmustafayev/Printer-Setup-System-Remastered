<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "Error404.jsp", "/error404");
	AuthorizeUtil.AuthorizedRedirectHome(request, response);
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
		<title>PrintDesk - Error 404</title>
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
					<li class="nav-item">
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
		<div class="card ml-4 mr-4 mt-3 mb-4">
			<div class="card-header">
				<div class="d-flex align-items-center">
					<span class="align-middle">Error 404</span>
				</div>
			</div>
			<div class="card-body p-0">
				<div class="mx-auto w-75 text-center">
					<h1 class="display-1">Error 404!</h1>
					<h1 class="display-4">Sorry! Requested page could not be found!</h1>
					<img class="mb-4 mt-2" src="img/error/error404.gif">
				</div>
			</div>
		</div>
	</body>
</html>