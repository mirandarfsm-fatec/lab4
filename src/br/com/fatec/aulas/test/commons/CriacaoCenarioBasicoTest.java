package br.com.fatec.aulas.test.commons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.api.entity.Exercicio;
import br.com.fatec.aulas.api.entity.Professor;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.dao.ExercicioDAOImpl;
import br.com.fatec.aulas.core.dao.ProfessorDAOImpl;

public class CriacaoCenarioBasicoTest  extends ConfigCenarioTestCase{

	private EntityDAO<Aluno> alunoDao;
	private EntityDAO<Disciplina> disciplinaDao;
	private EntityDAO<Professor> professorDao;
	private EntityDAO<Exercicio> exercicioDao;
	
	/** */
	@Before
	public void config(){
		this.alunoDao = new AlunoDAOImpl();
		this.disciplinaDao = new DisciplinaDAOImpl();
		this.exercicioDao = new ExercicioDAOImpl();
		this.professorDao = new ProfessorDAOImpl();
	}
	
	/** */
	@Test
	public void criarCenarioTest(){
		Assert.assertEquals(3, this.alunoDao.findAll().size());
		Assert.assertEquals(3, this.disciplinaDao.findAll().size());
		Assert.assertEquals(3, this.professorDao.findAll().size());
		Assert.assertEquals(3, this.exercicioDao.findAll().size());
	}
}
