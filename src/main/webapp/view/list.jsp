<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Employee</title>
	<link rel="stylesheet" href="${path}/webjars/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong><span class="glyphicon glyphicon-list List"></span> Employee List</strong>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-condensed table-hover table-striped">
							<thead>
								<tr>
									<th>Name</th>
									<th>Date Of Birth</th>
									<th>Salary</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${employees.size() > 0}">
										<c:forEach items="${employees}" var="employee">
											<tr>
												<td>${employee.name}</td>
												<td>${employee.dateOfBirth}</td>
												<td>${employee.salary}</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="3">No Employee Available.</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
							<tfoot>
								<tr align="right">
									<td colspan="3">
										<a href="/" class="btn btn-default btn-xs">
											<span class="glyphicon glyphicon-plus-sign"></span> Employee
										</a>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>