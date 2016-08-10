package br.com.fatec.aulas.core.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fatec.aulas.core.helper.ConfigDBMapper;

/**
 * @author Miranda
 *
 * @version 1.0.1
 */
public class GeradorIdService<Classe> {

	private static GeradorIdService instance;
	private Long idSequence = 100L;
	private static Connection connection;

	private GeradorIdService() {
	}

	public static GeradorIdService getInstance() {
		try {
			if (instance == null) {
				instance = new GeradorIdService();
				connection = ConfigDBMapper.getInstance().getDefaultConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public synchronized Long getNextId(String tableName) {
		//TODO: Problemas em classe sem id cadastrador
		try {
			PreparedStatement query = connection.prepareStatement("SELECT NEXT_ID FROM FATEC_IDS WHERE TABLE_NAME = ?");
			query.setString(1, tableName);
			ResultSet resultNextId = query.executeQuery();
			resultNextId.next();
			idSequence = resultNextId.getLong("NEXT_ID");
			PreparedStatement updateID = connection.prepareStatement("UPDATE FATEC_IDS SET NEXT_ID = ? WHERE TABLE_NAME = ?");
			updateID.setLong(1, idSequence + 1);
			updateID.setString(2, tableName);
			updateID.execute();
			updateID.close();
			connection.commit();
			return idSequence;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao pegar o ID para a tabela '" + tableName + "'", e);
		}
	}

}
