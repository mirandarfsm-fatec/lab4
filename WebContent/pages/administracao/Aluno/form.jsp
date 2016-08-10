<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Alunos</title>
	</head>
	<body>
		<div>
			<form action="" method="post">
				<input type="hidden" name="t.id" value="${t.id}">
				<label>Nome:</label>
				<input type="text" name="t.nome" value="${t.nome}">
				<label>R.A:</label>
				<input type="text" name="t.ra" value="${t.ra}">
				<label>Data de Nascimento:</label>
				<input type="date" name="dataNascimento" value="${t.dataNascimento}">
				<label>Observações:</label>
				<textarea cols="50" rows="10" name="t.observacao">${t.observacao}</textarea>
				<input type="submit" value="Salvar"  formaction="Aluno!salvar">
				<input type="submit" value="Cancelar" formaction="Aluno!carregarLista">
			</form>
		</div>
	</body>
</html>