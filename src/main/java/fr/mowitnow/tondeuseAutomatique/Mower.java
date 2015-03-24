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
	
    public Mower(int xPosition, int yPosition, char direction) throws FailedInitializationException{
    	
    	if(direction != 'N' && direction != 'E' && direction != 'S' && direction != 'W') {
    		throw new FailedInitializationException();
    	}
    	
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
	
	/**
	 * Permet de faire avancer la tondeuse
	 */
	public void moveForward() {
			switch(this.direction) {
			case 	'N': this.yPosition += 1;
					break;
			case 	'E': this.xPosition += 1;
					break;
			case 	'S': this.yPosition -= 1;
					break;
			case 	'W': this.xPosition -= 1;
					break;
		}
	}
	
	/**
	 * Tourne la tondeuse à droite
	 * @param order
	 */
	public void turnRight() {

			switch(this.direction) {
			case 	'N': this.direction = 'E';
					break;
			case 	'E': this.direction = 'S';
					break;
			case 	'S': this.direction = 'W';
					break;
			case 	'W': this.direction = 'N';
					break;
		}

	}
	
	/**
	 * Tourne la tondeuse à droite
	 * @param order
	 */
	public void turnLeft() {

			switch(this.direction) {
			case 	'E': this.direction = 'N';
					break;
			case 	'S': this.direction = 'E';
					break;
			case 	'W': this.direction = 'S';
					break;
			case 	'N': this.direction = 'W';
					break;
		}

	}
}
