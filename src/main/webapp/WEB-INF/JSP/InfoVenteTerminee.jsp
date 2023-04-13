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
	
        </header>
            <h1>${acheteur.pseudo} a remporté la vente</h1>
			<div>
				<%@ include file="prezVente.jspf" %>
			</div>
            <div>
                <a href="#">Retour</a>
            </div>

    </body>
</html>