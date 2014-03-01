package entity;

import com.j256.ormlite.field.DatabaseField;

public class Matter {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(canBeNull=false)
	private String name;
	@DatabaseField(canBeNull=false)
	private int semester;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, canBeNull=false)
	private Spinneret spinneret;
	@DatabaseField
	private int hours;
	
	
	public Matter(){
		
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

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Spinneret getSpinneret() {
		return spinneret;
	}

	public void setSpinneret(Spinneret spinneret) {
		this.spinneret = spinneret;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
}
