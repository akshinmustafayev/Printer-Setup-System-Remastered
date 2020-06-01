<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "AdminPrinters.jsp", "/adminprinters");
	AuthorizeUtil.AuthorizedRedirect(request, response);
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="ErrorPrinterIDNotNumber" value='${requestScope["ErrorPrinterIDNotNumber"]}'/>
<c:set var="PrinterDeleted" value='${requestScope["PrinterDeleted"]}'/>
<c:set var="ErrorPrinterDelete" value='${requestScope["ErrorPrinterDelete"]}'/>
<c:set var="NewPrinterCreateSuccess" value='${requestScope["NewPrinterCreateSuccess"]}'/>
<c:set var="ErrorNewPrinterCreate" value='${requestScope["ErrorNewPrinterCreate"]}'/>
<c:set var="ErrorPrinterNotNumber" value='${requestScope["ErrorPrinterNotNumber"]}'/>
<c:set var="ErrorPrinterNotFound" value='${requestScope["ErrorPrinterNotFound"]}'/>
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
		<title>PrintDesk - Admin Printers configuration Page</title>
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
				<p class="mb-1"><img class="size-16" src="img/admin/printers.png" alt="Printers"/><a class="ml-2 text-body font-weight-bold" href="${context}/adminprinters"><u>Printers</u></a></p>
				<p class="mb-1"><img class="size-16" src="img/admin/branches.png" alt="Branches"/><a class="ml-2 text-body" href="${context}/adminbranches">Branches</a></p>
				<p><img class="size-16" src="img/admin/administrators.png" alt="Administrators"/><a class="ml-2 text-body" href="${context}/adminadmins">Administrators</a></p>
				<p class="mb-1 pt-3"><em>Types</em></p>
				<p><img class="size-16" src="img/admin/printertypes.png" alt="Printer types"/><a class="ml-2 text-body" href="${context}/adminprintertypes">Printer types</a></p>
				<p class="mb-1 pt-3"><em>System</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/help.png" alt="Manual page settings"/><a class="ml-2 text-body" href="${context}/adminmanualpage">Manual page settings</a></p>
				<p><img class="size-16" src="img/admin/installscript.png" alt="Install Script page settings"/><a class="ml-2 text-body" href="${context}/admininstallscript">Install Script page settings</a></p>
			</div>
			<div class="col-9 pl-4">
				<div class="d-flex align-items-center border-bottom">
					<img class="size-64 mr-2" src="img/admin/printers.png" alt="Printers"/>
					<h1 class="display-5 mr-4 mt-2 mb-4">Printers</h1>
				</div>
				<div class="d-flex align-items-center mb-3 bg-light">
					<a class="btn btn-outline-primary m-2" href="${context}/adminprinterscreate" role="button">Create printer</a>
				</div>
				<c:if test = "${ErrorPrinterIDNotNumber == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Printer ID must be number!</div>
				</c:if>
				<c:if test = "${PrinterDeleted == true}">
					<div class="alert alert-success mt-3 mb-3" role="alert">Printer deleted!</div>
				</c:if>
				<c:if test = "${ErrorPrinterDelete == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Printer deletion error!</div>
				</c:if>
				<c:if test = "${NewPrinterCreateSuccess == true}">
					<div class="alert alert-success mt-3 mb-3" role="alert">Printer created!</div>
				</c:if>
				<c:if test = "${ErrorNewPrinterCreate == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Printer creation error!</div>
				</c:if>
				<c:if test = "${ErrorPrinterNotNumber == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Printer ID is not number!</div>
				</c:if>
				<c:if test = "${ErrorPrinterNotFound == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Printer not found!</div>
				</c:if>
				<table class="table">
					<thead>
						<tr class="d-flex">
							<th class="col-1"></th>
							<th class="col-1"></th>
							<th class="col-4">Name</th>
							<th class="col-3">Branch</th>
							<th class="col-3">IP</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test = "${printers.size() > 0}">
								<c:forEach begin="0" items="${printers}" var="printer">
									<tr class="d-flex">
										<td class="col-1" scope="row">
											<form method="post" action="adminprinters">
												<button type="submit" name="button_deleteprinter" class="btn btn-link m-0 p-0" title="Delete printer"><span class="oi oi-x"></span></button>
												<input class="hidden" name="deleteprinterid" value="${printer.GetId()}">
											</form>
										</td>
										<td class="col-1">
											<a href="${context}/adminprintersedit?printerid=${printer.GetId()}">
												<button type="submit" name="button_editprinter" class="btn btn-link m-0 p-0" title="Edit printer"><span class="oi oi-wrench"></span></button>
											</a>
										</td>
										<td class="col-4 table-overflow">${printer.GetName()}</td>
										<td class="col-3 table-overflow">${printer.GetBranchName()}</td>
										<td class="col-3 table-overflow"><a href="http://${printer.GetIp()}" target="_blank">${printer.GetIp()}</a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="d-flex">
									<td class="col-1" scope="row"></td>
									<td class="col-1"></td>
									<td class="col-4">(empty)</td>
									<td class="col-3"></td>
									<td class="col-3"></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>