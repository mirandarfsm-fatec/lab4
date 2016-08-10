package br.com.fatec.aulas.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.aulas.api.dao.MatriculaDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.core.helper.AlunoFactory;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.helper.DisciplinaFactory;

/**
 * @author Robson
 *
 * @version 1.0.1
 */
public class MatriculaDAOImpl implements MatriculaDAO{

	Connection connection;
	private DisciplinaFactory disciplinaFactory;
	private AlunoFactory alunoFactory;

	/** */
	public MatriculaDAOImpl() {
		this.disciplinaFactory = new DisciplinaFactory();
		this.alunoFactory = new AlunoFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	public void update(Disciplina disciplina) {
		if (disciplina.getAlunos() != null) {
			try {
				PreparedStatement deleteAlunos = connection
						.prepareStatement("DELETE FROM FATEC_ALUNO_DISCIPLINA WHERE ID_DISCIPLINA = ?");
				deleteAlunos.setLong(1, disciplina.getId());
				deleteAlunos.execute();
				deleteAlunos.close();
				for (Aluno aluno : disciplina.getAlunos()) {
					PreparedStatement insert = connection
							.prepareStatement("INSERT INTO FATEC_ALUNO_DISCIPLINA (ID_ALUNO,ID_DISCIPLINA) VALUES (?,?)");
					insert.setLong(1, aluno.getId());
					insert.setLong(2, disciplina.getId());
					insert.execute();
					insert.close();
					connection.commit();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
	}

	public void update(Aluno aluno) {
		if (aluno.getDisciplinas() != null) {
			try {
				PreparedStatement deleteDisciplinas = connection
						.prepareStatement("DELETE FROM FATEC_ALUNO_DISCIPLINA WHERE ID_ALUNO = ?");
				deleteDisciplinas.setLong(1, aluno.getId());
				deleteDisciplinas.execute();
				deleteDisciplinas.close();
				for (Disciplina disciplina : aluno.getDisciplinas()) {
					PreparedStatement insert = connection
							.prepareStatement("INSERT INTO FATEC_ALUNO_DISCIPLINA (ID_ALUNO,ID_DISCIPLINA) VALUES (?,?)");
					insert.setLong(1, aluno.getId());
					insert.setLong(2, disciplina.getId());
					insert.execute();
					insert.close();
					connection.commit();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}

	}
	
	public List<Disciplina> getDisciplinas(Long id){
		PreparedStatement query = null;

		try {
			query = this.connection.prepareStatement("SELECT d."
					+ Disciplina.COL_ID + ",d." + Disciplina.COL_NOME
					+ " FROM FATEC_ALUNO_DISCIPLINA ad, " + Disciplina.TABLE
					+ " d WHERE d." + Disciplina.COL_ID
					+ "= ad.ID_DISCIPLINA AND ID_ALUNO = ?");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			List<Disciplina> disciplinas = this.disciplinaFactory.criarDisciplinas(resultado);
			connection.commit();
			return disciplinas;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar disciplinas.", e);
		}

	}

	public Aluno getDisciplinas(Aluno aluno) {
		aluno.setDisciplinas(this.getDisciplinas(aluno.getId()));
		return aluno;
	}
	
	public List<Aluno> getAlunos(Long id) {
		PreparedStatement query = null;
		try {
			query = this.connection.prepareStatement("SELECT a." + Aluno.COL_ID
					+ ",a." + Aluno.COL_NOME + ",a." + Aluno.COL_RA + ",a."
					+ Aluno.COL_DATA_NASCIMENTO + ",a." + Aluno.COL_OBSERVACAO
					+ " FROM FATEC_ALUNO_DISCIPLINA ad, " + Aluno.TABLE
					+ " a WHERE a." + Aluno.COL_ID
					+ "= ad.ID_ALUNO AND ad.ID_DISCIPLINA= ?");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			List<Aluno> alunos = this.alunoFactory.criarAlunos(resultado);
			connection.commit();
			return alunos;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar alunos.", e);
		}
	}

	public Disciplina getAlunos(Disciplina disciplina) {
		disciplina.setAlunos(this.getAlunos(disciplina.getId()));
		return disciplina;
	}

}
