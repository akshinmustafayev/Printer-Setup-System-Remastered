<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "AdminInstallScript.jsp", "/admininstallscript");
	AuthorizeUtil.AuthorizedRedirect(request, response);
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="InstallScriptSaved" value='${requestScope["InstallScriptSaved"]}'/>
<c:set var="InstallScriptSaveError" value='${requestScope["InstallScriptSaveError"]}'/>
<c:set var="ScriptExtensionSaved" value='${requestScope["ScriptExtensionSaved"]}'/>
<c:set var="ScriptExtensionSaveError" value='${requestScope["ScriptExtensionSaveError"]}'/>
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
		<title>PrintDesk - Admin Install Script configuration Page</title>
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
				<p><img class="size-16" src="img/admin/printertypes.png" alt="Printer types"/><a class="ml-2 text-body" href="${context}/adminprintertypes">Printer types</a></p>
				<p class="mb-1 pt-3"><em>System</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/help.png" alt="Manual page settings"/><a class="ml-2 text-body" href="${context}/adminmanualpage">Manual page settings</a></p>
				<p><img class="size-16" src="img/admin/installscript.png" alt="Install Script page settings"/><a class="ml-2 text-body font-weight-bold" href="${context}/admininstallscript"><u>Install Script page settings</u></a></p>
			</div>
			<div class="col-9 pl-4">
				<h1 class="display-5 mr-4 mt-2 mb-4">Install Script</h1>
				<p><em>* Make changes to Install Script and press Save button. Variables listed below will be changed to the specified value:</em></p>
				<ul class="mb-5">
					<li class="mb-1"><kbd>%PRINTER_NAME%</kbd> - <em>Printer name</em></li>
					<li class="mb-1"><kbd>%PRINTER_DESCRIPTION%</kbd> - <em>Printer description</em></li>
					<li class="mb-1"><kbd>%PRINTER_SHARE_NAME%</kbd> - <em>Printer shared name on server</em></li>
					<li class="mb-1"><kbd>%PRINTER_ID%</kbd> - <em>Printer ID in the system</em></li>
					<li class="mb-1"><kbd>%PRINTER_BRANCH_ID%</kbd> - <em>Printer branch ID in the system</em></li>
					<li class="mb-1"><kbd>%PRINTER_IP%</kbd> - <em>Printer IP address</em></li>
					<li class="mb-1"><kbd>%PRINTER_VENDOR%</kbd> - <em>Printer vendor</em></li>
					<li class="mb-1"><kbd>%PRINTER_TYPE%</kbd> - <em>Printer type</em></li>
				</ul>
				<c:if test = "${InstallScriptSaved == true}">
					<div class="alert alert-success mt-3" role="alert">Install script saved!</div>
				</c:if>
				<c:if test = "${InstallScriptSaveError == true}">
					<div class="alert alert-danger mt-3" role="alert">Install script not saved. Error!</div>
				</c:if>
				<c:if test = "${ScriptExtensionSaved == true}">
					<div class="alert alert-success mt-3" role="alert">Script extension saved!</div>
				</c:if>
				<c:if test = "${ScriptExtensionSaveError == true}">
					<div class="alert alert-danger mt-3" role="alert">Script extension not saved. Error!</div>
				</c:if>
				<div class="mb-3">
					<form method="post" action="admininstallscript">
						<label for="inputExtensionInput">Script extension</label>
						<input class="form-control mb-4" id="inputExtensionInput" type="text" name="adminscriptextension" value="${scriptextension}">
						<label for="adminscriptInput">Script</label>
						<textarea class="form-control" name="adminscript" id="adminscriptInput" class="w-100" rows="15" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">${installscript}</textarea>
						<button name="savescript_button" type="submit" class="btn btn-outline-primary mt-3">Save</button>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>