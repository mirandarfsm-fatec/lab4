package br.com.fatec.aulas.api.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.core.dao.MatriculaDAOImpl;

/**
 * @author Miranda
 * 
 * @version 1.0.1
 */
@Getter
@Setter
public class Aluno extends IdentifiedEntity {

	/** */
	public static final String TABLE = "FATEC_ALUNO";
	/** */
	public static final String COL_NOME = "NOME";
	/** */
	public static final String COL_RA = "RA";
	/** */
	public static final String COL_DATA_NASCIMENTO = "DATA_NASCIMENTO";
	/** */
	public static final String COL_OBSERVACAO = "OBSERVACAO";

	private String nome;
	private String ra;
	private Date dataNascimento;
	private String observacao;
	@ManyToMan
    @JoinTable(name="EMP_PROJ",
                joinColumns=
                     @JoinColumn(name="EMP_ID"),
                inverseJoinColumns=
                     @JoinColumn(name="PROJ_ID")
    )
	private List<Disciplina> disciplinas;

	public List<Disciplina> getDisciplinas(){
		if(this.disciplinas == null){
			this.disciplinas = new MatriculaDAOImpl().getDisciplinas(this.id);
		}
		return this.disciplinas; 
	}
}
