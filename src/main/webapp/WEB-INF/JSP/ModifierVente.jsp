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
	
       <h1>Nouvelle Vente</h1>
       <div>
           <img src="ordiportable1.jpg">
       </div>
       <form action="<%=request.getContextPath()%>/VendreUnArticle" method="post">
           <div>
               <label for="nom">Article</label>
               <input type="text" id="nom" name="nom" value="${article.nom}" ${acces}>
           </div>
           <div>
               <label for="description">Description</label>
               <textarea name="description" rows="10"  value="${article.description}" ${acces}></textarea>
           </div>
           <div>
               <label for="categorie">Catégorie</label>
               <select class="form-select" id="categorie" name="categorie" ${acces}>
                   <c:forEach var="ctg" items="${categories}">
                   		<c:if test="${ctg==article.categorie}">
                   			<option value=${ctg} selected>${ctg}</option>
                   		</c:if>
                   		<c:if test="${ctg!=article.categorie}">
                   			<option value=${ctg}>${ctg}</option>
                   		</c:if>									
				</c:forEach>
               </select>
           </div>
           <div>
               <label for="photo">Photo de l'article</label>
               <input type="submit" value="UPLOADER" ${acces}>
           </div>
           <div>
               <label for="prix">Mise à prix</label>
               <input type="number" id="prix" name="prix"   value="${article.miseAPrix}" ${acces}>
           </div>
           <div>
               <label for="debut">Début de l'enchère</label>
               <input type="date" id="debut" name="debut"  value="${article.debutEncheres}" ${acces}>
           </div>
           <div>
               <label for="fin">Fin de l'enchère</label>
               <input type="date" id="fin" name="fin"  value="${article.finEncheres}">
           </div>
           <div>
               <fieldset>
                   <legend>Retrait</legend>
                   <div>
                       <label for="rue">Rue :</label>
                       <input type="text" id="rue" name="rue" value="${article.lieuRetrait.rue}">
                   </div>
                   <div>
                       <label for="codepostal">Code postal :</label>
                       <input type="text" id="codepostal" name="codepostal" value="${article.lieuRetrait.codePostal}">
                   </div>
                   <div>
                       <label for="ville">Ville :</label>
                       <input type="text" id="ville" name="ville" value="${article.lieuRetrait.ville}">
                   </div>
               </fieldset>
           </div>
           <div>
               <input class="btn-lg" type="submit" value="Enregistrer" name="btnEnregistrer" id="btnEnregistrer">
               <input class="btn-lg" type="reset" value="Réinitialiser" ${acces}>
               <a href="<%=request.getContextPath()%>">Annuler</a>
               <input class="btn-lg" type="submit" value="Annuler la vente" name="btnAnnuler" id="btnAnnuler" ${acces}>
           </div>
       </form>
</body>
</html>