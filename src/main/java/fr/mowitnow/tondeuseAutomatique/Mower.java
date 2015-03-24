package fr.mowitnow.tondeuseAutomatique;

/**
 * Hello world!
 *
 */
public class Mower 
{
	
	/**
	 * Position sur l'axe des abscisses
	 */
	private int xPosition;
	
	/**
	 * Position sur l'axe des ordonnées
	 */
	private int yPosition;
	
	/**
	 * Direction : N/E/S/W
	 */
	private char direction;
	
    public Mower(int xPosition, int yPosition, char direction) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.direction = direction;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public char getDirection() {
		return direction;
	}
	
	public void move(char order) {
		
		if('A' == order) {
			switch(this.direction) {
				case 	'N': this.yPosition += 1;
						break;
				case 	'E': this.xPosition += 1;
						break;
				case 	'S': this.xPosition -= 1;
						break;
				case 	'W': this.yPosition -= 1;
						break;
			}
		}

	}
}
