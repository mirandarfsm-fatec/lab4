package br.com.fatec.aulas.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.api.entity.Professor;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;

public class ProfessorFactory {

	/**
	 * @param id
	 * @param nome
	 * @param rp
	 * @param dataNascimento
	 * @param email
	 * @return o {@link Professor} criado a partir dos parametros passados
	 */
	public static Professor criarProfessor(Long id, String nome, String rp,
			String email, Date dataNascimento) {
		Professor professor = new Professor();
		professor.setId(id);
		professor.setNome(nome);
		professor.setRp(rp);
		professor.setEmail(email);
		professor.setDataNascimento(dataNascimento);
		return professor;
	}

	/**
	 * @param id
	 * @param nome
	 * @param rp
	 * @param dataNascimento
	 * @param email
	 * @param idDisciplina
	 * @return o {@link Professor} criado a partir dos parametros passados
	 */
	public static Professor criarProfessor(Long id, String nome, String rp,
			String email, Date dataNascimento, Long idDisciplina) {
		Professor professor = new Professor();
		professor.setId(id);
		professor.setNome(nome);
		professor.setRp(rp);
		professor.setEmail(email);
		professor.setDataNascimento(dataNascimento);
		professor.setDisciplina(new DisciplinaDAOImpl().findById(idDisciplina));
		return professor;
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 * @param rp
	 * @param email
	 * @param dataNascimento
	 * @param disciplina
	 * @return {@link Professor} criado a partir dos parametros passados
	 */
	public static Professor criarProfessor(Long id, String nome, String rp,
			String email, Date dataNascimento, Disciplina disciplina) {
		Professor professor = new Professor();
		professor.setId(id);
		professor.setNome(nome);
		professor.setRp(rp);
		professor.setEmail(email);
		professor.setDataNascimento(dataNascimento);
		professor.setDisciplina(disciplina);
		return professor;
	}

	/**
	 * @param resultado
	 * @return {@link Professor}
	 */
	public static Professor criarProfessor(ResultSet resultado) {
		try {
			return ProfessorFactory.criarProfessor(resultado.getLong(Professor.COL_ID),
					resultado.getString(Professor.COL_NOME),
					resultado.getString(Professor.COL_RP),
					resultado.getString(Professor.COL_EMAIL),
					resultado.getDate(Professor.COL_DATA_NASCIMENTO),
					resultado.getLong(Professor.COL_ID_DISCIPLINA));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar professor.", e);
		}
	}

	/**
	 * @param resultado
	 * @return {@link List}
	 */
	public static List<Professor> criarProfessores(ResultSet resultado) {
		try {
			List<Professor> professor = new ArrayList<Professor>();
			while (resultado.next()) {
				professor.add(ProfessorFactory.criarProfessor(resultado));
			}
			return professor;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar aluno.", e);
		}
	}

}
