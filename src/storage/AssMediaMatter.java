package storage;

import com.j256.ormlite.field.DatabaseField;

import entity.Matter;
import entity.Media;

class AssMediaMatter {
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, uniqueCombo=true, canBeNull=false)
	private Media media;
	@DatabaseField(foreign=true, foreignAutoRefresh=true, uniqueCombo=true, canBeNull=false)
	private Matter matter;
	
	
	public AssMediaMatter(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
