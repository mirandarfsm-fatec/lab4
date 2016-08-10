package br.com.fatec.aulas.api.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Miranda
 *
 * @version 1.0.1
 */
@Getter
@Setter
public class AlunoDisciplina extends IdentifiedEntity {

	/** */
	public static final String TABLE = "FATEC_ALUNO_DISCIPLINA";
	/** */
	public static final String COL_ID_ALUNO = "ID_ALUNO";
	/** */
	public static final String COL_ID_DISCIPLINA = "ID_DISCIPLINA";

	private Disciplina disciplina;
	private Aluno aluno;

}
