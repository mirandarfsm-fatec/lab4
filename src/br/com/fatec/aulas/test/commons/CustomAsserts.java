package br.com.fatec.aulas.test.commons;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.api.entity.Exercicio;
import br.com.fatec.aulas.api.entity.Professor;

/**
 * @author Carlos
 *
 * @version
 */
public class CustomAsserts {

	public static void assertDate(Date expected, Date actual) {
		Calendar cExpected = Calendar.getInstance();
		Calendar cActual = Calendar.getInstance();
		cExpected.setTimeInMillis(expected.getTime());
		cActual.setTimeInMillis(actual.getTime());

		Assert.assertEquals(cExpected.get(Calendar.YEAR),
				cActual.get(Calendar.YEAR));
		Assert.assertEquals(cExpected.get(Calendar.MONTH),
				cActual.get(Calendar.MONTH));
		Assert.assertEquals(cExpected.get(Calendar.DAY_OF_MONTH),
				cActual.get(Calendar.DAY_OF_MONTH));

	}

	public static void assertAluno(Aluno expected, Aluno actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getRa(), actual.getRa());
		CustomAsserts.assertDate(expected.getDataNascimento(),
				actual.getDataNascimento());
	}

	public static void assertAlunos(List<Aluno> expected, List<Aluno> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertAluno(expected.get(i), actual.get(i));
		}
	}

	public static void assertDisciplina(Disciplina expected, Disciplina actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
	}

	public static void assertDisciplinas(List<Disciplina> expected,
			List<Disciplina> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertDisciplina(expected.get(i), actual.get(i));
		}
	}

	public static void assertProfessor(Professor expected, Professor actual) {
		Assert.assertEquals(expected.getNome(), actual.getNome());
		Assert.assertEquals(expected.getRp(), actual.getRp());
		Assert.assertEquals(expected.getEmail(), actual.getEmail());
		if(expected.getDisciplina() != null || actual.getDisciplina() != null){
			Assert.assertEquals(expected.getDisciplina().getId(), actual.getDisciplina().getId());
		}
		CustomAsserts.assertDate(expected.getDataNascimento(),actual.getDataNascimento());
	}

	public static void assertProfessores(List<Professor> expected,
			List<Professor> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertProfessor(expected.get(i), actual.get(i));
		}
	}

	public static void assertExercicio(Exercicio expected, Exercicio actual) {
		Assert.assertEquals(expected.getPergunta(), actual.getPergunta());
		Assert.assertEquals(expected.getResposta(), actual.getResposta());
		Assert.assertEquals(expected.getDisciplina().getId(), actual
				.getDisciplina().getId());
	}

	public static void assertExercicios(List<Exercicio> expected,
			List<Exercicio> actual) {
		for (int i = 0; i < expected.size(); i++) {
			assertExercicio(expected.get(i), actual.get(i));
		}
	}

}
