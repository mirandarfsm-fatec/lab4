<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Cadastro Professor</title>
	</head>
	<body>
		<div>
			<h3>
				<form action="">
					<input type="submit" value="Novo" formaction="Professor!instanciarNovo">
				</form>
			</h3>
		</div>
		<div>
			<c:if test="${listaEntidade.isEmpty()}">Nenhum Professor Cadastrado!</c:if>
			<c:if test="${!listaEntidade.isEmpty()}">
				<table>
					<tr>
						<th>Nome</th>
						<th>RP</th>
						<th>Data de Nascimento</th>
						<th>E-MAIL</th>
					</tr>
					<c:forEach var="entidade" items="${listaEntidade}">
						<tr>
							<form action="" method="post">
								<input type="hidden" name="t.id" value="${entidade.id}">
								<td><label>${entidade.nome}</label></td>
								<td><label>${entidade.rp}</label></td>
								<td><label>${entidade.dataNascimento}</label></td>
								<td><label>${entidade.email}</label></td>
								<td><input type="submit" value="editar" formaction="Professor!buscar"></td>
								<td><input type="submit" value="excluir" formaction="Professor!remover"></td>
							</form>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>		
	</body>
</html>