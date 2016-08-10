package br.com.fatec.aulas.web.action.administracao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.api.entity.Professor;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.dao.ProfessorDAOImpl;

@Getter
@Setter
public class ProfessorAdministracaoAction extends
		AdministracaoAction<Professor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dataNascimento;
	private String idDisciplina;
	private List<Disciplina> listaDisciplina;

	public ProfessorAdministracaoAction() {
		this.setDao(new ProfessorDAOImpl());
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
		try {
			this.getT().setDisciplina(new Disciplina());
			this.getT().getDisciplina().setId(Long.parseLong(idDisciplina));
			this.getT().setDataNascimento(
					new SimpleDateFormat("yyyy-MM-dd")
							.parse(this.dataNascimento));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return super.salvar();
	}
}
