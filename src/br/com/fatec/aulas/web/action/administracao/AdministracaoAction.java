package br.com.fatec.aulas.web.action.administracao;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.IdentifiedEntity;

import com.opensymphony.xwork2.ActionSupport;

@Getter
@Setter
public class AdministracaoAction<T extends IdentifiedEntity> extends ActionSupport {

	private static final long serialVersionUID = 1L;

	protected static final String FORM = "form";
	protected static final String LIST = "list";

	private T t;
	protected List<T> listaEntidade;
	protected EntityDAO<T> dao;

	public String carregarLista() {
		this.listaEntidade = this.dao.findAll();
		return LIST;
	}

	public String instanciarNovo() {
		return FORM;
	}

	public String buscar() {
		this.t = this.dao.findById((this.t).getId());
		return FORM;
	}

	public String salvar() {
		if (this.t.getId() == null) {
			this.t = this.dao.save(this.t);
		} else {
			this.t = this.dao.update(this.t);
		}
		this.carregarLista();
		return LIST;
	}

	public String remover() {
		this.dao.remove(this.t);
		this.carregarLista();
		return LIST;
	}

}
