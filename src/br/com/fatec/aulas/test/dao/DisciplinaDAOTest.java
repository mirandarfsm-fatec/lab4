package br.com.fatec.aulas.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.helper.DisciplinaFactory;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;

public class DisciplinaDAOTest extends ConfigDBTestCase {

	private EntityDAO<Disciplina> disciplinaDAO;

	@Before
	public void config() {
		this.disciplinaDAO = new DisciplinaDAOImpl();
		
	}

	@Test
	public void saveDisciplinaTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Disciplina disciplinaToSave = DisciplinaFactory.criarDisciplina(null, "matematica");
		Disciplina savedDisciplina = this.disciplinaDAO.save(disciplinaToSave);

		CustomAsserts.assertDisciplina(disciplinaToSave, savedDisciplina);
	}

	@Test
	public void findAllTest() {

		Disciplina disciplinaToSave1 = DisciplinaFactory.criarDisciplina(null, "matematica");
		Disciplina disciplinaToSave2 = DisciplinaFactory.criarDisciplina(
				null, "ingles");
		Disciplina disciplinaToSave3 = DisciplinaFactory.criarDisciplina(
				null, "portugues");

		List<Disciplina> experados = new ArrayList<Disciplina>();

		experados.add(this.disciplinaDAO.save(disciplinaToSave1));
		experados.add(this.disciplinaDAO.save(disciplinaToSave2));
		experados.add(this.disciplinaDAO.save(disciplinaToSave3));

		List<Disciplina> disciplinas = this.disciplinaDAO.findAll();
		Assert.assertEquals(3, disciplinas.size());
		CustomAsserts.assertDisciplinas(experados, disciplinas);
	}

	@Test
	public void removeDisciplinaTest() {
		Disciplina disciplinaToSave = DisciplinaFactory.criarDisciplina(
				null, "matematica");
		Disciplina savedDisciplina = this.disciplinaDAO.save(disciplinaToSave);

		CustomAsserts.assertDisciplina(disciplinaToSave, savedDisciplina);
		this.disciplinaDAO.remove(savedDisciplina);

		Assert.assertNull(this.disciplinaDAO.findById(savedDisciplina.getId()));
	}

	@Test
	public void updateDisciplinaTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Disciplina disciplinaToSave = DisciplinaFactory.criarDisciplina(null, "matematica");
		Disciplina savedDisciplina = this.disciplinaDAO.save(disciplinaToSave);

		savedDisciplina.setNome("augusto");

		Disciplina updatedDisciplina = this.disciplinaDAO.update(savedDisciplina);
		CustomAsserts.assertDisciplina(savedDisciplina, updatedDisciplina);
	}

}
