<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>Log in with your account</title>
	      <link rel="stylesheet" href="/resources/css/login.css">
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="container">
	<div class="title">
		portify
	</div>
	<div class="container-1">
		<div class="signIn">
		     <form method="POST" action="${contextPath}/login" class="form-signin" >
		        <div class="signIn-header">
			        <p>Welcome Back!</p>
			        <h2>Sign In</h2>
		        </div>
		
		        <div class="form-group ${error != null ? 'has-error' : ''}">
		            <span >${message}</span></br>
		            <label for="username">User Name:</label>
					<input name="username" type="text" 
		                   autofocus="autofocus" /><br><br>
		            
		            <label for="password">Password:</label>
		            <input name="password" type="password" /></br>
		            <span>${error}</span>
		            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</br>
		            <center><button type="submit">Log In</button><center>
		            <h4 >New User? <a href="${contextPath}/registration">  Sign Up</a></h4>
		        </div>
		
		    </form>
		</div>
		<div class="logo">
		<img class="logo-img" src="https://img.freepik.com/free-vector/mobile-login-concept-illustration_114360-83.jpg?size=626&ext=jpg&ga=GA1.2.2008743219.1686029620&semt=sph" height="450"/>
		</div>
	</div>

</div>
</body>
</html>