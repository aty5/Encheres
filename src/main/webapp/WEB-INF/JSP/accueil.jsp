<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="fr.eni.enchere.messages.LecteurMessage" %>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body>
	<jsp:include page="/WEB-INF/fragments/navBar.jsp"></jsp:include>
	
	<h1>ENI - Enchères</h1>
	<h2>Liste des enchères</h2>
		
<div >
	<b>Filtres : </b>
	<form action="<%=request.getContextPath()%>/Accueil" method="post">
	<input type="text" name="nomArticle" id="nomArticle" placeholder="Le nom de l'article contient"/><br>
	<label for="categorie">Catégorie : </label>
	<select id="categorie" name="categorie">
	    <c:forEach var="ctg" items="${categories}">							
			<option value=${ctg}>${ctg}</option>
		</c:forEach>
	</select>
	
<!-- <select name="categorie_select">
		<option  value="toutes">Toutes</option> 
		<option  value="Informatique">Informatique</option>
		<option  value="Ameublement">Ameublement</option>
		<option  value="Vêtement">Vêtement</option>
		<option  value="Sport&Loisirs">Sport et Loisirs</option>
	</select>  -->	
	
	<button type="submit">Rechercher</button>
	</form>
</div>

<div class="container-fluid">
	<div class="col-12">
	
	<c:choose>
	<c:when test="${empty sessionScope.utilisateur}">
 		<c:forEach items="${articlesEnCours}" var="row">
 			<section class="article">
  				<ul>
   					<li><c:out value="${row.nom}"/></li>
   					<li>Prix : <c:out value="${row.miseAPrix}"/> </li>
   					<li>Fin de l'enchère : <c:out value="${row.finEncheres}"/> </li>
   					<li>Vendeur : <c:out value="${row.pseudo}"/> </li>
  				</ul>
 			</section>
 		</c:forEach>	
 		</c:when>
 		
 		<c:otherwise>
 		<c:forEach items="${articlesEnCours}" var="row">
 			<section class="article">
  				<ul>
   					<li><a href="<%=request.getContextPath()%>/VoirArticle?numeroArticle=${row.idArticle}&pseudoVendeur=${row.pseudo}">
						<c:out value="${row.nom}"/>
						</a>
					</li>
   					<li>Prix : <c:out value="${row.miseAPrix}"/> </li>
   					<li>Fin de l'enchère : <c:out value="${row.finEncheres}"/> </li>
   					<li>Vendeur : <a href="<%=request.getContextPath()%>/AffichageUtilisateur?pseudo=${row.pseudo}"><c:out value="${row.pseudo}"/></a>
   						</li>
  				</ul>
 			</section>
 		</c:forEach>	
 		</c:otherwise>
 		</c:choose>
 		
	</div>
</div>
</body>
</html>

<!-- <%=request.getContextPath()%>/VoirArticle?idArticle=${row.nom}"><c:out value="${row.nom}"/> 
<%=request.getContextPath()%>/AfficherUtilisateur"> -->