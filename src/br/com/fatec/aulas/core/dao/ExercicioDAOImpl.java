package br.com.fatec.aulas.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Exercicio;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.core.helper.ExercicioFactory;
import br.com.fatec.aulas.core.service.GeradorIdService;

/**
 * @author Miranda
 *
 * @version 1.0.1
 */
public class ExercicioDAOImpl implements EntityDAO<Exercicio> {

	private Connection connection;
	private ExercicioFactory exercicioFactory;

	/** */
	public ExercicioDAOImpl() {
		this.exercicioFactory = new ExercicioFactory();
		this.connection = ConfigDBMapper.getInstance().getDefaultConnection();
	}

	@Override
	public Exercicio save(Exercicio exercicio) {
		PreparedStatement insert = null;
		try {
			insert = this.connection.prepareStatement("INSERT INTO " + Exercicio.TABLE + " VALUES (?,?,?,?)");
			Long id = GeradorIdService.getInstance().getNextId(Exercicio.TABLE);
			insert.setLong(1, id);
			insert.setString(2, exercicio.getPergunta());
			insert.setString(3, exercicio.getResposta());
			insert.setLong(4, exercicio.getDisciplina().getId());
			insert.execute();
			connection.commit();
			return this.findById(id);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar exercicio.", e);
		}
	}

	@Override
	public void remove(Exercicio exercicio) {
		PreparedStatement remove = null;
		try {
			remove = this.connection.prepareStatement("DELETE FROM "
					+ Exercicio.TABLE + " WHERE " + Exercicio.COL_ID + " = ?");
			remove.setLong(1, exercicio.getId());
			remove.execute();
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao remover exercicio "
					+ exercicio.getPergunta(), e);
		}
	}

	@Override
	public List<Exercicio> findAll() {
		PreparedStatement query = null;
		List<Exercicio> exercicios = new ArrayList<Exercicio>();
		try {
			query = this.connection.prepareStatement("SELECT * FROM "
					+ Exercicio.TABLE);
			ResultSet resultado = query.executeQuery();
			exercicios = this.exercicioFactory.criarExercicios(resultado);
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar exercicios.", e);
		}

		return exercicios;
	}

	@Override
	public Exercicio findById(Long id) {
		PreparedStatement query = null;
		Exercicio exercicio = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM "
					+ Exercicio.TABLE + " WHERE " + Exercicio.COL_ID + " = ?");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				exercicio = this.exercicioFactory.criarExercicio(resultado);
				if (resultado.next()) {
					throw new RuntimeException("O ID " + id + " está duplicado");
				}
			}
			connection.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar exercício.", e);
		}

		return exercicio;
	}

	@Override
	public Exercicio update(Exercicio exercicio) {
		PreparedStatement update = null;
		try {
			update = this.connection.prepareStatement("UPDATE "
					+ Exercicio.TABLE + " SET " + Exercicio.COL_PERGUNTA
					+ " = ?," + Exercicio.COL_RESPOSTA + " = ?,"
					+ Exercicio.COL_ID_DISCIPLINA + " = ? WHERE "
					+ Exercicio.COL_ID + " = ?");
			update.setString(1, exercicio.getPergunta());
			update.setString(2, exercicio.getResposta());
			update.setLong(3, exercicio.getDisciplina().getId());
			update.setLong(4, exercicio.getId());
			update.execute();
			connection.commit();
			return this.findById(exercicio.getId());
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar exercicio "
					+ exercicio.getPergunta(), e);
		}
	}

	public List<Exercicio> getExercicios(Long id) {
		PreparedStatement query = null;
		try {
			query = this.connection.prepareStatement("SELECT * FROM " + Exercicio.TABLE + " WHERE " + Exercicio.COL_ID_DISCIPLINA + "= ?");
			query.setLong(1, id);
			ResultSet resultado = query.executeQuery();
			List<Exercicio> exercicios = ExercicioFactory
					.criarExercicios(resultado);
			connection.commit();
			return exercicios;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar Exercicio.", e);
		}
	}

}
