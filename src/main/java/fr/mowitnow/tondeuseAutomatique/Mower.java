package fr.mowitnow.tondeuseAutomatique;

import fr.mowitnow.tondeuseAutomatique.exceptions.FailedInitializationMowerException;

/**
 * Représente une tondeuse
 * @author Arthur
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
	
	/**
	 * Instructions d'exécutions
	 */
	private String instructions;

	/**
	 * Pelouse à tondre
	 */
	private Lawn lawn;
	
	/**
	 * Constructeur de la tondeuse indiquant les informations de la pelouse, les instructions et la position intiale de la tondeuse
	 * @param lawn
	 * @param instructions
	 * @param xPosition
	 * @param yPosition
	 * @param direction
	 * @throws FailedInitializationMowerException
	 */
    public Mower(Lawn lawn, String instructions, int xPosition, int yPosition, char direction) throws FailedInitializationMowerException{
    	
    	if(direction != 'N' && direction != 'E' && direction != 'S' && direction != 'W') {
    		throw new FailedInitializationMowerException("MOWER_DIRECTION_ERROR", "Erreur dans la description de la direction de la tondeuse.");
    	}
    	
		if(instructions == null) {
			throw new FailedInitializationMowerException("NULL_INSTRUCTIONS", "Instructions absentes.");
		}
		else {
			this.instructions = instructions;
		}
	
    	if(lawn == null) {
    		throw new FailedInitializationMowerException("NULL_LAWN", "Description de la pelouse absente.");
    	}
    	else {
    		this.lawn = lawn;
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
	private void moveForward() {
		int nextXPosition = xPosition;
		int nextYPosition = yPosition;
		
		switch(this.direction) {
			case 	'N': nextYPosition += 1;
					break;
			case 	'E': nextXPosition += 1;
					break;
			case 	'S': nextYPosition -= 1;
					break;
			case 	'W': nextXPosition -= 1;
					break;
		}
		
		if(isInsideLawn(nextXPosition, nextYPosition)) {
			xPosition = nextXPosition;
			yPosition = nextYPosition;
		}
	}
	
	/**
	 * Tourne la tondeuse à droite
	 */
	private void turnRight() {

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
	 * Tourne la tondeuse à gauche
	 */
	private void turnLeft() {
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
	
	/**
	 * Exécution des instructions
	 */
	public boolean executeInstructions() {
		
		if(instructions != null) {
		
			char[] letters = instructions.toCharArray();
			
			for (char letter : letters) {
				switch (letter) {
				case 'A':
					moveForward();
					break;
				case 'D':
					turnRight();
					break;
				case 'G':
					turnLeft();
					break;
				default:
					return false;
				}
			}
			
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Vérifie si la tondeuse reste dans la pelouse
	 * @param xPosition valeur des abscisses après instruction
	 * @param yPosition valeur des ordonnées après instruction
	 * @return
	 */
	private boolean isInsideLawn(int xPosition, int yPosition) {
		return xPosition > lawn.getWidth() || xPosition < 0 || yPosition > lawn.getLength() || yPosition < 0 ? false : true;
	}

	public String getInstructions() {
		return instructions;
	}

	public Lawn getLawn() {
		return lawn;
	}
	
	/**
	 * Affiche la position de la tondeuse
	 */
	@Override
	public String toString() {
		return this.getxPosition() + " " + this.getyPosition() + " " + this.getDirection();
	}
	
}
