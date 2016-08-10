package br.com.fatec.aulas.web.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.aulas.api.dao.EntityDAO;
import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.core.dao.AlunoDAOImpl;
import br.com.fatec.aulas.core.helper.AlunoFactory;

@WebServlet("/aluno-save")
public class AlunoSaveServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().print("Usuário " + req.getParameter("nome") + " salvo com Sucesso");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EntityDAO<Aluno> alunoDao = new AlunoDAOImpl();
		Date date = new Date();
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("data_nascimento"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Aluno aluno = AlunoFactory.criarAluno(null, req.getParameter("nome"), req.getParameter("ra"), date, req.getParameter("observacao"));
		alunoDao.save(aluno);
		doGet(req, resp);
		
	}
	
	

}
