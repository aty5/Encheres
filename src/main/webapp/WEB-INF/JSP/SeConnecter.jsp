<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchères Connexion</title>
<link rel="stylesheet" href="/WEB-INF/styles.css"> 
</head>

<body>
	<header>ENI-Enchère</header>

	<form action="<%=request.getContextPath()%>/SeConnecter" method="post">
		<label for="identifiant">Identifiant :</label>
		<input type="text" name="identifiant" id="identifiant">
		
		<label for="mot_de_passse">Mot de passe :</label>
		<input type="password" name="mot_de_passe" id="mot_de_passe">
		
		<input type="submit" value="Connexion" name="connecter" id="connecter">
		<input type="checkbox" name="cbxSouvenir" id="cbxSouvenir">
		<label for="cbxSouvenir">Se souvenir de moi</label>
		
		<label> 
			<a href="#">Mot de passe oublié</a>
		</label>
		
		<input type="submit" value="Créer un compte" name="creerCompte"  id="creerCompte">
		
	</form>




</body>
</html>