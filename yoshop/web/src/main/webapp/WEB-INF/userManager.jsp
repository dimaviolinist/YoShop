<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/jsp-decorator.tld" prefix="decorator"%>

<!-- Admin Page is not for mobile devices ! -->

<!DOCTYPE html >

<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>User Manager</title>

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/normalize.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/demo.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/component.css" />

<link href='http://fonts.googleapis.com/css?family=Cabin+Condensed'
	rel='stylesheet' type='text/css'>


</head>
<body>
	<div class="wrap">
		<div class="header">
			<div class="logo">
				<a href="index.jsp"><img
					src="${pageContext.request.contextPath}/images/logo.png" alt="">
				</a>
			</div>
			<div class="header-right">
				<div class="user-info">
					<ul>
						<li>We are very glad to see you again,</li>
						<li>lead us, reign us, The Greatest ${sessionScope.user}!</li>
					</ul>
				</div>
				<div class="menu">
					<ul class="nav">
						<li><a href="AdminPage">Admin Page</a></li>
						<li><a href="BlackList">BlackList</a></li>
						<li><a href="index.jsp">Home</a></li>
						<li><a href="about.html">About</a></li>
						<li><a href="contact.html">Contact</a></li>
						<li><a href="Account">Account</a></li>
						<li><a href="Logout">Sign out</a></li>
					</ul>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="main">
			<div class="component">
				<table>
					<thead>
						<tr>
							<th>Login</th>
							<th>Name</th>
							<th>Surname</th>
							<th>Email</th>
							<th>Is Banned</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="user-login">${userForManagement.login}</td>
							<td class="user-name">${userForManagement.name}</td>
							<td class="user-surname">${userForManagement.surname}</td>
							<td class="user-email">${userForManagement.email}</td>
							<td class="user-isbanned">${userForManagement.isBannedUntil}</td>
						</tr>
					</tbody>					
				</table>
				<form action="UserManager" method="Post">
					<p>
						<select name="banTerm">
							<option selected value="1">A day</option>
							<option value="3">Three days</option>
							<option value="7">A week</option>
							<option value="30">A month</option>
							<option value="100000">Forever</option>
						</select>
						<input type="hidden" name="userId" value="${userForManagement.id}" />	
						<input type="hidden" name="userLogin" value="${userForManagement.login}"/>				
						<input type="submit" name="doBan" value="${userForManagement.isBannedUntil != null ? 'ADD BAN' : 'BAN'}">
					</p>
				</form>
				<c:if test="${userForManagement.isBannedUntil != null}">
					<form action="UserManager" method="Post">
						<p>
							<input type="hidden" name="userId" value="${userForManagement.id}"/>
							<input type="hidden" name="userLogin" value="${userForManagement.login}"/>
							<input type="submit" name="stopBan" value="STOP BAN">
						</p>
					</form>
				</c:if>
				<c:if test="${requestScope.banListStory!=null}">
					<table>
						<thead>
							<tr>
								<th>Ban start</th>
								<th>Ban end</th>
								<th>Reason</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.banListStory}" var="userBanRecord">
								<tr>									
									<td class="user-login">${userBanRecord.banStart}</td>
									<td class="user-name">${userBanRecord.banEnd}</td>
									<td class="user-surname">${userBanRecord.reason}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>