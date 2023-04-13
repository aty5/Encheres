<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Détail d'un article</title>
    </head>
    <body>
        <header>
           <jsp:include page="/WEB-INF/fragments/navBar.jsp"></jsp:include>
	
        </header>
            <h1> ${acheteur.pseudo} à remporté la vente</h1>
			<div>
				<%@ include file="prezVente.jspf" %>
			</div>
            <div>
            	<form>
                    <input type="submit" value="Retrait effectué">
                </form>
               <a href="<%=request.getContextPath()%>">Retour</a>
            </div>

    </body>
</html>