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

<title>Admin Page</title>

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
						<c:if test="${requestScope.noUsers==null}">							
						<c:forEach items="${userList}" var="user"> 
							<tr onclick="location.href='${pageContext.request.contextPath}/UserManager?user=${user.login}'">
								<td class="user-login">${user.login}</td>
								<td class="user-name">${user.name}</td>
								<td class="user-surname">${user.surname}</td>
								<td class="user-email">${user.email}</td>
								<td class="user-isbanned">${user.isBannedUntil}</td>
							</tr>						
						</c:forEach>
						</c:if>						
					</tbody>
				</table>
				<c:if test="${requestScope.noUsers!=null}">	
					<h1>${requestScope.noUsers}</h1>
				</c:if>		
			</div>
		</div>
	</div>
</body>
</html>