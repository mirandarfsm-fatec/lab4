package br.com.fatec.aulas.api.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.core.dao.ExercicioDAOImpl;
import br.com.fatec.aulas.core.dao.MatriculaDAOImpl;
import br.com.fatec.aulas.core.dao.ProfessorDAOImpl;

/**
 * @author Miranda
 *
 * @version 1.0.1
 */
@Getter
@Setter
public class Disciplina extends IdentifiedEntity {

	/** */
	public static final String TABLE = "FATEC_DISCIPLINA";
	/** */
	public static final String COL_NOME = "NOME";

	private String nome;
	private List<Aluno> alunos;
	private List<Exercicio> exercicios;
	private List<Professor> profesores;
	
	public List<Aluno> getAluno(){
		if(this.alunos == null)
			this.alunos = new MatriculaDAOImpl().getAlunos(this.id);
		return this.alunos;
	}
		
	public List<Exercicio> getExercicios(){
		if(this.exercicios == null)
			this.exercicios = new ExercicioDAOImpl().getExercicios(this.id);
		return this.exercicios;
	}
	
	public List<Professor> geProfesores(){
		if(this.profesores == null)
			this.profesores = new ProfessorDAOImpl().getProfessores(this.id);
		return this.profesores;
	}

}
