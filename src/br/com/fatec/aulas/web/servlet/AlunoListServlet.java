package br.com.fatec.aulas.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;

@WebServlet("/aluno-list")
public class AlunoListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EntityDAO<Aluno> alunoDao = new AlunoDAOImpl();
		List<Aluno> alunosSalvos = alunoDao.findAll();
		req.setAttribute("listaEntidades", alunosSalvos);
		req.getRequestDispatcher("/listar-entidades.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	
	

}
