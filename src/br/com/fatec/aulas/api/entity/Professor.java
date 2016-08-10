package br.com.fatec.aulas.api.entity;

import java.util.Date;

import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.dao.ExercicioDAOImpl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Professor extends IdentifiedEntity {

	/** */
	public static final String TABLE = "FATEC_PROFESSOR";
	/** */
	public static final String COL_NOME = "NOME";
	/** */
	public static final String COL_RP = "RP";
	/** */
	public static final String COL_DATA_NASCIMENTO = "DATA_NASCIMENTO";
	/** */
	public static final String COL_EMAIL = "EMAIL";
	/** */
	public static final String COL_ID_DISCIPLINA = "ID_DISCIPLINA";

	private String nome;
	private String rp;
	private Date dataNascimento;
	private String email;
	private Disciplina disciplina;
	

}
