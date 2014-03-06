package app;
import java.util.ArrayList;
import java.util.List;


public class Tools {
	public static List<Integer> toIntList(String list, String separator){
		ArrayList<Integer> result=new ArrayList<Integer>();
		
		for(String value : list.split(separator))
				result.add(Integer.parseInt(value));
		
		return result;
	}
}
