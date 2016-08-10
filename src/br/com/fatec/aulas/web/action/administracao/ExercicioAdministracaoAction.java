package br.com.fatec.aulas.web.action.administracao;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.api.entity.Exercicio;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.dao.ExercicioDAOImpl;

@Getter
@Setter
public class ExercicioAdministracaoAction extends
		AdministracaoAction<Exercicio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Disciplina> listaDisciplina;
	private String idDisciplina;

	public ExercicioAdministracaoAction() {
		this.setDao(new ExercicioDAOImpl());
	}

	public void carregarListaDisciplina() {
		this.listaDisciplina = new DisciplinaDAOImpl().findAll();
	}

	@Override
	public String instanciarNovo() {
		this.carregarListaDisciplina();
		return super.instanciarNovo();
	}

	@Override
	public String buscar() {
		this.carregarListaDisciplina();
		return super.buscar();
	}
	
	@Override
	public String salvar() {
		this.getT().setDisciplina(new Disciplina());
		this.getT().getDisciplina().setId(Long.parseLong(idDisciplina));
		return super.salvar();
	}

}
