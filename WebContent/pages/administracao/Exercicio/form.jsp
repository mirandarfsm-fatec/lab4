<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Exercício</title>
	</head>
	<body>
		<div>
			<form action="" method="post">
				<input type="hidden" name="t.id" value="${t.id}" />
				<label>Disciplina:</label>
				<select name="idDisciplina" >
					<c:forEach var="disciplina" items="${listaDisciplina}">
						<option value="${disciplina.id}" title="${disciplina.nome}" label="${disciplina.nome}" />
					</c:forEach>
				</select>
				<label>Pergunta:</label>
				<input type="text" name="t.pergunta" value="${t.pergunta}" />
				<label>Resposta:</label>
				<input type="text" name="t.resposta" value="${t.resposta}" />

				<input type="submit" value="Salvar"  formaction="Exercicio!salvar">
				<input type="submit" value="Cancelar" formaction="Exercicio!carregarLista" />
			</form>
		</div>
	</body>
</html>