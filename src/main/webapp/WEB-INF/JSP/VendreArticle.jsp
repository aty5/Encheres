<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vendre un article</title>
</head>
<body>
       <jsp:include page="/WEB-INF/fragments/navBar.jsp"></jsp:include>
	
       <h1 class="text-center">Nouvelle Vente</h1>
       <div>
           <img src="ordiportable1.jpg">
       </div>
       <form action="<%=request.getContextPath()%>/VendreUnArticle" method="post">
           <div>
               <label for="nom">Article</label>
               <input type="text" id="nom" name="nom">
           </div>
           <div>
               <label for="description">Description</label>
               <textarea name="description" rows="10"></textarea>
           </div>
           <div>
               <label for="categorie">Catégorie</label>
               <select id="categorie" name="categorie">
                   <c:forEach var="ctg" items="${categories}">							
					<option value=${ctg}>${ctg}</option>
				</c:forEach>
               </select>
           </div>
           <div>
               <label for="photo">Photo de l'article</label>
               <input type="submit" value="UPLOADER">
           </div>
           <div>
               <label for="prix">Mise à prix</label>
               <input type="number" id="prix" name="prix">
           </div>
           <div>
               <label for="debut">Début de l'enchère</label>
               <input type="date" id="debut" name="debut" value="${debutencheres}">
           </div>
           <div>
               <label for="fin">Fin de l'enchère</label>
               <input type="date" id="fin" name="fin">
           </div>
           <div>
               <fieldset>
                   <legend>Retrait</legend>
                   <div>
                       <label for="rue">Rue :</label>
                       <input type="text" id="rue" name="rue" value="${adresseRetraitParDefaut.rue}">
                   </div>
                   <div>
                       <label for="codepostal">Code postal :</label>
                       <input type="text" id="codepostal" name="codepostal" value="${adresseRetraitParDefaut.codePostal}">
                   </div>
                   <div>
                       <label for="ville">Ville :</label>
                       <input type="text" id="ville" name="ville" value="${adresseRetraitParDefaut.ville}">
                   </div>
               </fieldset>
           </div>
           <div>
               <input class="btn-lg" type="submit" value="Enregistrer">
               <input class="btn-lg" type="reset" value="Réinitialiser">
               <a href="<%=request.getContextPath()%>/WEB-INF/JSP/accueil.jsp">Annuler</a>
           </div>
       </form>
    </body>
</html>