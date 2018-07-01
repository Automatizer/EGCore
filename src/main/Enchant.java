package main;

import java.util.ArrayList;

import org.bukkit.Material;

public abstract class Enchant {
	
	public String name;
	public ArrayList<Material> allowedMats;

	public abstract void startup();
	public abstract void execute();
	public abstract Integer getLevel();
	
}
