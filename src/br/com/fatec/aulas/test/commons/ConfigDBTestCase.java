package br.com.fatec.aulas.test.commons;

import java.sql.Connection;
import java.sql.DriverManager;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.junit.After;
import org.junit.Before;

import br.com.fatec.aulas.core.helper.ConfigDBMapper;

/**
 * @author Carlos
 *
 * @version 1.0.1
 */
public abstract class ConfigDBTestCase {

	@Before
	public void upAmbiente() throws Exception {
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec-test");
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/aulas/liquibase/changelog-master.xml",new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");
		//org.hsqldb.util.DatabaseManagerSwing.main(new String[] {"--url", "jdbc:mem:hsqldb:test","--noexit"});
	}
	
	@After
	public void downAmbiente() throws Exception {
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
		conn.prepareStatement("DROP SCHEMA PUBLIC CASCADE").execute();
	}

}
