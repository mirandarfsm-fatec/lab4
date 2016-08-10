package br.com.fatec.aulas.api.dao;

import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;

public interface MatriculaDAO {

	void update(Aluno aluno);

	void update(Disciplina disciplina);

	Disciplina getAlunos(Disciplina disciplina);

	Aluno getDisciplinas(Aluno aluno);
}
