<html>

<body>
<h2 center = "center" > Donez-Primesc </h2>
	
	<form action="users" method="get"> <!-- "action"= contollerul care manageriaza parametrii din formular -->
	<input type= "text" name="uid">
	<input type="submit" value="Paseaza"><!--Apas butonul "Paseaza"=> actiune:afisare -->
	</form>
	
		<button onclick="location.href = '${pageContext.request.contextPath}/register.jsp';" id="myButton" >Autentificare</button>
	
</body>


</html>
