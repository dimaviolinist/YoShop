<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/jsp-decorator.tld" prefix="decorator"%>

<!DOCTYPE html >

<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title><decorator:placeholder name='title' /></title>

<!-- Custom CSS -->
							
<link href="<c:url value="/css/style.css" />"
	rel="stylesheet" type="text/css">

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
						<c:if test="${sessionScope.user == null}">
						<li>Hello!</li>
						<li>Welcome!</li>
						</c:if>	
						<c:if test="${sessionScope.user != null}">
						<li>We are glad to see you again,</li>
						<li>${sessionScope.user}</li>
						</c:if>	
					</ul>
				</div>
				<div class="menu">
					<ul class="nav">
						<c:if test="${sessionScope.userRole == 'admin'}">
						<li><a href="AdminPage">Admin Page</a></li>
						<li><a href="BlackList">Black List Page</a></li>							
						</c:if>							
						<li><a href="index.jsp">Home</a></li>
						<li><a href="about.html">About</a></li>						
						<li><a href="contact.html">Contact</a></li>
						<li><a href="Order">Card: </a></li>												
						<c:if test="${sessionScope.user == null}">
						<li><a href="registration.jsp">Sign up</a></li>
						<li><a href="login.jsp">Sign in</a></li>
						</c:if>	
						<c:if test="${sessionScope.user != null}">
						<li><a href="Account">Account</a></li>
						<li><a href="Logout">Sign out</a></li>
						</c:if>											
					</ul>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="main">			
			<decorator:placeholder name="content" />
		</div>
	</div>
</body>
</html>