package br.com.fatec.aulas.web.action.administracao;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;

@Getter
@Setter
public class DisciplinaAdministracaoAction extends
		AdministracaoAction<Disciplina> {

	/**
				 * 
				 */
	private static final long serialVersionUID = 1L;
	
	
	public DisciplinaAdministracaoAction() {
		this.setDao(new DisciplinaDAOImpl());
	}
}
