<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Professor</title>
	</head>
	<body>
		<div>
			<form action="" method="post">
				<input type="hidden" name="t.id" value="${t.id}">
				<label>Disciplina:</label>
				<select name="idDisciplina" >
					<c:forEach var="disciplina" items="${listaDisciplina}">
						<option value="${disciplina.id}" title="${disciplina.nome}" label="${disciplina.nome}" />
					</c:forEach>
				</select>
				<label>Nome:</label>
				<input type="text" name="t.nome" value="${t.nome}">
				<label>R.A:</label>
				<input type="text" name="t.rp" value="${t.rp}">
				<label>Data de Nascimento:</label>
				<input type="date" name="dataNascimento" value="${t.dataNascimento}">
				<label>E-mail:</label>
				<input type="email" name="t.email" value="${t.email}">
				<input type="submit" value="Salvar"  formaction="Professor!salvar">
				<input type="submit" value="Cancelar" formaction="Professor!carregarLista">
			</form>
		</div>
	</body>
</html>