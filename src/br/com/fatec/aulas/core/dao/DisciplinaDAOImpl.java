package br.com.fatec.aulas.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.helper.DisciplinaFactory;
import br.com.fatec.aulas.core.service.GeradorIdService;

/**
 * @author Miranda
 *
 * @version 1.0.0
 */
public class DisciplinaDAOImpl implements EntityDAO<Disciplina> {

	private Connection connection;
	private DisciplinaFactory disciplinaFactory;
	private MatriculaDAOImpl matriculaDao;

	/** */
	public DisciplinaDAOImpl() {
		this.disciplinaFactory = new DisciplinaFactory();
		this.matriculaDao = new MatriculaDAOImpl();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	@Override
	public Disciplina save(Disciplina disciplina) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO "
					+ Disciplina.TABLE + " VALUES (?,?)");
			disciplina.setId(GeradorIdService.getInstance().getNextId(
					Disciplina.TABLE));
			insert.setLong(1, disciplina.getId());
			insert.setString(2, disciplina.getNome());
			insert.execute();
			connection.commit();
			this.matriculaDao.update(disciplina);
			return this.findById(disciplina.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar disciplina.", e);
		}
	}

	@Override
	public void remove(Disciplina disciplina) {
		PreparedStatement remove = null;
		try {
			remove = this.connection
					.prepareStatement("DELETE FROM " + Disciplina.TABLE
							+ " WHERE " + Disciplina.COL_ID + " = ?");
			remove.setLong(1, disciplina.getId());
			remove.execute();
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover disciplina "
					+ disciplina.getNome(), e);
		}
	}

	@Override
	public List<Disciplina> findAll() {
		PreparedStatement query = null;
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM "
					+ Disciplina.TABLE);
			ResultSet resultado = query.executeQuery();
			disciplinas = this.disciplinaFactory.criarDisciplinas(resultado);
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar disciplinas.", e);
		}

		return disciplinas;
	}

	@Override
	public Disciplina findById(Long id) {
		PreparedStatement query = null;
		Disciplina disciplina = null;
		try {
			query = this.connection
					.prepareStatement("SELECT * FROM " + Disciplina.TABLE
							+ " WHERE " + Disciplina.COL_ID + " = ?");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				disciplina = this.disciplinaFactory.criarDisciplina(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar disciplina.", e);
		}

		return disciplina;
	}

	@Override
	public Disciplina update(Disciplina disciplina) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE "
					+ Disciplina.TABLE + " SET " + Disciplina.COL_NOME
					+ " = ? WHERE " + Disciplina.COL_ID + " = ?");
			update.setString(1, disciplina.getNome());
			update.setLong(2, disciplina.getId());
			update.execute();
			connection.commit();
			return this.findById(disciplina.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar disciplina "
					+ disciplina.getNome(), e);
		}
	}

}
