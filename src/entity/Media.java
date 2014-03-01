package entity;

import com.j256.ormlite.field.DatabaseField;

public class Media {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(canBeNull=false)
	private String title;
	@DatabaseField
	private Type type;
	@DatabaseField
	private String author;
	
	public Media(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public enum Type{
		MOVIE, DOC, SOUND
	}
}
