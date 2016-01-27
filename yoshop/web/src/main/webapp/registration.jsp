<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/jsp-decorator.tld" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<decorator:decorate filename="WEB-INF/template.jsp">
	<decorator:content placeholder='title'>Yo Shop - Registration</decorator:content>
	<decorator:content placeholder="content">
		<div class="register">
			<script type="text/javascript">
				function inputFocus(i) {
					if (i.value == i.defaultValue) {
						i.value = "";
						i.style.color = "#000";
					}
				}
				function inputBlur(i) {
					if (i.value == "") {
						i.value = i.defaultValue;
						i.style.color = "#888";
					}
				}
			</script>
			<form name="registrationForm" method="post" action="Registration">
				<div class="register-top-grid">
					<h3>PERSONAL INFORMATION</h3>
					<div>
						<span>${requestScope.error == 'No name' ? requestScope.error : 'First Name'}<label>*</label></span>
						<input type="text"name="name" required="required" style="color:#888;" value="${requestScope.error == null || requestScope.error == 'No name' ? 'myname' : param.name}" onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
					</div>
					<div>
						<span>Surname<label>*</label></span>
						 <input type="text"name="surname" style="color:#888;" value="${requestScope.error == null ? 'mysurname' : param.surname}" onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
					</div>
					<div>
						<span>${requestScope.error == 'No email' || requestScope.error == 'Email is already used' ? requestScope.error : 'Email Address'}<label>*</label></span>
						 <input type="email" name="email" required="required" style="color:#888;" value="${requestScope.error == null ||  requestScope.error == 'No email' || requestScope.error == 'Email is already used' ? 'myemail' : param.email}" onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
					</div>				
				</div>
				<div class="clear"> </div>
					   <a class="news-letter" href="#">
						 <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i>Sign Up for Newsletter</label>
					   </a>					 
				<div class="register-bottom-grid">
					<h3>LOGIN INFORMATION</h3>
					<div>
						<span>${requestScope.error == 'No login' || requestScope.error == 'Login is already exist' ? requestScope.error : 'Login'}<label>*</label></span>
						<input type="text" name="login" required="required" style="color:#888;" value="${requestScope.error == null ||  requestScope.error == 'No login' || requestScope.error == 'Login is already exist' ? 'myusername' : param.login}" onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
					</div>
					<div>
						<span>${requestScope.error == 'No password' ? requestScope.error : 'Password'}<label>*</label></span>
						 <input type="password" name="password" required="required" style="color:#888;" value="${requestScope.error == null ||  requestScope.error == 'No password' ? 'mypassword' : param.password}" onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
					</div>					
				</div>				
			</form>
			<div class="clear"> </div> 
				<div class="register-but">
				<form>
				<input type="submit" value="Register">
				<div class="clear"> </div>
				</form>
			</div>	
		</div>
	</decorator:content>
</decorator:decorate>

