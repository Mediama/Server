package entity;

import com.j256.ormlite.field.DatabaseField;

public class AssMediaMatter {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, uniqueCombo=true, canBeNull=false)
	private Media media;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, uniqueCombo=true, canBeNull=false)
	private Matter matter;
	
	
	public AssMediaMatter(){
		
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

	public Matter getMatter() {
		return matter;
	}

	public void setMatter(Matter matter) {
		this.matter = matter;
	}
}
