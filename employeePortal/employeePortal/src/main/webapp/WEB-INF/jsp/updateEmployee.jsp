

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Employee</title>
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
            <h2 class="sign">Edit Employee</h2>
            <form:form action="/updateEmployee" method="post" modelAttribute="userForm">
				<label for="id">E-id:</label>
				<form:input type="text" path="id"/>
				<h5 style="color : red;"><form:errors path="id" /></h5>
				
                <label for="firstName">First Name:</label>
                <form:input type="text" path="firstName" />
                <h5 style="color : red;"><form:errors path="firstName" /></h5>

                <label for="lastName">Last Name:</label>
                <form:input type="text" path="lastName" />
                <h5 style="color : red;"><form:errors path="lastName" /></h5>

                <label for="email">Email:</label>
                <form:input type="text" path="email" />
                <h5 style="color : red;"><form:errors path="email" /></h5>

                <label for="role">Role:</label>
                <form:input type="text" path="role" />
                <h5 style="color : red;"><form:errors path="password" /></h5>


                <center><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></center>

            </form:form>
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
