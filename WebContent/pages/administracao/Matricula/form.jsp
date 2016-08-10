<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib prefix="s" uri="/struts-tags" %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Matricular Aluno</title>
	</head>
	<body>
		<div>
			<form action="" method="post">
				<input type="hidden" name="aluno.id" value="${aluno.id}">
				<label>Nome:</label>
				<label>${aluno.nome}</label> 
				<br/>
				<label>Disciplinas</label>
				<s:checkboxlist list="disciplinaLista" listKey="id" listValue="nome" name="listaDisciplinaAluno" value="" />
				<input type="submit" value="Salvar"  formaction="Matricula!salvar">
				<input type="submit" value="Remover"  formaction="Matricula!remover">
				<input type="submit" value="Cancelar" formaction="Matricula!carregarLista">
			</form>
		</div>
	</body>
</html>