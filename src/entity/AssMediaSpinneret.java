package entity;

import com.j256.ormlite.field.DatabaseField;

public class AssMediaSpinneret {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private Media media;
	@DatabaseField(foreign=true, foreignAutoRefresh=true)
	private Spinneret spinneret;
	
	
	public AssMediaSpinneret(){
		
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

	public Spinneret getSpinneret() {
		return spinneret;
	}

	public void setSpinneret(Spinneret spinneret) {
		this.spinneret = spinneret;
	}
}
