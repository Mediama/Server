package entity;

import com.j256.ormlite.field.DatabaseField;

public class Level {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private Spinneret spinneret;
	@DatabaseField
	private String name;
	
	public Level(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
