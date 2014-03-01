package entity;

import com.j256.ormlite.field.DatabaseField;

public class Rate {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, uniqueCombo=true, canBeNull=false)
	private Media media;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, uniqueCombo=true, canBeNull=false)
	private User user;
	@DatabaseField(canBeNull=false)
	private int rate;
	@DatabaseField(canBeNull=true)
	private String com;
	
	
	public Rate(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}
}
