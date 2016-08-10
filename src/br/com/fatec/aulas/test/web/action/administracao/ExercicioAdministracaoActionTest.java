package br.com.fatec.aulas.test.web.action.administracao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.api.entity.Exercicio;
import br.com.fatec.aulas.core.dao.ExercicioDAOImpl;
import br.com.fatec.aulas.core.helper.ExercicioFactory;
import br.com.fatec.aulas.test.commons.ConfigCenarioTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;
import br.com.fatec.aulas.web.action.administracao.ExercicioAdministracaoAction;

public class ExercicioAdministracaoActionTest extends ConfigCenarioTestCase{

	private ExercicioAdministracaoAction exercicioAction;
	private EntityDAO<Exercicio> exercicioDao;
	private Disciplina disciplina;
	
	@Before
	public void setUp() {
		this.exercicioAction = new ExercicioAdministracaoAction();
		this.exercicioDao = new ExercicioDAOImpl();
		this.disciplina = new Disciplina();
	}

	@Test
	public void testCarregarListaDisciplina() {
		exercicioAction.carregarListaDisciplina();
		Assert.assertNotNull(exercicioAction.getListaDisciplina());
	}

	@Test
	public void testInstanciarNovo() {
		Assert.assertEquals("form", exercicioAction.instanciarNovo());
	}

	@Test
	public void testBuscar() {
		Exercicio exercicio = ExercicioFactory.criarExercicio(1L, "5+7", "12", 1L);
		exercicioAction.setT(exercicio);
		exercicioAction.buscar();
		CustomAsserts.assertExercicio(exercicio, exercicioAction.getT());
	}

	@Test
	public void testSalvar() {
		Exercicio exercicioToSave = ExercicioFactory.criarExercicio(null, "3+2", "5",1L);
		exercicioAction.setT(exercicioToSave);
		exercicioAction.setIdDisciplina("1");
		this.exercicioAction.salvar();
		Exercicio exercicioSaved = exercicioAction.getT();

		CustomAsserts.assertExercicio(exercicioToSave, exercicioSaved);
	}

	@Test
	public void testUpdate() {
		Exercicio exercicioToUpdate = ExercicioFactory.criarExercicio(2L, "3+2", "5",1L);
		exercicioAction.setT(exercicioToUpdate);
		exercicioAction.setIdDisciplina("1");
		this.exercicioAction.salvar();
		Exercicio exercicioUpdated = exercicioAction.getT();

		CustomAsserts.assertExercicio(exercicioToUpdate,exercicioUpdated);
	}

	@Test
	public void testRemover() {
		disciplina.setId(1L);
		Exercicio exercicioToSave = ExercicioFactory.criarExercicio(null,"3+2", "5", disciplina);
		Exercicio savedExercicio = this.exercicioDao.save(exercicioToSave);

		CustomAsserts.assertExercicio(exercicioToSave, savedExercicio);
		this.exercicioAction.setT(savedExercicio);
		this.exercicioAction.remover();

		Assert.assertNull(this.exercicioDao.findById(savedExercicio.getId()));

	}

}
