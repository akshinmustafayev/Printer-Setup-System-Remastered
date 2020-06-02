<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "UserSettings.jsp", "/usersettings");
	AuthorizeUtil.AuthorizedRedirect(request, response);
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="ErrorPasswordsAreNotEqual" value='${requestScope["ErrorPasswordsAreNotEqual"]}'/>
<c:set var="PasswordChanged" value='${requestScope["PasswordChanged"]}'/>
<c:set var="ErrorPasswordChange" value='${requestScope["ErrorPasswordChange"]}'/>
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
		<title>PrintDesk - User settings</title>
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
				    <li class="nav-item dropdown active">
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
					<span class="align-middle">User profile settings</span>
				</div>
			</div>
			<div class="card-body p-0">
				<div class="jumbotron card card-image rounded-0 border-0" style="background-image: url(img/admin/usersettings_jumbotron.jpg);">
					<div class="text-white text-sm-left">
						<div>
							<h2 class="card-title font-weight-light"><%=session.getAttribute("fullname")%></h2>
						</div>
					</div>
				</div>
				<form method="post" action="usersettings" class="ml-4 mr-4 mt-1 mb-5">
					<h4>Change password</h4>
					<c:if test = "${ErrorPasswordsAreNotEqual == true}">
						<div class="alert alert-danger mt-3 mb-3" role="alert">Password are not equal!</div>
					</c:if>
					<c:if test = "${PasswordChanged == true}">
						<div class="alert alert-success mt-3 mb-3" role="alert">Password changed!</div>
					</c:if>
					<c:if test = "${ErrorPasswordChange == true}">
						<div class="alert alert-danger mt-3 mb-3" role="alert">Password is not changed!</div>
					</c:if>
					<div class="form-group">
						<label for="inputNewPassword" class="required">New password</label>
						<input name="newpassword" type="password" class="form-control w-25" id="inputNewPassword" aria-describedby="inputNewPasswordHelp" value="${branch.GetName()}" required>
						<small id="inputNewPasswordHelp" class="form-text text-muted">Input new password</small>
					</div>
					<div class="form-group">
						<label for="inputNewPasswordConfirm" class="required">Confirm new password</label>
						<input name="newpasswordconfirm" type="password" class="form-control w-25" id="inputNewPasswordConfirm" aria-describedby="inputNewPasswordConfirmHelp" value="${branch.GetName()}" required>
						<small id="inputNewPasswordConfirmHelp" class="form-text text-muted">Input new password again</small>
					</div>
					<button name="button_changepassword" type="submit" class="btn btn-primary">Change</button>
				</form>
			</div>
		</div>
	</body>
</html>