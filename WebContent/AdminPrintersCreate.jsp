<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "AdminPrintersCreate.jsp", "/adminprinterscreate");
	AuthorizeUtil.AuthorizedRedirect(request, response);
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="ErrorNewPrinterCreate" value='${requestScope["ErrorNewPrinterCreate"]}'/>
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
		<title>PrintDesk - Admin Create Printer</title>
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
				<p class="mb-1"><img class="size-16" src="img/admin/printers.png" alt="Printers"/><a class="ml-2 text-body font-weight-bold" href="${context}/adminprinters"><u>Printers</u></a> -><img class="size-16" src="img/admin/create.png" alt="Create Printer"/><a class="ml-2 text-body font-weight-bold" href="${context}/adminprinterscreate"><u>Create Printer</u></a></p>
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
					<img class="size-64 mr-2" src="img/admin/create.png" alt="Create printer"/>
					<h1 class="display-5 mr-4 mt-2 mb-4">Create Printer</h1>
				</div>
				<c:if test = "${ErrorNewPrinterCreate == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Printer creation error!</div>
				</c:if>
				<form class="mt-2" method="post" action="adminprinterscreate" enctype="multipart/form-data">
					<div class="form-group">
						<label for="inputPrinterName" class="required">Name</label>
						<c:choose>
							<c:when test = "${printer.GetName() == null}">
								<input name="newprintername" type="text" class="form-control" id="inputPrinterName" aria-describedby="printerNameHelp" required>
							</c:when>
							<c:otherwise>
								<input name="newprintername" type="text" class="form-control" id="inputPrinterName" aria-describedby="printerNameHelp" value="${printer.GetName()}" required>
							</c:otherwise>
						</c:choose>
						<small id="printerNameHelp" class="form-text text-muted">Input full name of the printer</small>
					</div>
					<div class="form-group">
						<label for="inputPrinterDescription">Description</label>
						<c:choose>
							<c:when test = "${printer.GetDescription() == null}">
								<textarea name="newprinterdescription" class="form-control" id="inputPrinterDescription" aria-describedby="printerDescriptionHelp" rows="4"></textarea>
							</c:when>
							<c:otherwise>
								<textarea name="newprinterdescription" class="form-control" id="inputPrinterDescription" aria-describedby="printerDescriptionHelp" rows="4">${printer.GetDescription()}</textarea>
							</c:otherwise>
						</c:choose>
						<small id="printerDescriptionHelp" class="form-text text-muted">Input description of the printer</small>
					</div>
					<div class="form-group">
						<label for="newPrinterBranch" class="required">Branch</label>
						<select name="newprinterbranch" class="form-control" id="newPrinterBranch" required>
							<c:choose>
								<c:when test = "${branches.size() > 0}">
									<c:forEach begin="0" items="${branches}" var="branch">
										<option value="${branch.GetId()}">${branch.GetName()}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="1">None</option>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
					<div class="form-group">
						<label for="inputPrinterIP">IP address</label>
						<c:choose>
							<c:when test = "${printer.GetIp() == null}">
								<input name="newprinterip" type="text" class="form-control" id="inputPrinterIP" aria-describedby="printerIPHelp">
							</c:when>
							<c:otherwise>
								<input name="newprinterip" type="text" class="form-control" id="inputPrinterIP" aria-describedby="printerIPHelp" value="${printer.GetIp()}">
							</c:otherwise>
						</c:choose>
						<small id="printerIPHelp" class="form-text text-muted">Input IP address of the printer</small>
					</div>
					<div class="form-group">
						<label for="inputPrinterVendor">Vendor</label>
						<c:choose>
							<c:when test = "${printer.GetVendor() == null}">
								<input name="newprintervendor" type="text" class="form-control" id="inputPrinterVendor" aria-describedby="printerVendorHelp">
							</c:when>
							<c:otherwise>
								<input name="newprintervendor" type="text" class="form-control" id="inputPrinterVendor" aria-describedby="printerVendorHelp" value="${printer.GetVendor()}">
							</c:otherwise>
						</c:choose>
						<small id="printerVendorHelp" class="form-text text-muted">Input vendor of the printer. For example: HP, Xerox, Canon and etc.</small>
					</div>
					<div class="form-group">
						<label for="newPrinterType" class="required">Printer type</label>
						<select name="newprintertype" class="form-control" id="newPrinterType" required>
							<c:choose>
								<c:when test = "${printerstypes.size() > 0}">
									<c:forEach begin="0" items="${printerstypes}" var="printerstype">
										<option value="${printerstype.GetId()}">${printerstype.GetType()}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="1">None</option>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
					<div class="form-group">
						<label for="inputPrinterServerSharedName">Server shared name</label>
						<c:choose>
							<c:when test = "${printer.GetServerShareName() == null}">
								<input name="newprintersharename" type="text" class="form-control" id="inputPrinterServerSharedName" aria-describedby="printerServerShareNameHelp">
							</c:when>
							<c:otherwise>
								<input name="newprintersharename" type="text" class="form-control" id="inputPrinterServerSharedName" aria-describedby="printerServerShareNameHelp" value="${printer.GetServerShareName()}">
							</c:otherwise>
						</c:choose>
						<small id="printerServerShareNameHelp" class="form-text text-muted">Input server shared name of the printer. For example: \\server01\Printer1</small>
					</div>
					<div class="form-group">
						<label for="inputPrinterLocation">Location</label>
						<c:choose>
							<c:when test = "${printer.GetLocation() == null}">
								<input name="newprinterlocation" type="text" class="form-control" id="inputPrinterLocation" aria-describedby="printerLocationHelp">
							</c:when>
							<c:otherwise>
								<input name="newprinterlocation" type="text" class="form-control" id="inputPrinterLocation" aria-describedby="printerLocationHelp" value="${printer.GetLocation()}">
							</c:otherwise>
						</c:choose>
						<small id="printerLocationHelp" class="form-text text-muted">Input location of the printer</small>
					</div>
					<div class="form-group">
				  		<label for="inputBranchImage">Image</label>
				  		<input name="newprinterimage" type="file" class="form-control-file" id="inputPrinterImage">
					</div>
					<br>
					<button type="submit" name="button_createprinter" class="btn btn-primary btn-lg">Create</button>
				</form>
			</div>
		</div>
	</body>
</html>