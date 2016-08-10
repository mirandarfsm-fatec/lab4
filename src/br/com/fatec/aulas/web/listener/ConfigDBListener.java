package br.com.fatec.aulas.web.listener;

import java.sql.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;

@WebListener
public final class ConfigDBListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0)  {
	
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
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
