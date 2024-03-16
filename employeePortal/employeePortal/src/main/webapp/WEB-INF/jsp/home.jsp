<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/home.css">
</head>
<body>
<c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
</c:if>

<div class="container">
	<div class="sidebar">
		<ol>
			<li>
				<img alt="" src="https://png.pngtree.com/png-clipart/20230920/original/pngtree-flat-design-vector-illustration-of-target-icon-with-bow-in-center-png-image_12461727.png" />
				<span>Portify</span>
			</li>
			<li>
				<img alt="" src="https://png.pngtree.com/png-vector/20190119/ourmid/pngtree-calendar-icon-graphic-design-template-vector-png-image_325812.jpg" />
				<span>LMS</span>
			</li>
			<c:if test="${role == 'admin'}">
			    <li>
			    	<a href="/adminDesk"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS87FpEHh7b1IAE-l9u-QyhqzZdT6b3Kr8HSQ&usqp=CAU" /></a>
			        <span>admin</span>
			    </li>
			</c:if>
		</ol>
	</div>
	<div class="content-data">
		<div class="header">
			<div class="header-left">
				${user}
			</div>
			<div class="header-right">
				<img alt="" src="https://png.pngtree.com/png-vector/20190411/ourmid/pngtree-vector-notification-icon-png-image_927192.jpg" />
				<img src="https://icons.veryicon.com/png/o/internet--web/prejudice/user-128.png" />
				<a href="/login">logout</a>
			</div>
		</div>
		<div class="middle">
			<table class="middle-table">
				<tr><th>Total Requests</th></tr>
				<tr><td>${totalRequests }</td></tr>
			</table>
			<table class="middle-table">
				<tr><th>Approved Requests</th></tr>
				<tr><td>${approvedRequests }</td></tr>
			</table>
			<table class="middle-table">
				<tr><th>Rejected Requests</th></tr>
				<tr><td>${rejectedRequests }</td></tr>
			</table>
			<table class="middle-table">
				<tr><th>Pending Requests</th></tr>
				<tr><td>${pendingRequests }</td></tr>
			</table>
		</div>
		<div class="request-title">
			<div>My Requests</div>
			<div class="request-icons">
				<div><a href=""><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPN_wDcLj2JWJb9KSdsoXFW5aqT8aqsVUHAA&usqp=CAU" /></a></div>
				<div><a href="/addLeaveRequest"><img src="https://www.freepnglogos.com/uploads/plus-icon/plus-icon-plus-math-icon-download-icons-9.png"/></a></div>
				<div><a href=""><img src="https://cdn-icons-png.flaticon.com/512/107/107799.png"/></a></div>
				<div><a href=""><img src="https://www.iconpacks.net/icons/2/free-refresh-icon-3104-thumb.png"/></a></div>
			</div>
		</div>
		<div class="bottom">
			<table class="bottom-table">
				<tr>
					<th>Request ID</th>
					<th>Name</th>
					<th>Leave Type</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Applied On</th>
					<th>No.Of.Days</th>
					<th>Status</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${leaveList}" var="leaveList">
				<tr>
					<td>${leaveList.empId }</td>
					<td>${leaveList.empName }</td>
					<td>${leaveList.leaveType }</td>
					<td>${leaveList.startDate }</td>
					<td>${leaveList.endDate }</td>
					<td>${leaveList.appliedOn }</td>
					<td>${leaveList.noOfDays }</td>
					<td>${leaveList.status}</td>
					<td>
						<div>
							<c:choose>
								<c:when test="${leaveList.status !='pending' }">
									<img src="https://cdn1.iconfinder.com/data/icons/essential-21/128/Edit-512.png" height="20px"/>
									<img src="https://icons.veryicon.com/png/o/commerce-shopping/soft-designer-online-tools-icon/delete-77.png" height="20px"/>
								</c:when>
								<c:otherwise>
									<a href="/updateLeaveRequest/${leaveList.empId }"><img src="https://cdn1.iconfinder.com/data/icons/essential-21/128/Edit-512.png" height="20px"/></a>
									<a href="/deleteLeaveRequest/${leaveList.empId }"><img src="https://icons.veryicon.com/png/o/commerce-shopping/soft-designer-online-tools-icon/delete-77.png" height="20px"/></a>
								</c:otherwise>
							</c:choose>
							
						</div>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

</body>
</html>