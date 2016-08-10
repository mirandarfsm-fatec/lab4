<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Cadastro Exercício</title>
	</head>
	<body>
		<div>
		</div>
		<div>
			<c:if test="${listaAlunos != null}">
				<table>
					<tr>
						<th>Nome</th>
						<th>R.A.</th>

					</tr>
					<c:forEach var="aluno" items="${listaAlunos}">
						<tr>
							<form action="" method="post">
								<input type="hidden" name="aluno.id" value="${aluno.id}">
								<td><label>${aluno.nome}</label></td>
								<td><label>${aluno.ra}</label></td>
								<td><input type="submit" value="Logar" formaction="AlunoLogin!carregarExercicios"></td>
							</form>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${listaProfessores!= null}">
				<table>
					<tr>
						<th>Nome</th>
						<th>R.P.</th>

					</tr>
					<c:forEach var="professor" items="${listaProfessores}">
						<tr>
							<form action="" method="post">
								<input type="hidden" name="professor.id" value="${professor.id}">
								<td><label>${professor.nome}</label></td>
								<td><label>${professor.rp}</label></td>
								<td><input type="submit" value="Logar" formaction="ProfessorLogin!carregarExercicios"></td>
							</form>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>		
	</body>
</html>