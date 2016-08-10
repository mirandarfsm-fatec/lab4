<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Matricula</title>
	</head>
	<body>
		<div>
			<table>
				<tr>
					<th>Nome</th>
					<th>Disciplinas</th>
				</tr>
				<c:forEach var="entidade" items="${listaEntidade}">
					<tr>
						<form action="" method="post">
							<input type="hidden" name="aluno.id" value="${entidade.id}">
							<td><label>${entidade.nome}</label></td>
							<td>
								<c:forEach var="disciplina" items="${entidade.disciplinas}">
									<label>${disciplina.nome}</label>
								</c:forEach>
							</td>
							<td><input type="submit" value="Matricular" formaction="Matricula!buscar"></td>
							<td><input type="submit" value="Desmatricular" formaction="Matricula!buscarRemocao"></td>
						</form>
					</tr>
				</c:forEach>
			</table>
		</div>		
	</body>
</html>