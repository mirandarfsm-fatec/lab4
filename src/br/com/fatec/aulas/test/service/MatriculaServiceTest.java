package br.com.fatec.aulas.test.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.dao.MatriculaDAOImpl;
import br.com.fatec.aulas.core.service.MatriculaServiceImpl;
import br.com.fatec.aulas.test.commons.ConfigCenarioTestCase;

public class MatriculaServiceTest extends ConfigCenarioTestCase {

	private MatriculaServiceImpl matriculaService;
	private EntityDAO<Aluno> alunoDao;
	private EntityDAO<Disciplina> disciplinaDao;

	@Before
	public void setUp() {
		this.matriculaService = new MatriculaServiceImpl();
		this.alunoDao = new AlunoDAOImpl();
		this.disciplinaDao = new DisciplinaDAOImpl();
	}

	@After
	public void tearDown() {
		Disciplina disciplina = this.disciplinaDao.findById(1L);
		disciplina.setAlunos(null);
		this.disciplinaDao.update(disciplina);

		Aluno aluno = this.alunoDao.findById(1L);
		aluno.setDisciplinas(null);
		this.alunoDao.update(aluno);
	}

	@Test
	public void matricularUmAlunoEmDisciplinaVazia() {
		Aluno[] alunoArray = new Aluno[1];
		alunoArray[0] = this.alunoDao.findById(1L);
		Disciplina disciplina = this.disciplinaDao.findById(1L);
		new MatriculaServiceImpl().add(disciplina, alunoArray);
		disciplina = new MatriculaDAOImpl().getAlunos(disciplina);
		Assert.assertEquals(1, disciplina.getAlunos().size());
	}

	@Test
	public void matricularVariosAlunoEmDisciplinaVazia() {
		Aluno[] alunoArray = new Aluno[2];
		alunoArray[0] = this.alunoDao.findById(2L);
		alunoArray[1] = this.alunoDao.findById(3L);
		Disciplina disciplina = this.disciplinaDao.findById(1L);
		matriculaService.add(disciplina, alunoArray);
		disciplina = new MatriculaDAOImpl().getAlunos(disciplina);
		Assert.assertEquals(2, disciplina.getAlunos().size());
	}

	@Test
	public void matricularAlunoEmDisciplinaNaoVazia() {
		matricularUmAlunoEmDisciplinaVazia();

		Aluno[] alunoArray = new Aluno[1];
		alunoArray[0] = this.alunoDao.findById(2L);
		Disciplina disciplina = this.disciplinaDao.findById(1L);
		matriculaService.add(disciplina, alunoArray);
		disciplina = new MatriculaDAOImpl().getAlunos(disciplina);
		Assert.assertEquals(2, disciplina.getAlunos().size());
	}

	public void matricularUmDisciplinaEmAlunoVazia() {

	}

	public void matricularVariosDisciplinaEmAlunoVazia() {

	}

	public void matricularDisciplinaEmAlunoNaoVazia() {

	}

}
