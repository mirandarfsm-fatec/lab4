package br.com.fatec.aulas.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.api.entity.Professor;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.dao.ProfessorDAOImpl;
import br.com.fatec.aulas.core.helper.DisciplinaFactory;
import br.com.fatec.aulas.core.helper.ProfessorFactory;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;

public class ProfessorDAOTest extends ConfigDBTestCase {

	private EntityDAO<Professor> professorDAO;
	private ProfessorFactory professorFactory;
	private Disciplina disciplina;
	private EntityDAO<Disciplina> disciplinaDAO;

	@Before
	public void config() {
		this.professorDAO = new ProfessorDAOImpl();
		this.professorFactory = new ProfessorFactory();
		this.disciplinaDAO = new DisciplinaDAOImpl();
		disciplina = DisciplinaFactory.criarDisciplina(null, "matematica");
		disciplina = disciplinaDAO.save(disciplina);
	}

	@Test
	public void saveProfessorTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Professor professorToSave = this.professorFactory.criarProfessor(null, "carlos", "0000000010","carlos@fatec.br", dataNascimento.getTime(),disciplina);
		Professor savedProfessor = this.professorDAO.save(professorToSave);

		CustomAsserts.assertProfessor(professorToSave, savedProfessor);
	}
	
	@Test
	public void saveProfessorDisciplinaNullTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Professor professorToSave = this.professorFactory.criarProfessor(null, "carlos", "0000000010","carlos@fatec.br", dataNascimento.getTime());
		Professor savedProfessor = this.professorDAO.save(professorToSave);

		CustomAsserts.assertProfessor(professorToSave, savedProfessor);
	}

	@Test
	public void findAllTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Professor professorToSave1 = this.professorFactory.criarProfessor(null, "carlos", "0000000010","carlos@fatec.br", dataNascimento.getTime(),disciplina);
		dataNascimento.set(1988, 6, 7);
		Professor professorToSave2 = this.professorFactory.criarProfessor(null, "carlos1", "0000000011","carlos@fatec.br", dataNascimento.getTime(),disciplina);
		dataNascimento.set(1988, 6, 9);
		Professor professorToSave3 = this.professorFactory.criarProfessor(null, "carlos2", "0000000012","carlos@fatec.br", dataNascimento.getTime(),disciplina);

		List<Professor> experados = new ArrayList<Professor>();

		experados.add(this.professorDAO.save(professorToSave1));
		experados.add(this.professorDAO.save(professorToSave2));
		experados.add(this.professorDAO.save(professorToSave3));

		List<Professor> professores = this.professorDAO.findAll();
		Assert.assertEquals(3, professores.size());
		CustomAsserts.assertProfessores(experados, professores);
	}

	@Test
	public void removeProfessorTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Professor professorToSave = this.professorFactory.criarProfessor(null, "carlos", "0000000010","carlos@fatec.br", dataNascimento.getTime(),disciplina);
		Professor savedProfessor = this.professorDAO.save(professorToSave);

		CustomAsserts.assertProfessor(professorToSave, savedProfessor);
		this.professorDAO.remove(savedProfessor);

		Assert.assertNull(this.professorDAO.findById(savedProfessor.getId()));
	}

	@Test
	public void updateProfessorTest() {
		Disciplina disciplina2 = DisciplinaFactory.criarDisciplina(null, "fisica");
		disciplina2 = disciplinaDAO.save(disciplina2);
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Professor professorToSave = this.professorFactory.criarProfessor(null, "carlos", "0000000010","carlos@fatec.br", dataNascimento.getTime(),disciplina);
		Professor savedProfessor = this.professorDAO.save(professorToSave);

		savedProfessor.setNome("augusto");
		savedProfessor.setRp("982182807497412");
		dataNascimento.set(1988, 6, 4);
		savedProfessor.setDataNascimento(dataNascimento.getTime());
		savedProfessor.setDisciplina(disciplina2);

		Professor updatedProfessor = this.professorDAO.update(savedProfessor);
		CustomAsserts.assertProfessor(savedProfessor, updatedProfessor);
	}
	
}
