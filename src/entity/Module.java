package entity;

import com.j256.ormlite.field.DatabaseField;

public class Module {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(uniqueCombo=true, canBeNull=false)
	private int order;
	@DatabaseField(uniqueCombo=true, canBeNull=false, foreign=true, foreignAutoRefresh=true)
	private Formation formation;
	
	
	public Module(){
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public Formation getFormation() {
		return formation;
	}
	
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
}
