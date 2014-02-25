package entity;

import com.j256.ormlite.field.DatabaseField;

public class Matter {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private Level level;
	
	
	public Matter(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}
