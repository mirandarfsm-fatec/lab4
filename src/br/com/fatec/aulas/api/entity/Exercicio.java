package br.com.fatec.aulas.api.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Miranda
 *
 * @version 1.0.0
 */
@Getter
@Setter
public class Exercicio extends IdentifiedEntity {

	/** */
	public static final String TABLE = "FATEC_EXERCICIO";
	/** */
	public static final String COL_PERGUNTA = "PERGUNTA";
	/** */
	public static final String COL_RESPOSTA = "RESPOSTA";
	/** */
	public static final String COL_ID_DISCIPLINA = "ID_DISCIPLINA";

	private Disciplina disciplina;
	private String pergunta;
	private String resposta;


}
