package br.com.fatec.aulas.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.dao.MatriculaDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.api.entity.Disciplina;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.dao.DisciplinaDAOImpl;
import br.com.fatec.aulas.core.dao.MatriculaDAOImpl;

/**
 * 
 * @author robson
 *
 * @version 1.0.1
 */
public class MatriculaServiceImpl {

	private EntityDAO<Disciplina> disciplinaDAO;
	private EntityDAO<Aluno> alunoDAO;
	private MatriculaDAO matriculaDAO;

	public MatriculaServiceImpl() {
		this.disciplinaDAO = new DisciplinaDAOImpl();
		this.alunoDAO = new AlunoDAOImpl();
		this.matriculaDAO = new MatriculaDAOImpl();
	}

	public void add(Disciplina disciplina, Aluno... alunoArray) {
		disciplina = this.disciplinaDAO.findById(disciplina.getId());
		Set<Aluno> alunosToAdd = new HashSet<Aluno>();
		alunosToAdd.addAll(Arrays.asList(alunoArray));
		List<Aluno> alunosUtilizados = disciplina.getAlunos() == null ? new ArrayList<Aluno>()
				: disciplina.getAlunos();
		if (alunosUtilizados.size() > 0) {
			for (Aluno alunoToAdd : new ArrayList<Aluno>(alunosToAdd)) {
				if (alunosUtilizados.contains(alunoToAdd)) {
					alunosToAdd.remove(alunoToAdd);
				}
			}
		}
		alunosUtilizados.addAll(alunosToAdd);
		disciplina.setAlunos(alunosUtilizados);
		this.matriculaDAO.update(disciplina);
	}

	public void add(Aluno aluno, Disciplina... disciplinaArray) {
		aluno = this.alunoDAO.findById(aluno.getId());
		Set<Disciplina> disciplinasToAdd = new HashSet<Disciplina>();
		disciplinasToAdd.addAll(Arrays.asList(disciplinaArray));
		List<Disciplina> disciplinasUtilizadas = aluno.getDisciplinas() == null ? new ArrayList<Disciplina>()
				: aluno.getDisciplinas();
		if (disciplinasUtilizadas.size() > 0) {
			for (Disciplina disciplinaToAdd : new ArrayList<Disciplina>(
					disciplinasToAdd)) {
				if (disciplinasUtilizadas.contains(disciplinaToAdd)) {
					disciplinasToAdd.remove(disciplinaToAdd);
				}
			}
		}
		disciplinasUtilizadas.addAll(disciplinasToAdd);
		aluno.setDisciplinas(disciplinasUtilizadas);
		this.matriculaDAO.update(aluno);
	}

	public void add(Aluno aluno, Set<Disciplina> disciplinasToAdd) {
		aluno = this.alunoDAO.findById(aluno.getId());
		List<Disciplina> disciplinasUtilizadas = aluno.getDisciplinas() == null ? new ArrayList<Disciplina>()
				: aluno.getDisciplinas();
		if (disciplinasUtilizadas.size() > 0) {
			for (Disciplina disciplinaToAdd : new ArrayList<Disciplina>(
					disciplinasToAdd)) {
				if (disciplinasUtilizadas.contains(disciplinaToAdd)) {
					disciplinasToAdd.remove(disciplinaToAdd);
				}
			}
		}
		disciplinasUtilizadas.addAll(disciplinasToAdd);
		aluno.setDisciplinas(disciplinasUtilizadas);
		this.matriculaDAO.update(aluno);
	}

	public void remove(Disciplina disciplina, Aluno... alunoArray) {
		disciplina = this.disciplinaDAO.findById(disciplina.getId());

		Set<Aluno> alunosToAdd = new HashSet<Aluno>();
		alunosToAdd.addAll(Arrays.asList(alunoArray));
		List<Aluno> alunosUtilizados = disciplina.getAlunos() == null ? new ArrayList<Aluno>()
				: disciplina.getAlunos();
		if (alunosUtilizados.size() > 0) {
			for (Aluno alunoToAdd : new ArrayList<Aluno>(alunosToAdd)) {
				if (alunosUtilizados.contains(alunoToAdd)) {
					alunosUtilizados.remove(alunoToAdd);
				}
			}
			disciplina.setAlunos(alunosUtilizados);
			this.matriculaDAO.update(disciplina);
		}
	}

	public void remove(Aluno aluno, Disciplina... disciplinaArray) {
		aluno = this.alunoDAO.findById(aluno.getId());

		Set<Disciplina> disciplinasToAdd = new HashSet<Disciplina>();
		disciplinasToAdd.addAll(Arrays.asList(disciplinaArray));
		List<Disciplina> disciplinasUtilizadas = aluno.getDisciplinas() == null ? new ArrayList<Disciplina>()
				: aluno.getDisciplinas();
		if (disciplinasUtilizadas.size() > 0) {
			for (Disciplina disciplinaToAdd : new ArrayList<Disciplina>(
					disciplinasToAdd)) {
				if (disciplinasUtilizadas.contains(disciplinaToAdd)) {
					disciplinasToAdd.remove(disciplinaToAdd);
				}
			}
			aluno.setDisciplinas(disciplinasUtilizadas);
			this.matriculaDAO.update(aluno);
		}
	}

	public List<Disciplina> minimizeList(List<Disciplina> all,
			List<Disciplina> checked) {
		if (checked.size() > 0) {
			for (Disciplina disciplina : new ArrayList<Disciplina>(checked)) {
				all.remove(disciplina);
			}
		}
		return all;
	}

	public void remove(Aluno aluno, Set<Disciplina> disciplinasToRemove) {
		aluno = this.alunoDAO.findById(aluno.getId());

		List<Disciplina> disciplinasUtilizadas = aluno.getDisciplinas() == null ? new ArrayList<Disciplina>() : aluno.getDisciplinas();
		if (disciplinasUtilizadas.size() > 0) {
			for (Disciplina disciplinaToRemove : new ArrayList<Disciplina>(disciplinasToRemove)) {
				if (disciplinasUtilizadas.contains(disciplinaToRemove)) {
					disciplinasUtilizadas.remove(disciplinaToRemove);
				}
			}
			aluno.setDisciplinas(disciplinasUtilizadas);
			this.matriculaDAO.update(aluno);
		}
	}

}
