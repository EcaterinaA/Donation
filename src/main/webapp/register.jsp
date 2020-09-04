<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Register page</title>
</head>

<body>
<h1>Inregistrare utilizator</h1>
<hr/>
</body>
<form action="users" method="POST"> <!--Trimit catre controller -->
		Nume:<input type= "text" name="lastName"><br><br>
		Prenume:<input type= "text" name="firstName"><br><br>
		Parola:<input type= "password" name="password"><br><br>
		Telefon:<input type= "text" name="phone"><br><br>
		E-mail:<input type= "text" name="email"><br><br>
	
	<input type="submit" value="Inregistreaza-te">
	</form>
	

</html>