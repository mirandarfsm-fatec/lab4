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

import org.junit.Assert;
import org.junit.Test;

import br.com.fatec.aulas.api.entity.Aluno;

/**
 * @author Carlos
 *
 * @version 1.0.1
 */
public class QueryInterferenceTest {
	
	@Test
	public void testAll() throws Exception{
		primeiro();
		segundo();
		terceiro();
	}

	public void primeiro() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
		Liquibase liquibase = new Liquibase("br/com/fatec/aulas/liquibase/changelog-master.xml", new ClassLoaderResourceAccessor(), database);
		liquibase.forceReleaseLocks();
		liquibase.update("fatec");
	}

	public void segundo() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		PreparedStatement insert = conn.prepareStatement("INSERT INTO " + Aluno.TABLE + " VALUES (1, 'teste', '12345', '2015-05-16', null);");
		insert.execute();
	}

	public void terceiro() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", "");
		PreparedStatement query = conn.prepareStatement("SELECT * FROM " + Aluno.TABLE + ";");
		ResultSet resultado = query.executeQuery();
		Assert.assertTrue(resultado.next());
		Assert.assertEquals(1L, resultado.getLong(1));
	}

}
