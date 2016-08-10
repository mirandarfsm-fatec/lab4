package br.com.fatec.aulas.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;

/**
 * @author Carlos
 *
 * @version 1.0.1
 */
public class AlunoFactory {

	/**
	 * @param id
	 * @param nome
	 * @param ra
	 * @param dataNascimento
	 * @param observacao
	 * @return o {@link Aluno} criado a partir dos parametros passados
	 */
	public static Aluno criarAluno(Long id, String nome, String ra,
			Date dataNascimento, String observacao) {
		Aluno aluno = new Aluno();
		aluno.setId(id);
		aluno.setNome(nome);
		aluno.setRa(ra);
		aluno.setDataNascimento(dataNascimento);
		aluno.setObservacao(observacao);
		return aluno;
	}
	
	public static Aluno criarAluno(Long id, String nome, String ra,
			String dataNascimento, String observacao) throws ParseException {
		Aluno aluno = new Aluno();
		aluno.setId(id);
		aluno.setNome(nome);
		aluno.setRa(ra);
		aluno.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(dataNascimento));
		aluno.setObservacao(observacao);
		return aluno;
	}

	public static Aluno criarAluno(Long id, String nome, String ra,
			Date dataNascimento, String observacao, List<Disciplina> disciplinas) {
		Aluno aluno = new Aluno();
		aluno.setId(id);
		aluno.setNome(nome);
		aluno.setRa(ra);
		aluno.setDataNascimento(dataNascimento);
		aluno.setObservacao(observacao);
		aluno.setDisciplinas(disciplinas);
		return aluno;
	}

	public static Aluno criarAluno(ResultSet resultado) {
		try {
			return AlunoFactory.criarAluno(resultado.getLong(Aluno.COL_ID),
					resultado.getString(Aluno.COL_NOME),
					resultado.getString(Aluno.COL_RA),
					resultado.getDate(Aluno.COL_DATA_NASCIMENTO),
					resultado.getString(Aluno.COL_OBSERVACAO));
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar aluno.", e);
		}
	}

	public static List<Aluno> criarAlunos(ResultSet resultado) {
		try {
			List<Aluno> alunos = new ArrayList<Aluno>();
			while (resultado.next()) {
				alunos.add(AlunoFactory.criarAluno(resultado));
			}
			return alunos;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao criar aluno.", e);
		}
	}

}
