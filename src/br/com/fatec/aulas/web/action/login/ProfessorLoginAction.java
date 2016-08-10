package br.com.fatec.aulas.web.action.login;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.api.entity.Professor;
import br.com.fatec.aulas.core.dao.ProfessorDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

@Getter
@Setter
public class ProfessorLoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Professor professor;

	public String carregarExercicios() {
		professor = new ProfessorDAOImpl().findById(professor.getId());
		return SUCCESS;
	}

}
