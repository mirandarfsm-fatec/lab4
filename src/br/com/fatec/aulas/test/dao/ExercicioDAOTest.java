package br.com.fatec.aulas.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.api.entity.Exercicio;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.dao.ExercicioDAOImpl;
import br.com.fatec.aulas.core.helper.DisciplinaFactory;
import br.com.fatec.aulas.core.helper.ExercicioFactory;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;

public class ExercicioDAOTest extends ConfigDBTestCase {

	private EntityDAO<Exercicio> exercicioDAO;
	private Disciplina disciplina;
	private EntityDAO<Disciplina> disciplinaDAO;

	@Before
	public void config() {
		this.exercicioDAO = new ExercicioDAOImpl();
		this.disciplinaDAO = new DisciplinaDAOImpl();
		disciplina = DisciplinaFactory.criarDisciplina(null, "matematica");
		disciplina = disciplinaDAO.save(disciplina);
	}

	@Test
	public void saveExercicioTest() {
		Exercicio exercicioToSave = ExercicioFactory.criarExercicio(null,
				"3+2", "5", disciplina);
		Exercicio savedExercicio = this.exercicioDAO.save(exercicioToSave);

		CustomAsserts.assertExercicio(exercicioToSave, savedExercicio);
	}

	@Test
	public void findAllTest() {
		Exercicio exercicioToSave1 = ExercicioFactory.criarExercicio(null,
				"3+2", "5", disciplina);
		Exercicio exercicioToSave2 = ExercicioFactory.criarExercicio(null,
				"1+1", "2", disciplina);
		Exercicio exercicioToSave3 = ExercicioFactory.criarExercicio(null,
				"3+3", "6", disciplina);

		List<Exercicio> experados = new ArrayList<Exercicio>();

		experados.add(this.exercicioDAO.save(exercicioToSave1));
		experados.add(this.exercicioDAO.save(exercicioToSave2));
		experados.add(this.exercicioDAO.save(exercicioToSave3));

		List<Exercicio> exercicios = this.exercicioDAO.findAll();
		Assert.assertEquals(3, exercicios.size());
		CustomAsserts.assertExercicios(experados, exercicios);
	}

	@Test
	public void removeExercicioTest() {
		Exercicio exercicioToSave = ExercicioFactory.criarExercicio(null,
				"3+2", "5", disciplina);
		Exercicio savedExercicio = this.exercicioDAO.save(exercicioToSave);

		CustomAsserts.assertExercicio(exercicioToSave, savedExercicio);
		this.exercicioDAO.remove(savedExercicio);

		Assert.assertNull(this.exercicioDAO.findById(savedExercicio.getId()));
	}

	@Test
	public void updateExercicioTest() {
		Disciplina disciplina2 = DisciplinaFactory.criarDisciplina(null,"fisica");
		disciplina2 = disciplinaDAO.save(disciplina2);

		Exercicio exercicioToSave = ExercicioFactory.criarExercicio(null,
				"3+2", "5", disciplina);
		Exercicio savedExercicio = this.exercicioDAO.save(exercicioToSave);

		savedExercicio.setPergunta("3+3");
		savedExercicio.setResposta("6");
		savedExercicio.setDisciplina(disciplina2);

		Exercicio updatedExercicio = this.exercicioDAO.update(savedExercicio);
		CustomAsserts.assertExercicio(savedExercicio, updatedExercicio);
		this.exercicioDAO.remove(updatedExercicio);
	}



}
