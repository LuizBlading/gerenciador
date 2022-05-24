<%@ page import="java.util.List,br.com.alura.gerenciador.modelo.Empresa" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lista Empresas - Java Stard Taglib</title>
	</head>
	<body>
		<!--importando arquivo de logout -->
		<c:import url="logout-parcial.jsp"/>
	
		Usuario Logado: ${ usuarioLogado.login }
		
		<br>
		<br>
		<br>
	
		<c:if test="${ not empty empresa }">
			<!-- = o sinal de igual faz o print na tela -->
			<p>Empresa ${ empresa } cadastrada com sucesso!</p>
		</c:if>	
		
		<h3>Lista Empresas:</h3>
		
		<ul>
			<c:forEach items="${ empresas }" var="empresa">
			
				<li>${ empresa.nome } - <fmt:formatDate value="${ empresa.dataAbertura }" pattern="dd/MM/yyyy"/>
					<a href="/gerenciador/entrada?acao=MostraEmpresas&id=${ empresa.id }">Editar</a>
					<a href="/gerenciador/entrada?acao=RemoveEmpresas&id=${ empresa.id }">Remove</a>
				</li>
			</c:forEach>
		</ul>
	</body>
</html>