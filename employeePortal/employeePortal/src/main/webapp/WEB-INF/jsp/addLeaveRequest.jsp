<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Leave Request</title>
<link rel="stylesheet" href="/resources/css/addLeaveRequest.css">
</head>
<body>

<div class="container">
	<div class="header">
		<div></div>
		<div>New Leave Request</div>
		<div><a href="/"><img src="https://w7.pngwing.com/pngs/509/91/png-transparent-cancel-circle-close-cross-delete-exit-remove-arrows-and-universal-actions-icon.png"/></a></div>
	</div>
	<div>
		<form:form action="/addLeaveRequest" method="post" modelAttribute="leaveForm">
			<label for="empId">E-ID:</label>
                <form:input type="text" path="empId" />
                <h5 style="color : red;"><form:errors path="empId" /></h5>

                <label for="empName">Employee Name:</label>
                <form:input type="text" path="empName" />
                <h5 style="color : red;"><form:errors path="empName" /></h5>

                <label for="leaveType">Leave Type:</label>
                <form:input type="text" path="leaveType" />
                <h5 style="color : red;"><form:errors path="leaveType" /></h5>
                
                <label for="leaveType">Leave Type:</label>
				<form:select path="leaveType">
				    <form:option value="" label="-- Select Leave Category --" />
				    <form:option value="planned" label="Planned" />
				    <form:option value="unplanned" label="Unplanned" />
				</form:select>
				<h5 style="color: red;"><form:errors path="leaveType" /></h5>
                
                <label for="startDate">Start Date:</label>
                <form:input type="date" path="startDate" />
                <h5 style="color : red;"><form:errors path="startDate" /></h5>
                
                <label for="endDate">End Date:</label>
                <form:input type="date" path="endDate" />
                <h5 style="color : red;"><form:errors path="endDate" /></h5>
				
				<label for="noOfDays">No. of Days:</label>
                <form:input type="number" path="noOfDays" />
                <h5 style="color : red;"><form:errors path="noOfDays" /></h5>
                
                <label for="reason">Reason:</label>
                <form:input type="text" path="reason" />
                <h5 style="color : red;"><form:errors path="reason" /></h5>
               
                <center><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></center>
		</form:form>
	</div>
</div>
</body>
</html> --%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add New Leave Request</title>
<link rel="stylesheet" href="/resources/css/addLeaveRequest.css">
</head>
<body>

<div class="container">
    <div class="header">
        <div></div>
        <div>New Leave Request</div>
        <div><a href="/"><img src="https://w7.pngwing.com/pngs/509/91/png-transparent-cancel-circle-close-cross-delete-exit-remove-arrows-and-universal-actions-icon.png"/></a></div>
    </div>
    <div>
        <form:form action="/addLeaveRequest" method="post" modelAttribute="leaveForm">
            <table>
                <tr>
                    <td><label for="empId">E-ID:</label></td>
                    <td><form:input type="text" path="empId" /></td>
                    <td><h5 style="color: red;"><form:errors path="empId" /></h5></td>
                </tr>
                <tr>
                    <td><label for="empName">Employee Name:</label></td>
                    <td><form:input type="text" path="empName" /></td>
                    <td><h5 style="color: red;"><form:errors path="empName" /></h5></td>
                </tr>
                <tr>
                    <td><label for="leaveType">Leave Type:</label></td>
                    <td>
                        <form:select path="leaveType">
                            <form:option value="" label="-- Select Leave Category --" />
                            <form:option value="planned" label="Planned" />
                            <form:option value="unplanned" label="Unplanned" />
                        </form:select>
                    </td>
                    <td><h5 style="color: red;"><form:errors path="leaveType" /></h5></td>
                </tr>
                <tr>
                    <td><label for="startDate">Start Date:</label></td>
                    <td><form:input type="date" path="startDate" /></td>
                    <td><h5 style="color: red;"><form:errors path="startDate" /></h5></td>
                </tr>
                <tr>
                    <td><label for="endDate">End Date:</label></td>
                    <td><form:input type="date" path="endDate" /></td>
                    <td><h5 style="color: red;"><form:errors path="endDate" /></h5></td>
                </tr>
                <tr>
                    <td><label for="noOfDays">No. of Days:</label></td>
                    <td><form:input type="number" path="noOfDays" /></td>
                    <td><h5 style="color: red;"><form:errors path="noOfDays" /></h5></td>
                </tr>
                <tr>
                    <td><label for="reason">Reason:</label></td>
                    <td><form:input type="text" path="reason" /></td>
                    <td><h5 style="color: red;"><form:errors path="reason" /></h5></td>
                </tr>
            </table>

            <center><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></center>
        </form:form>
    </div>
</div>
</body>
</html>
