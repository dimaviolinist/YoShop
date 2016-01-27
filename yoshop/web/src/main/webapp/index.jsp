<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/jsp-decorator.tld" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<decorator:decorate filename="WEB-INF/template.jsp">
	<decorator:content placeholder='title'>Yo Shop</decorator:content>
	<decorator:content placeholder="content">
		<div class="sidebar">
			<div class="s-main">
				<div class="s_hdr">
					<h2>Categories</h2>
				</div>
				<div class="text1-nav">
					<ul>
						<li><a href="">The standard chunk of Lorem gfd</a></li>
						<li><a href="">Duis a augue ac libero euismodf</a></li>
						<li><a href="">The standard chunk of Lorem Ips</a></li>
						<li><a href="">Duis a augue ac libero euismodd</a></li>
						<li><a href="">The standard chunk of Lorem gfd</a></li>
						<li><a href="">Duis a augue ac libero euismodf</a></li>
						<li><a href="">The standard chunk of Lorem Ips</a></li>
						<li><a href="">Duis a augue ac libero euismodd</a></li>
						<li><a href="">The standard chunk of Lorem gfd</a></li>
						<li><a href="">Duis a augue ac libero euismodf</a></li>
						<li><a href="">The standard chunk of Lorem Ips</a></li>
						<li><a href="">Duis a augue ac libero euismodd</a></li>
						<li><a href="">The standard chunk of Lorem Ips</a></li>
						<li><a href="">Duis a augue ac libero euismodd</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="cnt-main btm">
				<div class="section group">					
					<c:if test="true">Hello world!</c:if>					
					<p>${requestScope.error ? 'mypasswordERORRRR' : param.password}</p>
					<p>${requestScope.error ?  param.password : 'mypassword'}</p>
				</div>
			</div>
		</div>
	</decorator:content>
</decorator:decorate>