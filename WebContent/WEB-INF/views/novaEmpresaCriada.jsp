<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Nova Empresa Criada</title>
</head>
	<body>
		
		<c:if test="${ not empty empresa }">
			<!-- = o sinal de igual faz o print na tela -->
			<p>Empresa ${ empresa } cadastrada com sucesso!</p>
		</c:if>
		
		<c:if test="${ empty empresa }">
			Nenhuma empresa cadastrada!
		</c:if>

	</body>
</html>



<!-- java server page -> JSP --> 