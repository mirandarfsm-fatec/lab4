package br.com.fatec.aulas.web.action.administracao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;

@Getter
@Setter
public class AlunoAdministracaoAction extends AdministracaoAction<Aluno> {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private String dataNascimento;

	public AlunoAdministracaoAction() {
		this.setDao(new AlunoDAOImpl());
	}

	@Override
	public String salvar() {
		try {
			this.getT().setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(this.dataNascimento));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return super.salvar();
	}

}
