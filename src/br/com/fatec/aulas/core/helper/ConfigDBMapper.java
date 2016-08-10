package br.com.fatec.aulas.core.helper;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hsqldb.lib.StringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.com.fatec.aulas.core.helper.exception.FatecRunTimeException;

public class ConfigDBMapper {

	private static ConfigDBMapper instance;
	private static JSONParser parse = new JSONParser();
	private static final ClassLoader loader = ConfigDBMapper.class.getClassLoader();
	private static Map<String, ConfigConnection> configConnections = new HashMap<String, ConfigConnection>();
	private String defaultConnectionName;
	private List<String> possibleConfigs;

	private ConfigDBMapper() {
		loadConnections();
	}

	public static ConfigDBMapper getInstance() {
		if (instance == null) {
			instance = new ConfigDBMapper();
		}
		return instance;
	}

	public void loadConnections() {
		try {
			String path = loader.getResource("br/com/fatec/aulas/core/configuration/database.json").getPath();
			JSONArray configs = (JSONArray) ConfigDBMapper.parse.parse(new FileReader(path));
			if (configs.size() < 1) {
				throw new FatecRunTimeException("É necessario ao menos uma configuração de Banco de Dados");
			}
			for (Object config : configs) {
				JSONObject configJSON = (JSONObject) config;
				String configNameJSON = (String) configJSON.get("name");
				String url = (String) configJSON.get("url");
				String login = (String) configJSON.get("login");
				String password = (String) configJSON.get("password");
				String driverClassName = (String) configJSON.get("driverClassName");

				Class.forName(driverClassName);

				configConnections.put(configNameJSON,new ConfigConnection(configNameJSON,url,login,password));
			}

			this.possibleConfigs = new ArrayList<String>(configConnections.keySet());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setDefaultConnectionName(String config) {
		if (this.defaultConnectionName == null && (config != null && !StringUtil.isEmpty(config))) {
			if (this.possibleConfigs.contains(config)) {
				this.defaultConnectionName = config;
			} else {
				throw new RuntimeException("Não existe configuração com nome '"+ config + "'.");
			}

		}
	}

	public Connection getDefaultConnection() {
		if (this.defaultConnectionName == null) {
			return null;
		}
		return this.getConnectionByConfig(this.defaultConnectionName);
	}

	public List<String> getPossibleConfigs() {
		return possibleConfigs;
	}

	public Connection getConnectionByConfig(String configName) {
		if (configConnections.containsKey(configName)) {
			ConfigConnection configConnection = configConnections.get(configName);
			try{
				return DriverManager.getConnection(configConnection.getUrl(),configConnection.getLogin(),configConnection.getPassword());
			}catch(SQLException e){
				throw new RuntimeException("Nao foi possivel gerar uma conexao para o Banco de Dados '" + configName + "'.",e); 
			}
		}
		throw new RuntimeException("Não existe configuração com nome '"	+ configName + "'");
	}
}
