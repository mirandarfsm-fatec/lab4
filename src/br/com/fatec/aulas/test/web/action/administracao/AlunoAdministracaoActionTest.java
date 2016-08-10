package br.com.fatec.aulas.test.web.action.administracao;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.helper.AlunoFactory;
import br.com.fatec.aulas.test.commons.ConfigCenarioTestCase;
import br.com.fatec.aulas.test.commons.CustomAsserts;
import br.com.fatec.aulas.web.action.administracao.AlunoAdministracaoAction;

public class AlunoAdministracaoActionTest extends ConfigCenarioTestCase{

	private AlunoAdministracaoAction alunoAction;
	private EntityDAO<Aluno> alunoDao;
	
	@Before
	public void setUp() {
		this.alunoAction = new AlunoAdministracaoAction();
		this.alunoDao = new AlunoDAOImpl();
	}

	@Test
	public void testInstanciarNovo() {
		Assert.assertEquals("form", alunoAction.instanciarNovo());
	}

	@Test
	public void testBuscar() throws ParseException {
		Aluno aluno = AlunoFactory.criarAluno(1L, "Carlos" ,"010101001" , "1991-06-03" ,"");
		alunoAction.setT(aluno);
		alunoAction.buscar();
		CustomAsserts.assertAluno(aluno, alunoAction.getT());
	}

	@Test
	public void testSalvar() throws ParseException {
		Aluno alunoToSave = AlunoFactory.criarAluno(null, "Joana" ,"010111111" , "1993-08-03" ,"");
		alunoAction.setDataNascimento("1993-08-03");
		alunoAction.setT(alunoToSave);
		this.alunoAction.salvar();
		Aluno alunoSaved = alunoAction.getT();

		CustomAsserts.assertAluno(alunoToSave, alunoSaved);
	}

	@Test
	public void testUpdate() throws ParseException {
		Aluno alunoToUpdate = AlunoFactory.criarAluno(1L, "Carlos" ,"010101001" , "1991-06-03" ,"");
		alunoAction.setDataNascimento("1991-06-03");
		alunoAction.setT(alunoToUpdate);
		this.alunoAction.salvar();
		Aluno alunoUpdated = alunoAction.getT();

		CustomAsserts.assertAluno(alunoToUpdate,alunoUpdated);
	}

	@Test
	public void testRemover() throws ParseException {
		Aluno alunoToSave = AlunoFactory.criarAluno(null,"Joaquim" ,"010111111" , "1993-08-03" ,"");
		Aluno savedAluno = this.alunoDao.save(alunoToSave);

		CustomAsserts.assertAluno(alunoToSave, savedAluno);
		this.alunoAction.setT(savedAluno);
		this.alunoAction.remover();

		Assert.assertNull(this.alunoDao.findById(savedAluno.getId()));

	}

}
