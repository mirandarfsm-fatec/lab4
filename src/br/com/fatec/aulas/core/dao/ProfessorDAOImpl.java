package br.com.fatec.aulas.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Professor;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.helper.ProfessorFactory;
import br.com.fatec.aulas.core.service.GeradorIdService;

public class ProfessorDAOImpl implements EntityDAO<Professor> {

	private Connection connection;
	private ProfessorFactory professorFactory;

	/** */
	public ProfessorDAOImpl() {
		this.professorFactory = new ProfessorFactory();
		try {
			this.connection = ConfigDBMapper.getInstance()
					.getDefaultConnection();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao criar conexão.", e);
		}
	}

	@Override
	public Professor save(Professor professor) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO "
					+ Professor.TABLE + " VALUES (?,?,?,?,?,?)");
			Long id = GeradorIdService.getInstance().getNextId(Professor.TABLE);
			insert.setLong(1, id);
			insert.setString(2, professor.getNome());
			insert.setString(3, professor.getRp());
			insert.setString(4, professor.getEmail());
			insert.setDate(5, new Date(professor.getDataNascimento().getTime()));
			if (professor.getDisciplina() != null) {
				insert.setLong(6, professor.getDisciplina().getId());
			} else {
				insert.setNull(6, Types.BIGINT);
			}
			insert.execute();
			connection.commit();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar Professor.", e);
		}
	}

	@Override
	public void remove(Professor professor) {
		PreparedStatement remove = null;
		try {
			remove = this.connection.prepareStatement("DELETE FROM "
					+ Professor.TABLE + " WHERE " + Professor.COL_ID + " = ?");
			remove.setLong(1, professor.getId());
			remove.execute();
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover aluno "
					+ professor.getNome(), e);
		}
	}

	@Override
	public List<Professor> findAll() {
		PreparedStatement query = null;
		List<Professor> professores = new ArrayList<Professor>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM "
					+ Professor.TABLE);
			ResultSet resultado = query.executeQuery();
			professores = this.professorFactory.criarProfessores(resultado);
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar professores.", e);
		}

		return professores;
	}

	@Override
	public Professor findById(Long id) {
		PreparedStatement query = null;
		Professor professor = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM "
					+ Professor.TABLE + " WHERE " + Professor.COL_ID + " = ?");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				professor = this.professorFactory.criarProfessor(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar professor.", e);
		}

		return professor;
	}

	@Override
	public Professor update(Professor professor) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE "
					+ Professor.TABLE + " SET " + Professor.COL_NOME + " = ?,"
					+ Professor.COL_RP + " = ?," + Professor.COL_EMAIL
					+ " = ?," + Professor.COL_DATA_NASCIMENTO + " = ?,"
					+ Professor.COL_ID_DISCIPLINA + " = ? WHERE "
					+ Professor.COL_ID + " = ?");
			update.setString(1, professor.getNome());
			update.setString(2, professor.getRp());
			update.setString(3, professor.getEmail());
			update.setDate(4, new Date(professor.getDataNascimento().getTime()));
			if (professor.getDisciplina() != null) {
				update.setLong(5, professor.getDisciplina().getId());
			} else {
				update.setNull(5, Types.BIGINT);
			}
			update.setLong(6, professor.getId());
			update.execute();
			connection.commit();
			return this.findById(professor.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar professor "
					+ professor.getNome(), e);
		}
	}

	public List<Professor> getProfessores(Long id) {
		PreparedStatement query = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Professor.TABLE + " WHERE " + Professor.COL_ID_DISCIPLINA + "= ?");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			List<Professor> professores = ProfessorFactory.criarProfessores(resultado);
			connection.commit();
			return professores;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar Professores.", e);
		}
	}
}
