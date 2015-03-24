package fr.mowitnow.tondeuseAutomatique;

import java.util.LinkedList;
import java.util.List;

public class Lawn {
	
	List<Mower> mowers;

	private double length;
	private double width;
	
	public Lawn(double length, double width) {
		this.length = length;
		this.width = width;
		
		mowers = new LinkedList<Mower>();
	}

	public double getLength() {
		return length;
	}

	public double getWidth() {
		return width;
	}
	
	public void addMower(int xPosition, int yPosition, char direction) {
		try {
			mowers.add(new Mower(xPosition, yPosition, direction));
		} catch (FailedInitializationException e) {
			// les positions fourniées sont erronées
		}
	}
	
	public void addMower(Mower mower) {
		mowers.add(mower);
	}

	public List<Mower> getMowers() {
		return mowers;
	}
}
