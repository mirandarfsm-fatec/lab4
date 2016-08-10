<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Professor</title>
</head>
<body>
	<div>
		<h3>Nome: ${professor.nome}</h3>
		<h4>RP: ${professor.rp}</h4>
		<h4>Data de Nascimento: ${professor.dataNascimento}</h4>
	</div>
	<div>
		<form>
			<input type="hidden" name="aluno.id" value="${aluno.id}"> <input
				type="submit" value="Cadastrar Perguntas"
				formaction=../administracao/Exercicio!instanciarNovo>
		</form>
	</div>
	<div>
		<h3>${professor.disciplina.nome }</h3>
		<c:forEach var="exercicio" items="${professor.disciplina.exercicios}">
			<form>
				<input type="hidden" value="${exercicio.id}">
				<p>${exercicio.pergunta} = <b>${exercicio.resposta}</b> </p>
			</form>
		</c:forEach>
	</div>
</body>
</html>