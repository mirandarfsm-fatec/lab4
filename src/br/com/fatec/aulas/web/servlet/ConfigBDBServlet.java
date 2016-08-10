package br.com.fatec.aulas.web.servlet;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;


public class ConfigBDBServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException{
		try {
			ConfigDBMapper.getInstance().setDefaultConnectionName("fatec-prod");
			Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
			Liquibase liquibase = new Liquibase("br/com/fatec/aulas/liquibase/changelog-master.xml",new ClassLoaderResourceAccessor(), database);
			liquibase.forceReleaseLocks();
			liquibase.update("fatec");
		} catch (Exception e) {
			throw new RuntimeException("Erro ao subir Banco de Dados",e);
		}
	}
}
