<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aluno</title>
</head>
<body>
	<div>
		<h3>Nome: ${aluno.nome}</h3>
		<h4>RA: ${aluno.ra}</h4>
		<h4>Data de Nascimento: ${aluno.dataNascimento}</h4>
	</div>
	
	<div>
		<form >
			<input type="hidden" name="aluno.id" value="${aluno.id}">
			<input type="submit" value="Cadastrar Disciplina" formaction=../administracao/Matricula!buscar>
		</form>
	</div>
	<div>
		<c:forEach var="disciplina" items="${aluno.disciplinas}">
			<h3>${disciplina.nome }</h3>
			<c:forEach var="exercicio" items="${disciplina.exercicios}">
				<form method="post">
					<input type="hidden" name="aluno.id" value="${aluno.id}">
					<input type="hidden" name="exercicio.id" value="${exercicio.id}">
					<label>${exercicio.pergunta}</label>
					<input type="text" name="exercicio.resposta">
					<input type="submit">
				</form>
				<c:if test="${verificarResposta}">Resposta Certa!</c:if>
			</c:forEach>
		</c:forEach>
	</div>
</body>
</html>