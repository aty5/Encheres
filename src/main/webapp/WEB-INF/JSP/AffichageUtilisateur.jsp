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
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/fragments/navBar.jsp"></jsp:include>
	
  <h1>Afficher utilisateur</h1>
   <c:if test="${not empty utilisateur}">
      <table>
         <tr>
            <td>Pseudo :</td>
            <td>${utilisateur.pseudo}</td>
         </tr>
         <tr>
            <td>Nom :</td>
            <td>${utilisateur.nom}</td>
         </tr>
         <tr>
            <td>Prénom :</td>
            <td>${utilisateur.prenom}</td>
         </tr>
         <tr>
            <td>Email :</td>
            <td>${utilisateur.email}</td>
         </tr>
         <tr>
            <td>Téléphone :</td>
            <td>${utilisateur.telephone}</td>
         </tr>
         <tr>
            <td>Rue :</td>
            <td>${utilisateur.rue}</td>
         </tr>
         <tr>
            <td>Code Postal :</td>
            <td>${utilisateur.codePostal}</td>
         </tr>
         <tr>
            <td>Ville :</td>
            <td>${utilisateur.ville}</td>
         </tr>
      </table>
   </c:if>

</body>
</html>