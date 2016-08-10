<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Disciplina</title>
	</head>
	<body>
		<div>
			<form action="" method="post">
				<input type="hidden" name="t.id" value="${t.id}" />
				<label>Nome:</label>
				<input type="text" name="t.nome" value="${t.nome}" />
				<input type="submit" value="Salvar"  formaction="Disciplina!salvar" />
				<input type="submit" value="Cancelar" formaction="Disciplina!carregarLista" />
			</form>
		</div>
	</body>
</html>