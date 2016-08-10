package br.com.fatec.aulas.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.core.helper.AlunoFactory;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.service.GeradorIdService;

/**
 * @author Carlos
 *
 * @version
 */
public class AlunoDAOImpl implements EntityDAO<Aluno> {

	private Connection connection;
	private AlunoFactory alunoFactory;
	private MatriculaDAOImpl matriculaDao;

	/** */
	public AlunoDAOImpl() {
		this.alunoFactory = new AlunoFactory();
		this.matriculaDao = new MatriculaDAOImpl();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	@Override
	public Aluno save(Aluno aluno) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO "+ Aluno.TABLE + " VALUES (?,?,?,?,?)");
			aluno.setId(GeradorIdService.getInstance().getNextId(Aluno.TABLE));
			insert.setLong(1, aluno.getId());
			insert.setString(2, aluno.getNome());
			insert.setString(3, aluno.getRa());
			insert.setDate(4, new Date(aluno.getDataNascimento().getTime()));
			insert.setString(5, aluno.getObservacao());
			insert.execute();
			this.matriculaDao.update(aluno);
			connection.commit();
			return this.findById(aluno.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar aluno.", e);
		}
	}

	@Override
	public void remove(Aluno aluno) {
		PreparedStatement remove = null;
		try {
			remove = this.connection.prepareStatement("DELETE FROM "
					+ Aluno.TABLE + " WHERE " + Aluno.COL_ID + " = ?");
			remove.setLong(1, aluno.getId());
			remove.execute();
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover aluno "
					+ aluno.getNome(), e);
		}
	}

	@Override
	public List<Aluno> findAll() {
		PreparedStatement query = null;
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM "
					+ Aluno.TABLE);
			ResultSet resultado = query.executeQuery();
			alunos = this.alunoFactory.criarAlunos(resultado);
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar alunos.", e);
		}

		return alunos;
	}

	@Override
	public Aluno findById(Long id) {
		PreparedStatement query = null;
		Aluno aluno = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Aluno.TABLE + " WHERE " + Aluno.COL_ID + " = ?");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				aluno = this.alunoFactory.criarAluno(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar aluno.", e);
		}

		return aluno;
	}

	@Override
	public Aluno update(Aluno aluno) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE " + Aluno.TABLE
					+ " SET " + Aluno.COL_NOME + " = ?," + Aluno.COL_RA
					+ " = ?," + Aluno.COL_DATA_NASCIMENTO + " = ?," + Aluno.COL_OBSERVACAO+ " = ? WHERE "
					+ Aluno.COL_ID + " = ?");
			update.setString(1, aluno.getNome());
			update.setString(2, aluno.getRa());
			update.setDate(3, new Date(aluno.getDataNascimento().getTime()));
			update.setString(4, aluno.getObservacao());
			update.setLong(5, aluno.getId());
			update.execute();
			connection.commit();
			return this.findById(aluno.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar aluno "
					+ aluno.getNome(), e);
		}
	}
}
