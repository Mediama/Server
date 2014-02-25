package entity;

import com.j256.ormlite.field.DatabaseField;

public class User {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField
	private String login;
	@DatabaseField
	private String password;
	@DatabaseField
	private String email;
	
	
	public User(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}