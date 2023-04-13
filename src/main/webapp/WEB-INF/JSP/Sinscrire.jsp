<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="fr.eni.enchere.messages.LecteurMessage" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>

	<h2>Mon profil</h2>
	
		<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger" role="alert">
			  <strong>Erreur de saisie, veuillez recommencer!</strong>
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
		</c:if>
	
	<form action="Sinscrire" method="post">

		<label for="pseudo">Pseudo:</label><br> 
		<input type="text" id="pseudo" name="pseudo" value="${pseudo}"><br> 
		
		
		<label for="nom">Nom:</label><br>
		<input type="text" id="nom" name="nom" value="${nom}"><br> 
		
		<label for="prenom">Prénom:</label><br> 
		<input type="text" id="prenom" name="prenom" value="${prenom}"><br> 
			
		<label for="email">Email:</label><br>
		<input type="email" id="email" name="email"  value="${email}"><br> 
		
		<label for="telephone">Téléphone:</label><br> 
		<input type="text" id="telephone" name="telephone" value="${telephone}"><br> 
			
		<label for="rue">Rue:</label><br>
		<input type="text" id="rue" name="rue" value="${rue}"><br>		
		
		<label for="code_postal">Code Postal:</label><br> 
		<input type="text" id="code_postal" name="code_postal" value="${code_postal}"><br> 
		
		<label for="ville">Ville:</label><br>
		<input type="text" id="ville"name="ville" value="${ville}"><br> 
			
		<label for="mot_de_passe1">Password:</label><br>
		<input type="password" id="mot_de_passe1" name="mot_de_passe1"><br>

		<label for="mot_de_passe2">Password:</label><br> 
		<input type="password" id="mot_de_passe2" name="mot_de_passe2"><br><br> 
		
		
		<input type="submit" value="Créer"> 
		<input type="reset" value="Annuler">
		
	</form>
	

</body>
</html>