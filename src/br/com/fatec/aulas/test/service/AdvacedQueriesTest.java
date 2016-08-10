package br.com.fatec.aulas.test.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.aulas.api.entity.Aluno;
import br.com.fatec.aulas.core.helper.ConfigDBMapper;
import br.com.fatec.aulas.test.commons.ConfigDBTestCase;

/**
 * @author Carlos
 *
 * @version
 */
public class AdvacedQueriesTest {

	@Before
	public void setUp() throws Exception {
		ConfigDBMapper.getInstance().setDefaultConnectionName("fatec-test");
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/aulas/liquibase/changelog-master.xml",new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");
	}

	@After
	public void setDown() throws Exception {
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
		conn.prepareStatement("DROP SCHEMA PUBLIC CASCADE").execute();
	}

	@Test
	public void insertTest() throws Exception {
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
		PreparedStatement insert = conn.prepareStatement("INSERT INTO " + Aluno.TABLE
				+ " VALUES (1, 'teste', '12345', '2015-05-16',null)");
		insert.execute();

		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Aluno.TABLE);
		ResultSet resultado = query.executeQuery();
		Assert.assertTrue(resultado.next());
		Assert.assertEquals(1L, resultado.getLong(1));
	}

	@Test
	public void selectTest() throws Exception {
		Connection conn = ConfigDBMapper.getInstance().getDefaultConnection();
		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Aluno.TABLE);
		ResultSet resultado = query.executeQuery();
		Assert.assertFalse(resultado.next());
	}

}
