import entity.Level;
import entity.Spinneret;
import storage.DatabaseManager;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseManager manager=DatabaseManager.getManager();
		
		Spinneret tiil=new Spinneret();
		tiil.setName("TIIL");
		
		Spinneret stapps=new Spinneret();
		stapps.setName("Stapps");
		
		Level tiilLvl1=new Level();
		tiilLvl1.setName("M1");
		
		Level tiilLvl2=new Level();
		tiilLvl2.setName("M2");
	}

}
