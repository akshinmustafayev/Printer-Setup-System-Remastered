<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "AdminAdmins.jsp", "/adminadmins");
	AuthorizeUtil.AuthorizedRedirect(request, response);
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
		<title>PrintDesk - Admin Other settings</title>
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
						<li class="nav-item active">
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
		<div class="row ml-4 mr-4 mt-3 mb-4">
			<div class="col-3 p-4 border-right">
				<p class="mb-2"><em>Home</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/home.png" alt="Home page"/><a class="ml-2 text-body" href="${context}/adminhome">Home page</a></p>
				<p class="mb-2 pt-3"><em>Details</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/printers.png" alt="Printers"/><a class="ml-2 text-body" href="${context}/adminprinters">Printers</a></p>
				<p class="mb-1"><img class="size-16" src="img/admin/branches.png" alt="Branches"/><a class="ml-2 text-body" href="${context}/adminbranches">Branches</a></p>
				<p><img class="size-16" src="img/admin/administrators.png" alt="Administrators"/><a class="ml-2 text-body" href="${context}/adminadmins">Administrators</a></p>
				<p class="mb-1 pt-3"><em>Types</em></p>
				<p><img class="size-16" src="img/admin/printertypes.png" alt="Printer types"/><a class="ml-2 text-body" href="${context}/adminprintertypes">Printer types</a></p>
				<p class="mb-1 pt-3"><em>System</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/help.png" alt="Manual page settings"/><a class="ml-2 text-body" href="${context}/adminmanualpage">Manual page settings</a></p>
				<p class="mb-1"><img class="size-16" src="img/admin/installscript.png" alt="Install Script page settings"/><a class="ml-2 text-body" href="${context}/admininstallscript">Install Script page settings</a></p>
				<p><img class="size-16" src="img/admin/other.png" alt="Other settings"/><a class="ml-2 text-body font-weight-bold" href="${context}/adminother"><u>Other settings</u></a></p>
			</div>
			<div class="col-9 pl-4">
				<div class="d-flex align-items-center border-bottom">
					<img class="size-64 mr-2" src="img/admin/other.png" alt="Other settings"/>
					<h1 class="display-5 mr-4 mt-2 mb-4">Other settings</h1>
				</div>
				
			</div>
		</div>
	</body>
</html>