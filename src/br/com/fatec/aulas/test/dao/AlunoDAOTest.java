package br.com.fatec.aulas.test.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.helper.AlunoFactory;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;

/**
 * @author Carlos
 *
 * @version
 */
public class AlunoDAOTest extends ConfigDBTestCase{

	private EntityDAO<Aluno> alunoDAO;

	@Before
	public void config() {
		this.alunoDAO = new AlunoDAOImpl();
	}


	@Test
	public void saveAlunoTest() {
		//org.hsqldb.util.DatabaseManagerSwing.main(new String[] {"--url", "jdbc:mem:hsqldb:test","--noexit"});
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);


		Aluno alunoToSave = AlunoFactory.criarAluno(null, "carlos", "0000000010", dataNascimento.getTime(), null);
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);

		CustomAsserts.assertAluno(alunoToSave, savedAluno);
	}

	@Test
	public void findAllTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Aluno alunoToSave1 = AlunoFactory.criarAluno(null, "carlos", "0000000010", dataNascimento.getTime(), null);
		dataNascimento.set(1988, 6, 7);
		Aluno alunoToSave2 = AlunoFactory.criarAluno(null, "carlos1", "0000000011", dataNascimento.getTime(), null);
		dataNascimento.set(1988, 6, 9);
		Aluno alunoToSave3 = AlunoFactory.criarAluno(null, "carlos2", "0000000012", dataNascimento.getTime(), null);

		List<Aluno> experados = new ArrayList<Aluno>();

		experados.add(this.alunoDAO.save(alunoToSave1));
		experados.add(this.alunoDAO.save(alunoToSave2));
		experados.add(this.alunoDAO.save(alunoToSave3));

		List<Aluno> alunos = this.alunoDAO.findAll();
		Assert.assertEquals(3, alunos.size());
		CustomAsserts.assertAlunos(experados, alunos);
	}

	@Test
	public void removeAlunoTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Aluno alunoToSave = AlunoFactory.criarAluno(null, "carlos", "0000000010", dataNascimento.getTime(), null);
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);

		CustomAsserts.assertAluno(alunoToSave, savedAluno);
		this.alunoDAO.remove(savedAluno);

		Assert.assertNull(this.alunoDAO.findById(savedAluno.getId()));
	}

	@Test
	public void updateAlunoTest() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.set(1988, 6, 5);

		Aluno alunoToSave = AlunoFactory.criarAluno(null, "carlos", "0000000010", dataNascimento.getTime(), null);
		Aluno savedAluno = this.alunoDAO.save(alunoToSave);

		savedAluno.setNome("augusto");
		savedAluno.setRa("982182807497412");
		dataNascimento.set(1988, 6, 4);
		savedAluno.setDataNascimento(dataNascimento.getTime());

		Aluno updatedAluno = this.alunoDAO.update(savedAluno);
		CustomAsserts.assertAluno(savedAluno, updatedAluno);
	}
}
