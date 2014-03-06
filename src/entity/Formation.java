package entity;

import com.j256.ormlite.field.DatabaseField;

public class Formation {
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField(canBeNull=false)
	private String name;
	
	public Formation(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
