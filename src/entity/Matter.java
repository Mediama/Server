package entity;

import com.j256.ormlite.field.DatabaseField;

public class Matter {
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField(canBeNull=false)
	private String name;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, canBeNull=false)
	private Module module;
	@DatabaseField
	private int hours;
	
	
	public Matter(){
		
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

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
}
