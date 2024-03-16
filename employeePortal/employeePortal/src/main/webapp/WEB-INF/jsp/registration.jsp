

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Create an account</title>
    <link rel="stylesheet" href="/resources/css/registration.css">
</head>

<body>

<div class="container">
    <h2 class="title">Portify</h2>
    <div class="registration">
        <div class="logo">
            <img src="https://img.freepik.com/premium-vector/online-registration-sign-up-with-man-sitting-near-smartphone_268404-95.jpg" />
        </div>
        <div class="container-1">
            <h2 class="sign">Sign Up</h2>
            <form:form action="/registration" method="post" modelAttribute="userForm">

                <label for="firstName">First Name:</label>
                <form:input type="text" path="firstName" />
                <h5 style="color : red;"><form:errors path="firstName" /></h5>

                <label for="lastName">Last Name:</label>
                <form:input type="text" path="lastName" />
                <h5 style="color : red;"><form:errors path="lastName" /></h5>

                <label for="email">Email:</label>
                <form:input type="text" path="email" />
                <h5 style="color : red;"><form:errors path="email" /></h5>

                <label for="password">Password:</label>
                <div class="password-container">
                    <form:input type="password" path="password" />
                    <span class="toggle-eye" onclick="togglePassword('password')">&#128065;</span>
                </div>
                <h5 style="color : red;"><form:errors path="password" /></h5>

                <label for="conformPassword">Confirm Password:</label>
                <div class="password-container">
                    <form:input type="password" path="conformPassword" />
                    <span class="toggle-eye" onclick="togglePassword('conformPassword')">&#128065;</span>
                </div>
                <h5 style="color : red;"><form:errors path="conformPassword" /></h5>

                <center><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></center>

            </form:form>
            <p>Already existing user ? <a href="/login">Sign in</a></p>
        </div>
    </div>
</div>
<!-- /container -->

<script>
    function togglePassword(id) {
        const passwordInput = document.getElementById(id);
        const eyeIcon = document.querySelector(`[onclick="togglePassword('${id}')"]`);
        const type = passwordInput.type === 'password' ? 'text' : 'password';
        passwordInput.type = type;
        eyeIcon.textContent = type === 'password' ? '👁️' : '👁️‍🗨️';
    }
</script>

</body>
</html>
