<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "AdminAdmins.jsp", "/adminadmins");
	AuthorizeUtil.AuthorizedRedirect(request, response);
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="ErrorAdminIDNotNumber" value='${requestScope["ErrorAdminIDNotNumber"]}'/>
<c:set var="ErrorMainAdminCanNotBeDeleted" value='${requestScope["ErrorMainAdminCanNotBeDeleted"]}'/>
<c:set var="ErrorMainAdminCanNotBeEdited" value='${requestScope["ErrorMainAdminCanNotBeEdited"]}'/>
<c:set var="AdminDeleted" value='${requestScope["AdminDeleted"]}'/>
<c:set var="ErrorAdminDelete" value='${requestScope["ErrorAdminDelete"]}'/>
<c:set var="NewAdminCreateSuccess" value='${requestScope["NewAdminCreateSuccess"]}'/>
<c:set var="ErrorNewAdminCreate" value='${requestScope["ErrorNewAdminCreate"]}'/>
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
				<p><img class="size-16" src="img/admin/administrators.png" alt="Administrators"/><a class="ml-2 text-body font-weight-bold" href="${context}/adminadmins"><u>Administrators</u></a></p>
				<p class="mb-1 pt-3"><em>Types</em></p>
				<p><img class="size-16" src="img/admin/printertypes.png" alt="Printer types"/><a class="ml-2 text-body" href="${context}/adminprintertypes">Printer types</a></p>
				<p class="mb-1 pt-3"><em>System</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/help.png" alt="Manual page settings"/><a class="ml-2 text-body" href="${context}/adminmanualpage">Manual page settings</a></p>
				<p><img class="size-16" src="img/admin/installscript.png" alt="Install Script page settings"/><a class="ml-2 text-body" href="${context}/admininstallscript">Install Script page settings</a></p>
			</div>
			<div class="col-9 pl-4">
				<div class="d-flex align-items-center border-bottom">
					<img class="size-64 mr-2" src="img/admin/administrators.png" alt="Administrators"/>
					<h1 class="display-5 mr-4 mt-2 mb-4">Administrators</h1>
				</div>
				<div class="d-flex align-items-center mb-3 bg-light">
					<a class="btn btn-outline-primary m-2" data-toggle="collapse" href="#collapseAdminAdd" role="button" aria-expanded="false" aria-controls="collapseAdminAdd">Add</a>
				</div>
				<div class="collapse mb-4" id="collapseAdminAdd">
					<div class="card card-body bg-light">
					    <form method="post" action="adminadmins">
							<div class="form-group">
								<label for="inputLogin" class="required">Login</label>
								<input name="newadminlogin" type="text" class="form-control" id="inputLogin" aria-describedby="loginHelp" required>
								<small id="loginHelp" class="form-text text-muted">Input full name of the administrator</small>
							</div>
							<div class="form-group">
								<label for="inputFullName" class="required">Full name</label>
								<input name="newadminfullname" type="text" class="form-control" id="inputFullName" aria-describedby="fullNameHelp" required>
								<small id="fullNameHelp" class="form-text text-muted">Input login here</small>
							</div>
							<div class="form-group">
								<label for="inputPassword" class="required">Password</label>
								<input name="newadminpassword" type="text" class="form-control" id="inputPassword" aria-describedby="passwordHelp" required>
								<small id="passwordHelp" class="form-text text-muted">Enter password here. Make sure that password is strong</small>
							</div>
							<button type="submit" name="button_createadmin" class="btn btn-primary">Create</button>
						</form>
					</div>
				</div>
				<c:if test = "${ErrorAdminIDNotNumber == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Administrator ID must be number!</div>
				</c:if>
				<c:if test = "${ErrorMainAdminCanNotBeDeleted == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">You can not delete main Administrator!</div>
				</c:if>
				<c:if test = "${AdminDeleted == true}">
					<div class="alert alert-success mt-3 mb-3" role="alert">Administrator deleted!</div>
				</c:if>
				<c:if test = "${ErrorAdminDelete == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Administrator deletion error!</div>
				</c:if>
				<c:if test = "${NewAdminCreateSuccess == true}">
					<div class="alert alert-success mt-3 mb-3" role="alert">Administrator created!</div>
				</c:if>
				<c:if test = "${ErrorNewAdminCreate == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Administrator creation error! Login must be unique or something went wrong!</div>
				</c:if>
				<table class="table">
					<thead>
						<tr class="d-flex">
							<th class="col-1"></th>
							<th class="col-1"></th>
							<th class="col-5">Login</th>
							<th class="col-5">Full name</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test = "${administrators.size() > 0}">
								<c:forEach begin="0" items="${administrators}" var="administrator">
									<tr class="d-flex">
										<td class="col-1" scope="row">
											<form method="post" action="adminadmins">
												<button type="submit" name="button_deleteadmin" class="btn btn-link m-0 p-0" title="Delete administrator"><span class="oi oi-x"></span></button>
												<input class="hidden" name="deleteadminid" value="${administrator.GetId()}">
											</form>
										</td>
										<td class="col-1">
											<a href="${context}/adminadminsedit?adminid=${administrator.GetId()}">
												<button class="btn btn-link m-0 p-0" title="Edit administrator"><span class="oi oi-wrench"></span></button>
											</a>
										</td>
										<td class="col-5">${administrator.GetLogin()}</td>
										<td class="col-5">${administrator.GetFullName()}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="d-flex">
									<td class="col-1" scope="row"></td>
									<td class="col-1"></td>
									<td class="col-5">(empty)</td>
									<td class="col-5"></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</body>
</html>