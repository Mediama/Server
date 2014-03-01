import java.sql.SQLException;

import storage.DatabaseManager;
import entity.Matter;
import entity.Spinneret;



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
		
		try {
			manager.getSpinneretDao().deleteBuilder().delete();
			
			manager.getSpinneretDao().create(tiil);
			manager.getSpinneretDao().create(stapps);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Matter math=new Matter();
		math.setName("Math");
		math.setSemester(1);
		math.setSpinneret(tiil);
		math.setHours(75);
		
		try {
			manager.getMatterDao().deleteBuilder().delete();
			
			manager.getMatterDao().create(math);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
