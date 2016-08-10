package br.com.fatec.aulas.web.action.login;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Professor;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.dao.ProfessorDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

@Getter
@Setter
public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<Aluno> listaAlunos;
	protected List<Professor> listaProfessores;

	public String carregarAlunos() {
		this.listaAlunos = new AlunoDAOImpl().findAll();
		return SUCCESS;
	}

	public String carregarProfessor() {
		this.listaProfessores = new ProfessorDAOImpl().findAll();
		return SUCCESS;
	}
}
