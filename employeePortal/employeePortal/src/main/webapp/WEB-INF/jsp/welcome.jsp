<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/welcome.css">
</head>
<body>
<div class="container">
	<div class="container-data">
		<div class="header">
			<h2 class="title">Portify</h2>
			<button type="submit" class="btn-login"><a href="/login">Login</a></button>
		</div>
		<div class="content">
			<div class="description">
				<h1>One Platform for all your systems.</h1>
				<p>Unify, Simplify, and Elevate: All Systems.</p>
				<p class="sol">One Solution</p>
				<a href="/login">
					<button type="submit" class="btn-create-new-account"><a href="/registration">Create a New Account</a></button>
				</a>
			</div>
			<div class="logo">
			<img src="https://www.bitscape.com/media/xqyfmluf/timesheet.png"/>
			</div>
		</div>
	</div>
</div>
</body>
</html>