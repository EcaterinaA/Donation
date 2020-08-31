<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ro.ecaterina.donation.model.User"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		User u1 = (User) request.getAttribute("user");//Am facut cast deoarece getAtribute returna un obiect de tip OBJECT
	out.println("Utilizatorul cu id-ul " + u1.getId() + " este " + u1);
	%>
<br>
<hr>
	<table>
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Phone</th>
			<th>Email</th>
		</tr>
		<c:forEach items= "${listUser}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.phone}</td>
				<td>${user.email}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>