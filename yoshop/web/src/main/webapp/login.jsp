<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/jsp-decorator.tld" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<decorator:decorate filename="WEB-INF/template.jsp">
	<decorator:content placeholder='title'>Yo Shop - Login</decorator:content>
	<decorator:content placeholder="content">
		<div class="register">
			<div class="col_1_of_list span_1_of_list login-left">
				<h3>LOGIN WITH QAUTH</h3>
				<p>Soon.</p>
			</div>
			<div class="col_1_of_list span_1_of_list login-right">
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
				<h3>REGISTERED CUSTOMERS</h3>
				<p>If you have an account with us, please log in.</p>
				<form name="loginForm" method="post" action="Login">
					<div>
						<span>${requestScope.error == 'No login' || requestScope.error == 'Login does not exist' ? requestScope.error : 'Login'}<label>*</label></span>
						 <input type="text" name="login" required="required" tabindex="1" style="color:#888;" value="${requestScope.error == null ||  requestScope.error == 'No login' || requestScope.error == 'Login does not exist' ? 'myusername' : param.login}" onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
					</div>
					<div>
						<span>${requestScope.error == 'No password' || requestScope.error == 'Incorrect password' ? requestScope.error : 'Password'}<label>*</label></span>
						 <input type="password" name="password" required="required" tabindex="2" style="color:#888;" value="${requestScope.error == null ||  requestScope.error == 'No password' || requestScope.error == 'Incorrect password' ? 'mypassword' : param.password}" onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
					</div>
					<a class="news-letter" href="#">
					<label class="checkbox">
					<input type="checkbox"  name="rememberMe"><i> </i>Remember me</label>
					</a>
					<a class="forgot" href="forgot.jsp">Forgot Your Password?</a> 					
					<input type="submit" value="Login">								
				</form>
			</div>
			<div class="clearfix"></div>

		</div>

	</decorator:content>
</decorator:decorate>