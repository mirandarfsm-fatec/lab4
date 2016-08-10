package br.com.fatec.aulas.core.service;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Exercicio;
import br.com.fatec.aulas.core.dao.ExercicioDAOImpl;

public class ExercicioServiceImpl {

	private EntityDAO<Exercicio> exercicioDao;
	public ExercicioServiceImpl(){
		this.exercicioDao = new ExercicioDAOImpl();
	}
	
	public Boolean isCorreto(Exercicio exercicio) {
		Exercicio exercicioCorreto = exercicioDao.findById(exercicio.getId());
		return exercicio.getResposta().toLowerCase().equals(exercicioCorreto.getResposta().toLowerCase());
	}

}
