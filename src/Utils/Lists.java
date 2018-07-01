package Utils;

import java.util.ArrayList;

import main.Enchant;

public class Lists {

	private static Lists instance = null;
	public ArrayList<Enchant> enchants;
	
	public static Lists getInstance() {
		if(instance == null) instance = new Lists();
		return instance;
	}
	
	public void startup() {
		enchants = new ArrayList<Enchant>();
	}
	
}
