package br.com.fatec.aulas.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.dao.MatriculaDAOImpl;
import br.com.fatec.aulas.core.helper.AlunoFactory;
import br.com.fatec.aulas.core.helper.DisciplinaFactory;
import br.com.fatec.aulas.test.commons.ConfigCenarioTestCase;

public class MatriculaDAOTest extends ConfigCenarioTestCase {

	private EntityDAO<Aluno> alunoDAO;
	private EntityDAO<Disciplina> disciplinaDAO;
	private MatriculaDAOImpl matriculaDAO;

	@Before
	public void config() {
		this.alunoDAO = new AlunoDAOImpl();
		this.disciplinaDAO = new DisciplinaDAOImpl();
		this.matriculaDAO = new MatriculaDAOImpl();

	}

	@Test
	public void saveAlunoComUmaDisciplinaTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		List<Disciplina> disciplinas = new ArrayList<Disciplina>();

		disciplinas.add(this.disciplinaDAO.findAll().get(0));

		Aluno alunoToSave = AlunoFactory.criarAluno(null, "carlos",
				"0000000010", dataNascimento.getTime(), null, disciplinas);
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);
		savedAluno = this.matriculaDAO.getDisciplinas(savedAluno);

		Assert.assertEquals(alunoToSave.getDisciplinas(),
				savedAluno.getDisciplinas());
		Assert.assertEquals(alunoToSave.getDisciplinas().size(), savedAluno
				.getDisciplinas().size());
	}

	@Test
	public void saveAlunoComMultiplasDisciplinasTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		List<Disciplina> disciplinas = this.disciplinaDAO.findAll();

		Aluno alunoToSave = AlunoFactory.criarAluno(null, "carlos",
				"0000000010", dataNascimento.getTime(), null, disciplinas);
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);
		savedAluno = this.matriculaDAO.getDisciplinas(savedAluno);

		Assert.assertEquals(alunoToSave.getDisciplinas(),
				savedAluno.getDisciplinas());
		Assert.assertEquals(alunoToSave.getDisciplinas().size(), savedAluno
				.getDisciplinas().size());
	}

	@Test
	public void saveDisciplinaComUmAlunoTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(this.alunoDAO.findAll().get(0));

		Disciplina disciplinaToSave = DisciplinaFactory.criarDisciplina(null,
				"matematica", alunos);
		Disciplina savedDisciplina = this.disciplinaDAO.save(disciplinaToSave);
		savedDisciplina = this.matriculaDAO.getAlunos(savedDisciplina);

		Assert.assertEquals(disciplinaToSave.getAlunos(),
				savedDisciplina.getAlunos());
		Assert.assertEquals(disciplinaToSave.getAlunos().size(),
				savedDisciplina.getAlunos().size());
	}

	@Test
	public void saveDisciplinaComMultiplosAlunoTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		List<Aluno> alunos = this.alunoDAO.findAll();

		Disciplina disciplinaToSave = DisciplinaFactory.criarDisciplina(null,
				"matematica", alunos);
		Disciplina savedDisciplina = this.disciplinaDAO.save(disciplinaToSave);
		savedDisciplina = this.matriculaDAO.getAlunos(savedDisciplina);

		Assert.assertEquals(disciplinaToSave.getAlunos(),
				savedDisciplina.getAlunos());
		Assert.assertEquals(disciplinaToSave.getAlunos().size(),
				savedDisciplina.getAlunos().size());
	}

}
