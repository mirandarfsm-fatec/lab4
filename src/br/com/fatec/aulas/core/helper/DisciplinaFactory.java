package br.com.fatec.aulas.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;

public class DisciplinaFactory {

	public static Disciplina criarDisciplina(Long id, String nome) {
		Disciplina disciplina = new Disciplina();
		disciplina.setId(id);
		disciplina.setNome(nome);
		return disciplina;
	}

	public static Disciplina criarDisciplina(Long id, String nome, List<Aluno> alunos) {
		Disciplina disciplina = new Disciplina();
		disciplina.setId(id);
		disciplina.setNome(nome);
		disciplina.setAlunos(alunos);
		return disciplina;
	}

	public static List<Disciplina> criarDisciplinas(ResultSet resultado) {
		try {
			List<Disciplina> disciplinas = new ArrayList<Disciplina>();
			while (resultado.next()) {
				disciplinas.add(DisciplinaFactory.criarDisciplina(resultado));
			}
			return disciplinas;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar disciplina.", e);
		}
	}

	public static Disciplina criarDisciplina(ResultSet resultado) {
		try {
			return DisciplinaFactory.criarDisciplina(resultado.getLong(Disciplina.COL_ID),
					resultado.getString(Disciplina.COL_NOME));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar disciplina.", e);
		}
	}


}
