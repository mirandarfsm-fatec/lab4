package br.com.fatec.aulas.core.helper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigConnection {
	private String name;
	private String url;
	private String login;
	private String password;
	
	public ConfigConnection(String name, String url, String login, String password) {
		this.name = name;
		this.url = url;
		this.login = login;
		this.password = password;
	}

}
