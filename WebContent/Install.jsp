<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "Error404.jsp", "/error404");
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="SuccessInstallSystem" value='${requestScope["SuccessInstallSystem"]}'/>
<c:set var="ErrorInstallSystem" value='${requestScope["ErrorInstallSystem"]}'/>
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
		<title>PrintDesk - Installation</title>
	</head>
	<body>
		<div class="card ml-4 mr-4 mt-3 mb-4">
			<div class="card-header">
				<div class="d-flex align-items-center">
					<span class="align-middle">Installation</span>
				</div>
			</div>
			<div class="card-body p-0">
				<c:if test = "${SuccessInstallSystem == true}">
					<div class="alert alert-success m-4" role="alert">Success install system!</div>
				</c:if>
				<c:if test = "${ErrorInstallSystem == true}">
					<div class="alert alert-danger m-4" role="alert">Check your DB connection or username or password!<br>If everything is OK, then check config.properties file. First line must be configured like : <u>db.configured=no</u> and <u>printersetupsystem</u> database must be deleted</div>
				</c:if>
				<form class="m-4" method="post" action="install">
					<div class="form-group">
						<label for="inputDBip" class="required">Database IP</label>
						<input name="installdbip" type="text" class="form-control" id="inputDBip" aria-describedby="inputDBipHelp" required>
						<small id="inputDBipHelp" class="form-text text-muted">Input IP of the database</small>
					</div>
					<div class="form-group">
						<label for="inputDBuser" class="required">Database User</label>
						<input name="installdbuser" type="text" class="form-control" id="inputDBuser" aria-describedby="inputDBuserHelp" required>
						<small id="inputDBuserHelp" class="form-text text-muted">Input User of the database</small>
					</div>
					<div class="form-group">
						<label for="inputDBpassword">Database Password</label>
						<input name="installdbpassword" type="text" class="form-control" id="inputDBip" aria-describedby="inputDBpasswordHelp">
						<small id="inputDBpasswordHelp" class="form-text text-muted">Input Password of the database</small>
					</div>
					<br>
					<button type="submit" name="button_installsystem" class="btn btn-primary btn-lg">Create</button>
				</form>
			</div>
		</div>
	</body>
</html>