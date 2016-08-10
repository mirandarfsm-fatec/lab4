package br.com.fatec.aulas.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.api.entity.Exercicio;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;

/**
 * @author Miranda
 *
 * @version 1.0.1
 */
public class ExercicioFactory {

	/**
	 * @param id
	 * @param pergunta
	 * @param resposta
	 * @param idDisciplina
	 * @return o {@link Exercicio} criado a partir dos parametros passados
	 */
	public static Exercicio criarExercicio(Long id, String pergunta, String resposta, Long idDisciplina) {
		Exercicio exercicio = new Exercicio();
		exercicio.setId(id);
		exercicio.setPergunta(pergunta);
		exercicio.setResposta(resposta);
		exercicio.setDisciplina(new DisciplinaDAOImpl().findById(idDisciplina));
		return exercicio;
	}
	
	/**
	 * @param id
	 * @param pergunta
	 * @param resposta
	 * @param disciplina
	 * @return {@link Exercicio}
	 */
	public static Exercicio criarExercicio(Long id, String pergunta, String resposta, Disciplina disciplina) {
		Exercicio exercicio = new Exercicio();
		exercicio.setId(id);
		exercicio.setPergunta(pergunta);
		exercicio.setResposta(resposta);
		exercicio.setDisciplina(disciplina);
		return exercicio;
	}
	
	/**
	 * @param resultado
	 * @return {@link Exercicio}
	 */
	public static Exercicio criarExercicio(ResultSet resultado) {
		try {
			return ExercicioFactory.criarExercicio(resultado.getLong(Exercicio.COL_ID),
					resultado.getString(Exercicio.COL_PERGUNTA),
					resultado.getString(Exercicio.COL_RESPOSTA),
					resultado.getLong(Exercicio.COL_ID_DISCIPLINA));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar exercicio.", e);
		}
	}

	/**
	 * @param resultado
	 * @return {@link List} {@link Exercicio}
	 */
	public static List<Exercicio> criarExercicios(ResultSet resultado) {
		try {
			List<Exercicio> exercicios = new ArrayList<Exercicio>();
			while (resultado.next()) {
				exercicios.add(ExercicioFactory.criarExercicio(resultado));
			}
			return exercicios;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar exercicio.", e);
		}
	}
}
