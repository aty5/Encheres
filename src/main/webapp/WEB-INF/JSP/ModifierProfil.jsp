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
<title>Modifier mon profil</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragments/navBar.jsp"></jsp:include>
	

<form action="ModifierProfil" method="post">

	<!-- <input type="hidden" name="idUtilisateur" value="${utilisateur.idUtilisateur}"/> -->

  
    <label for="pseudo">Pseudo :</label><br>
    <input type="text" id="pseudo" name="pseudo" value="${utilisateur.pseudo}" /><br>
  
  
    <label for="nom">Nom :</label><br>
    <input type="text" id="nom" name="nom" value="${utilisateur.nom}" /><br>
  
    <label for="prenom">Prénom :</label><br>
    <input type="text" id="prenom" name="prenom" value="${utilisateur.prenom}" /><br>
  
    <label for="email">Email :</label><br>
    <input type="email" id="email" name="email" value="${utilisateur.email}" /><br>
  
    <label for="telephone">Téléphone :</label><br>
    <input type="tel" id="telephone" name="telephone" value="${utilisateur.telephone}" /><br>
  
    <label for="rue">Rue :</label><br>
    <input type="text" id="rue" name="rue" value="${utilisateur.rue}" /><br>
  
    <label for="codePostal">Code postal :</label><br>
    <input type="text" id="codePostal" name="codePostal" value="${utilisateur.codePostal}" /><br>
  
    <label for="ville">Ville :</label><br>
    <input type="text" id="ville" name="ville" value="${utilisateur.ville}" /><br>
    
    <!-- <label for="ville">Mot de passe actuel:</label><br>
    <input type="password" id="ancienMdp" name="ancienMdp" /><br>
    
    <label for="ville">Nouveau mot de passe</label><br>
    <input type="password" id="nouveauMdp" name="nouveauMdp" /><br>
    
    <label for="ville">Confirmez le nouveau mot de passe</label><br>
    <input type="password" id="confirmationMdp" name="confirmationMdp" /><br> -->
  
    <input type="submit" value="Enregistrer" onclick="window.location.href='SupprimerCompte?idUtilisateur=${utilisateur.idUtilisateur}'" />
    
     <input type="button" value="Supprimer" onclick="window.location.href='SupprimerCompte?idUtilisateur=${utilisateur.idUtilisateur}'" />
  
</form>


</body>

</html>