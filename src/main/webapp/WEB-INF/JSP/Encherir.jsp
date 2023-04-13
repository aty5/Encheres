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
            <h1>Détail Vente</h1>
			<div>
				<%@ include file="prezVente.jspf" %>
			</div>
            <div>
                <form>
                    <label for="proposition">Ma proposition</label>
                    <input type="number" name="proposition" id="proposition" value="${article.prixVente}">
                    <input type="submit" value="Encherir" name="Btn_Encherir" id="Btn_Encherir">
                </form>
            </div>
    </body>
</html>