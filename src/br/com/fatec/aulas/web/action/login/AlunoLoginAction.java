package br.com.fatec.aulas.web.action.login;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.dao.MatriculaDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Exercicio;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.service.ExercicioServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

@Getter
@Setter
public class AlunoLoginAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	private EntityDAO<Aluno> alunoDao;
	private MatriculaDAO matriculaDao;
	private Exercicio exercicio;
	private ExercicioServiceImpl exercicioServiceImpl;
	
	public AlunoLoginAction() {
		this.exercicioServiceImpl = new ExercicioServiceImpl();
		this.alunoDao = new AlunoDAOImpl();
	}
	
	public String carregarExercicios() {
		this.aluno = this.alunoDao.findById(aluno.getId());
		return SUCCESS;
	}
	
	public boolean verificarResposta(){
		return this.exercicioServiceImpl.isCorreto(this.exercicio);
	}
}
