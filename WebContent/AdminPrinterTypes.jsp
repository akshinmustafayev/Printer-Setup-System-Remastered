<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "AdminPrinterTypes.jsp", "/adminprintertypes");
	AuthorizeUtil.AuthorizedRedirect(request, response);
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="ErrorTypeIDNotNumber" value='${requestScope["ErrorTypeIDNotNumber"]}'/>
<c:set var="ErrorNoneTypeNotBeDeleted" value='${requestScope["ErrorNoneTypeNotBeDeleted"]}'/>
<c:set var="PrinterTypeDeleted" value='${requestScope["PrinterTypeDeleted"]}'/>
<c:set var="ErrorPrinterTypeDelete" value='${requestScope["ErrorPrinterTypeDelete"]}'/>
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
		<title>PrintDesk - Admin Printer Types</title>
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
				<p class="mb-2"><em>Home</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/home.png" alt="Home page"/><a class="ml-2 text-body" href="${context}/adminhome">Home page</a></p>
				<p class="mb-2 pt-3"><em>Details</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/printers.png" alt="Printers"/><a class="ml-2 text-body" href="${context}/adminprinters">Printers</a></p>
				<p class="mb-1"><img class="size-16" src="img/admin/branches.png" alt="Branches"/><a class="ml-2 text-body" href="${context}/adminbranches">Branches</a></p>
				<p><img class="size-16" src="img/admin/administrators.png" alt="Administrators"/><a class="ml-2 text-body" href="${context}/adminadmins">Administrators</a></p>
				<p class="mb-1 pt-3"><em>Types</em></p>
				<p><img class="size-16" src="img/admin/printertypes.png" alt="Printer types"/><a class="ml-2 text-body font-weight-bold" href="${context}/adminprintertypes"><u>Printer types</u></a></p>
				<p class="mb-1 pt-3"><em>System</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/help.png" alt="Manual page settings"/><a class="ml-2 text-body" href="${context}/adminmanualpage">Manual page settings</a></p>
				<p><img class="size-16" src="img/admin/installscript.png" alt="Install Script page settings"/><a class="ml-2 text-body" href="${context}/admininstallscript">Install Script page settings</a></p>
			</div>
			<div class="col-9 pl-4">
				<div class="d-flex align-items-center border-bottom">
					<img class="size-64 mr-2" src="img/admin/printertypes.png" alt="Printer types"/>
					<h1 class="display-5 mr-4 mt-2 mb-4">Printer Types</h1>
				</div>
				<div class="d-flex align-items-center mb-3 bg-light">
					<a class="btn btn-outline-primary m-2" data-toggle="collapse" href="#collapseAdminAdd" role="button" aria-expanded="false" aria-controls="collapseAdminAdd">Add</a>
				</div>
				<div class="collapse mb-4" id="collapseAdminAdd">
					<div class="card card-body bg-light">
					    <form method="post" action="adminprintertypes">
							<div class="form-group">
								<label for="inputPrinterTypeName">Printer type</label>
								<input name="newprintertype" type="text" class="form-control" id="inputPrinterTypeName" aria-describedby="printerTypeNameHelp" required>
								<small id="printerTypeNameHelp" class="form-text text-muted">Enter printer type here</small>
							</div>
							<button type="submit" name="button_createprintertype" class="btn btn-primary">Create</button>
						</form>
					</div>
				</div>
				<c:if test = "${ErrorTypeIDNotNumber == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Printer type ID must be number!</div>
				</c:if>
				<c:if test = "${ErrorNoneTypeNotBeDeleted == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Printer "None" type can not be deleted!</div>
				</c:if>
				<c:if test = "${PrinterTypeDeleted == true}">
					<div class="alert alert-success mt-3 mb-3" role="alert">Printer type deleted!</div>
				</c:if>
				<c:if test = "${ErrorPrinterTypeDelete == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Error printer type delete!</div>
				</c:if>
				<table class="table">
					<thead>
						<tr class="d-flex">
							<th class="col-1"></th>
							<th class="col-11">Printer type</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test = "${printerstypes.size() > 0}">
								<c:forEach begin="0" items="${printerstypes}" var="printerstype">
									<tr class="d-flex">
										<td class="col-1" scope="row">
											<form method="post" action="adminprintertypes">
												<button type="submit" name="button_printerstype" class="btn btn-link m-0 p-0" title="Delete printer type"><span class="oi oi-x"></span></button>
												<input class="hidden" name="deleteprinterstypeid" value="${printerstype.GetId()}">
											</form>
										</td>
										<td class="col-11">${printerstype.GetType()}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="d-flex">
									<td class="col-1" scope="row"></td>
									<td class="col-11">(empty)</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>