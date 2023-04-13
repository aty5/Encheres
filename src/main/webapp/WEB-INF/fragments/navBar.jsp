<header>

<c:if test="${!empty sessionScope.utilisateur}">
<a href="<%=request.getContextPath()%>/Accueil">Eni Encheres</a>
</c:if>

<c:choose>
	<c:when test="${empty sessionScope.utilisateur}">
		<a class="nav-link active" href="<%=request.getContextPath()%>/SeConnecter">S'inscrire | Se connecter</a>
	</c:when>
	<c:otherwise>
		<a class="nav-link" href="<%=request.getContextPath()%>/VendreUnArticle">Encheres</a>
		<a class="nav-link" href="<%=request.getContextPath()%>/VendreUnArticle">Vendre un article</a>
		<a class="nav-link" href="<%=request.getContextPath()%>/ModifierProfil">Mon profil</a>
		<a class="nav-link disabled" href="<%=request.getContextPath()%>/Accueil" ${session.invalidate}>Deconnexion</a>
	</c:otherwise>
</c:choose>
</header>