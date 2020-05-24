<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "AdminAdmins.jsp", "/adminadmins");
	AuthorizeUtil.AuthorizedRedirect(request, response);
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="ManualPageSaved" value='${requestScope["ManualPageSaved"]}'/>
<c:set var="ManualPageSaveError" value='${requestScope["ManualPageSaveError"]}'/>
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
		<title>PrintDesk - Admin Administrators</title>
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
					<c:choose>
						<c:when test = "${isAdminEntered == true}">
							<li class="nav-item active">
								<a class="nav-link" href="${context}/adminhome">Admin</a>
							</li>
						</c:when>
					</c:choose>
				</ul>
			</div>
			<c:choose>
				<c:when test = "${isAdminEntered == true}">
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
				</c:when>
			</c:choose>
		</nav>
		<div class="row ml-4 mr-4 mt-3 mb-4">
			<div class="col-3 p-4 border-right">
				<p class="mb-2"><em>Details</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/printers.png" alt="Printers"/><a class="ml-2 text-body" href="${context}/adminprinters">Printers</a></p>
				<p class="mb-1"><img class="size-16" src="img/admin/branches.png" alt="Branches"/><a class="ml-2 text-body" href="${context}/adminbranches">Branches</a></p>
				<p><img class="size-16" src="img/admin/administrators.png" alt="Administrators"/><a class="ml-2 text-body font-weight-bold" href="${context}/adminadmins"><u>Administrators</u></a></p>
				<p class="mb-1 pt-3"><em>Types</em></p>
				<p><img class="size-16" src="img/admin/printertypes.png" alt="Printer types"/><a class="ml-2 text-body" href="${context}/adminbranches">Printer types</a></p>
				<p class="mb-1 pt-3"><em>System</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/help.png" alt="Manual page settings"/><a class="ml-2 text-body" href="${context}/adminmanualpage">Manual page settings</a></p>
				<p><img class="size-16" src="img/admin/installscript.png" alt="Install Script page settings"/><a class="ml-2 text-body" href="${context}/admininstallscript">Install Script page settings</a></p>
			</div>
			<div class="col-9 pl-4">
				<h1 class="display-5 mr-4 mt-2 mb-4">Administrators</h1>
				<div class="d-flex align-items-center mb-3">
					<button type="button" class="btn btn-outline-primary mr-2">Add</button>
					<button type="button" class="btn btn-outline-primary mr-2">Remove</button>
				</div>
				<table class="table">
					<thead>
						<tr class="d-flex">
							<th class="col-1"></th>
							<th class="col-1"></th>
							<th class="col-10">Login</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test = "${technicians.size() > 0}">
								<c:forEach begin="0" items="${technicians}" var="technician">
									<tr data-href="${context}/admintechnicianprofile?userid=${technician.GetId()}">
										<td><input type="checkbox"></td>
										<td>${technician.GetFullName()}</td>
										<td>${technician.GetEmail()}</td>
										<td>${technician.GetUserRoleName()}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="d-flex">
									<td class="col-1" scope="row">
										<button type="button" name="btn-help" class="btn btn-link m-0 p-0" title="Delete administrator">
											<span class="oi oi-x"></span>
										</button>
									</td>
									<td class="col-1">
										<button type="button" name="btn-help" class="btn btn-link m-0 p-0" title="Edit administrator">
											<span class="oi oi-wrench"></span>
										</button>
									</td>
									<td class="col-10">(empty)</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>