<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Détail d'un article</title>
    </head>
    <body>
        
            <jsp:include page="/WEB-INF/fragments/navBar.jsp"></jsp:include>
	
        
            <h1>Détail Vente</h1>
            <div>
                <div>
                    <p>Image</p>
                </div>
                <div>
                    <h3>${article.nom}</h3>
                    <div>
                        <p>Description :</p>
                        <div>
                            <p>${article.description}</p>
                        </div>
                    </div>
                    <div>
                        <p>Categorie :</p>
                        <div>
                            <p>${article.categorie}</p>
                        </div>
                    </div>
                    <div>
                        <p>Meilleur offre : </p>
                        <div>
                            <p>210 points proposée par Bob</p>
                        </div>
                    </div>
                    <div>
                        <p>Mise à prix :</p>
                        <div>
                            <p>185 points</p>
                        </div>
                    </div>
                    <div>
                        <p>Fin de l'enchère</p>
                        <div>
                            <p>La date de fin</p>
                        </div>
                    </div>
                    <div>
                        <p>Retrait :</p>
                        <div>
                            <p>adresse code postale Ville</p>
                        </div>
                    </div>
                    <div>
                        <p>Vendeur :</p>
                        <div>
                            <a href="#">Nom du vendeur</a>
                        </div>
                    </div>
                    <div>
                        <form>
                            <label for="proposition">Ma proposition</label>
                            <input type="nombre" name="proposition" id="proposition">
                            <input type="submit" value="Encherir">
                        </form>
                    </div>


                </div>
            </div>

    </body>
</html>