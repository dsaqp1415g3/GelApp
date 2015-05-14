package edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.model;

public class User {
	private int usuarioid;
	private String username;
	private String password;
	private boolean loginSuccesful;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getUsuarioid() {
		return usuarioid;
	}
	public void setUsuarioid(int usuarioid) {
		this.usuarioid = usuarioid;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoginSuccesful() {
		return loginSuccesful;
	}
	public void setLoginSuccesful(boolean loginSuccesful) {
		this.loginSuccesful = loginSuccesful;
	}

	
	
}
