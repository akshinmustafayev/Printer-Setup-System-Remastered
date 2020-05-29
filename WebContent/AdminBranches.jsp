<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.PrinterSetupSystem.misc.AuthorizeUtil" %>
<%
	AuthorizeUtil.UserLoadedJspRedirect(request, response, "AdminBranches.jsp", "/adminbranches");
	AuthorizeUtil.AuthorizedRedirect(request, response);
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="ErrorBranchIDNotNumber" value='${requestScope["ErrorBranchIDNotNumber"]}'/>
<c:set var="ErrorMainBranchCanNotBeDeleted" value='${requestScope["ErrorMainBranchCanNotBeDeleted"]}'/>
<c:set var="BranchDeleted" value='${requestScope["BranchDeleted"]}'/>
<c:set var="ErrorBranchDelete" value='${requestScope["ErrorBranchDelete"]}'/>
<c:set var="NewBranchCreateSuccess" value='${requestScope["NewBranchCreateSuccess"]}'/>
<c:set var="ErrorNewBranchCreate" value='${requestScope["ErrorNewBranchCreate"]}'/>
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
		<title>PrintDesk - Admin Branches configuration Page</title>
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
				<p class="mb-1"><img class="size-16" src="img/admin/branches.png" alt="Branches"/><a class="ml-2 text-body font-weight-bold" href="${context}/adminbranches"><u>Branches</u></a></p>
				<p><img class="size-16" src="img/admin/administrators.png" alt="Administrators"/><a class="ml-2 text-body" href="${context}/adminadmins">Administrators</a></p>
				<p class="mb-1 pt-3"><em>Types</em></p>
				<p><img class="size-16" src="img/admin/printertypes.png" alt="Printer types"/><a class="ml-2 text-body" href="${context}/adminprintertypes">Printer types</a></p>
				<p class="mb-1 pt-3"><em>System</em></p>
				<p class="mb-1"><img class="size-16" src="img/admin/help.png" alt="Manual page settings"/><a class="ml-2 text-body" href="${context}/adminmanualpage">Manual page settings</a></p>
				<p><img class="size-16" src="img/admin/installscript.png" alt="Install Script page settings"/><a class="ml-2 text-body" href="${context}/admininstallscript">Install Script page settings</a></p>
			</div>
			<div class="col-9 pl-4">
				<h1 class="display-5 mr-4 mt-2 mb-4">Branches</h1>
				<div class="d-flex align-items-center mb-3">
					<a class="btn btn-outline-primary mr-2" data-toggle="collapse" href="#collapseBranchAdd" role="button" aria-expanded="false" aria-controls="collapseBranchAdd">Add</a>
				</div>
				<div class="collapse mb-4" id="collapseBranchAdd">
					<div class="card card-body bg-light">
					    <form method="post" action="adminbranches">
							<div class="form-group">
								<label for="inputBranchName">Name</label>
								<input name="newbranchname" type="text" class="form-control" id="inputBranchName" aria-describedby="branchNameHelp" required>
								<small id="branchNameHelp" class="form-text text-muted">Input full name of the branch</small>
							</div>
							<div class="form-group">
								<label for="inputBranchDescription">Description</label>
								<input name="newbranchname" type="text" class="form-control" id="inputBranchDescription" aria-describedby="branchDescriptionHelp" required>
								<small id="branchDescriptionHelp" class="form-text text-muted">Input description of the branch</small>
							</div>
							<button type="submit" name="button_createbranch" class="btn btn-primary">Create</button>
						</form>
					</div>
				</div>
				<c:if test = "${ErrorBranchIDNotNumber == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Branch ID must be number!</div>
				</c:if>
				<c:if test = "${ErrorMainBranchCanNotBeDeleted == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">You can not delete None type branch!</div>
				</c:if>
				<c:if test = "${BranchDeleted == true}">
					<div class="alert alert-success mt-3 mb-3" role="alert">Branch deleted!</div>
				</c:if>
				<c:if test = "${ErrorBranchDelete == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Branch deletion error!</div>
				</c:if>
				<c:if test = "${NewBranchCreateSuccess == true}">
					<div class="alert alert-success mt-3 mb-3" role="alert">Branch created!</div>
				</c:if>
				<c:if test = "${ErrorNewBranchCreate == true}">
					<div class="alert alert-danger mt-3 mb-3" role="alert">Branch creation error!</div>
				</c:if>
				<table class="table">
					<thead>
						<tr class="d-flex">
							<th class="col-1"></th>
							<th class="col-1"></th>
							<th class="col-5">Name</th>
							<th class="col-5">Description</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test = "${branches.size() > 0}">
								<c:forEach begin="0" items="${branches}" var="branch">
									<tr class="d-flex">
										<td class="col-1" scope="row">
											<form method="post" action="adminbranches">
												<button type="submit" name="button_deletebranch" class="btn btn-link m-0 p-0" title="Delete branch"><span class="oi oi-x"></span></button>
												<input class="hidden" name="deletebranchid" value="${branch.GetId()}">
											</form>
										</td>
										<td class="col-1">
											<form method="post" action="adminbranches">
												<button type="submit" name="button_editadmin" class="btn btn-link m-0 p-0" title="Edit branch"><span class="oi oi-wrench"></span></button>
												<input class="hidden" name="editbranchid" value="${branch.GetId()}">
											</form>
										</td>
										<td class="col-5">${branch.GetName()}</td>
										<td class="col-5 table-overflow">${branch.GetDescription()}</td>
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