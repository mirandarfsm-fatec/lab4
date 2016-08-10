package br.com.fatec.aulas.web.action.administracao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.helper.DisciplinaFactory;
import br.com.fatec.aulas.core.service.MatriculaServiceImpl;

import com.opensymphony.xwork2.ActionSupport;
@Getter
@Setter
public class MatriculaAdministracaoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static final String FORM = "form";
	protected static final String LIST = "list";

	private Aluno aluno;
	private EntityDAO<Aluno> alunoDao;
	private List<Aluno> listaEntidade;
	private EntityDAO<Disciplina> disciplinaDao;
	private MatriculaServiceImpl matriculaServiceImpl;

	private List<Disciplina> disciplinaLista;
	private String[] listaDisciplinaAluno;
	
	public MatriculaAdministracaoAction() {
		this.alunoDao = new AlunoDAOImpl();
		this.matriculaServiceImpl = new MatriculaServiceImpl();
		this.disciplinaDao = new DisciplinaDAOImpl();
	}

	public String carregarLista() {
		this.listaEntidade = this.alunoDao.findAll();
		return LIST;
	}

	public String buscar() {
		this.aluno = this.alunoDao.findById(this.aluno.getId());
		this.disciplinaLista = this.matriculaServiceImpl.minimizeList(this.disciplinaDao.findAll(), aluno.getDisciplinas());
		return FORM;
	}
	
	public String buscarRemocao() {
		this.aluno = this.alunoDao.findById(this.aluno.getId());
		this.disciplinaLista = aluno.getDisciplinas();
		return FORM;
	}

	public String salvar() {
			Set<Disciplina> disciplinaArray = new HashSet<Disciplina>();
		for (String disciplina : listaDisciplinaAluno) {
			disciplinaArray.add(DisciplinaFactory.criarDisciplina(Long.parseLong(disciplina),null));
		}
		this.matriculaServiceImpl.add(aluno, disciplinaArray);
		this.carregarLista();
		return LIST;
	}

	public String remover() {
		Set<Disciplina> disciplinaArray = new HashSet<Disciplina>();
		for (String disciplina : listaDisciplinaAluno) {
			disciplinaArray.add(DisciplinaFactory.criarDisciplina(Long.parseLong(disciplina),null));
		}
		this.matriculaServiceImpl.remove(this.aluno, disciplinaArray);
		this.carregarLista();
		return LIST;
	}

}
