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
	
	/**
	 * Application des ordres à la tondeuse
	 * @param order
	 */
	public void move(char order) {
		
		if('A' == order) {
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
		else {
			if('D' == order) {
				switch(this.direction) {
					case 	'N': this.direction = 'E';
							break;
					case 	'E': this.xPosition = 'S'; 
							break;
					case 	'S': this.xPosition = 'W';
							break;
					case 	'W': this.yPosition = 'N';
							break;
				}
			}
			else {
				if('G' == order) {
					switch(this.direction) {
						case 	'N': this.yPosition = 'W';
								break;
						case 	'E': this.xPosition = 'N';
								break;
						case 	'S': this.xPosition = 'E';
								break;
						case 	'W': this.yPosition = 'S';
								break;
					}
				}
			}
		}

	}
}
