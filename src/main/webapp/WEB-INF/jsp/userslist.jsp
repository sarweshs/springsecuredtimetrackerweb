<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
<%-- <link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link> --%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="http://getbootstrap.com/2.3.2/assets/css/bootstrap.css"
	rel="stylesheet">
<link
	href="http://getbootstrap.com/2.3.2/assets/css/bootstrap-responsive.css"
	rel="stylesheet">
<!-- <link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
</head>

<body>

	<div class="container">
		<form:form role="form" action="/" modelAttribute="computerConfig"
			method="get">
			<div class="control-group inline">
				<label for="email">User Name</label> <input type="text"
					class="form-control" id="computerName" name="computerName">
				<%-- <form:input path="computerName"/> --%>
			</div>
			<div class="control-group inline">
				<label for="Date Of Data">Date:</label> <input type="text"
					class="form-control" id="dateOfData" name="dateOfData">
			</div>
			<div class="control-group inline">
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</form:form>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Users </span>
			</div>
			
			<div class="table">
				<div class="row-fluid">
					<div class="span1">#</div>
					<div class="span3">User Name</div>
					<div class="span3">Date Of Data</div>
					<div class="span3">Hours</div>
				</div>
				<c:forEach items="${users}" var="user" varStatus="loop">
					<div class="row-fluid accordion-toggle" data-toggle="collapse"
						data-target="#collapse${loop.index + 1}">
						<div class="span1">${loop.index + 1}</div>
						<div class="span3">${user.computerName}</div>
						<div class="span3">${user.dateOfData}</div>
						<div class="span3">${user.totalTime}</div>
					</div>
					<div id="collapse${loop.index + 1}" class="row-fluid collapse in">
					<div class="table">
						<div class="row-fluid">
							<div class="span2">#</div>
							<div class="span2">MAC Address</div>
							<div class="span2">IP Address</div>
							<div class="span2">Start Time</div>
							<div class="span2">End Time</div>
						</div>
						<c:forEach items="${user.listMacs}" var="mac" varStatus="loopmac">
							<div class="row-fluid">
								<div class="span2">${loopmac.index + 1}</div>
								<div class="span2">${mac.macAddress}</div>
								<div class="span2">${mac.ipAddress}</div>
								<div class="span2">${mac.startTime}</div>
								<div class="span2">${mac.endTime}</div>
							</div>
						</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<%-- <table class="table table-hover table-condensed"
				style="border-collapse: collapse;">
				<thead>
					<tr>
						<th>Sl No</th>
						<th>User Name</th>
						<th>Date Of Data</th>
						<th width="100">Hours</th>
						<th width="100"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user" varStatus="loop">
						<tr class="info">
							<td>${loop.index + 1}</td>
							<td>${user.computerName}</td>
							<td>${user.dateOfData}</td>
							<td>${user.totalTime}</td>
							<td><button type="button" class="btn btn-info"
									data-toggle="collapse" data-target="#demo${loop.index + 1}">
									Details</button></td>
						</tr>
						<tr id="#demo${loop.index + 1}" class="collapse in">
							<td colspan="5">
								
									<table class="table table-hover table-condensed">
										<thead>
											<tr>
												<th>Sl No</th>
												<th>Mac Address</th>
												<th>IP Address</th>
												<th width="100">Start Time</th>
												<th width="100">End Time</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${user.listMacs}" var="mac"
												varStatus="loopmac">
												<tr>
													<td>${loopmac.index + 1}</td>
													<td>${mac.macAddress}</td>
													<td>${mac.ipAddress}</td>
													<td>${mac.startTime}</td>
													<td>${mac.endTime}</td>
											</c:forEach>
										</tbody>
									</table>
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table> --%>
		</div>
		<%-- <div class="well">
	 		<a href="<c:url value='/newuser' />">Add New User</a>
	 	</div> --%>
	</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="http://getbootstrap.com/2.3.2/assets/js/jquery.js"></script>
	<script src="http://getbootstrap.com/2.3.2/assets/js/bootstrap.js"></script>

	<script src="http://getbootstrap.com/2.3.2/assets/js/holder/holder.js"></script>
	<script
		src="http://getbootstrap.com/2.3.2/assets/js/google-code-prettify/prettify.js"></script>

	<script src="http://getbootstrap.com/2.3.2/assets/js/application.js"></script>
</body>
</html>