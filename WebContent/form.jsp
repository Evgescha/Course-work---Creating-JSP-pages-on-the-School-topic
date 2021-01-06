<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>School Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> School Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Schools</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${entity != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${entity == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${entity != null}">
            			Edit School
            		</c:if>
						<c:if test="${entity == null}">
            			Add New School
            		</c:if>
					</h2>
				</caption>

				<c:if test="${entity != null}">
					<input type="hidden" name="id" value="<c:out value='${entity.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>School Year of foundation</label> <input type="number" min="1500" max="2021"
						value="<c:out value='${entity.yearOfFoundation}' />" class="form-control"
						name="yearOfFoundation" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>School Name</label> <input type="text"
						value="<c:out value='${entity.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>School Adres</label> <input type="text"
						value="<c:out value='${entity.adres}' />" class="form-control"
						name="adres" required="required">
				</fieldset>


				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
