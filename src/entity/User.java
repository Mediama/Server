package entity;

import com.j256.ormlite.field.DatabaseField;

public class User {
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField(canBeNull=false, unique=true)
	private String login;
	@DatabaseField(canBeNull=false)
	private String password;
	@DatabaseField(canBeNull=false)
	private String email;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, canBeNull=true)
	private Module module;
	
	
	public User(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
