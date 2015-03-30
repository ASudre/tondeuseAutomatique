package fr.mowitnow.tondeuseAutomatique;

/**
 * Réprésente la pelouse
 * @author Arthur
 *
 */
public class Lawn {
	
	/**
	 * Longueur --> ordonnée maximale du terrain
	 */
	private int length;
	
	/**
	 * Largeur --> abscisse maximale du terrain
	 */
	private int width;
	
	public Lawn(int width, int length) {
		this.length = length;
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

}
