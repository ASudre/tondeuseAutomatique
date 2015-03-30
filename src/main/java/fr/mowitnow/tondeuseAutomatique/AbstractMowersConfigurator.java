package fr.mowitnow.tondeuseAutomatique;

import java.util.LinkedList;
import java.util.List;

import fr.mowitnow.tondeuseAutomatique.exceptions.FileInfoException;

public abstract class AbstractMowersConfigurator {

	/**
	 * Tondeuses ajoutées
	 */
	protected List<Mower> mowers = new LinkedList<Mower>();
	
	/**
	 * Ajoute une tondeuse
	 * @param mower tondeuse à ajouter
	 * @throws FileInfoException
	 */
	protected void addMower(Mower mower) throws FileInfoException {
		
		if(!isValidDirection(mower.getDirection())) {
			throw new FileInfoException("MOWER_DIRECTION_ERROR", "Direction d'une tondeuse invalide.");
		}
		
		if(!isValidInstructionsInfo(mower.getInstructions())) {
			throw new FileInfoException("MOWER_INSTRUCTIONS_ERROR", "Erreur dans les instructions d'une tondeuse.");
		}
		
		if(!isValidPosition(mower.getLawn(), mower.getxPosition(), mower.getyPosition())) {
			throw new FileInfoException("ERROR_MOWER_DESCRIPTION", "Erreur dans la description d'une tondeuse.");
		}
		
		mowers.add(mower);
		
	}
	
	/**
	 * Démarre les tondeuses configurées
	 * @return
	 */
	public String startMowers() {
		
		StringBuilder mowersPositions = new StringBuilder();
		
		Mower mower = null;
		
		while(!hasNextMower()) {
			mower = pollNextMower();
			mower.executeInstructions();
			
			mowersPositions.append(mower).append("\n");
		}
		
		return mowersPositions.toString();
	}
	
	/**
	 * Vérifie la validité d'une instruction
	 * @param instruction à valider
	 * @return validité des instructions
	 */
	protected boolean isValidInstructionsInfo(String instructions) {
		
		if(instructions != null) {
		
			char[] letters = instructions.toCharArray();
			
			for (char letter : letters) {
				if(	   !new Character('A').equals(letter)
					&& !new Character('D').equals(letter)
					&& !new Character('G').equals(letter))
				{
					return false;
				}
			}
			
		}
		else {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Vérifie la validité de la direction
	 * @param direction à valider
	 * @return validité de la direction
	 */
	protected boolean isValidDirection(char direction) {
		if(!new Character('N').equals(direction)
		&& !new Character('E').equals(direction)
		&& !new Character('S').equals(direction)
		&& !new Character('W').equals(direction)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Vérifie la validité de la position dans la pelouse
	 * @param lawn pelouse configurée
	 * @param xPosition abscisse de la tondeuse
	 * @param yPosition ordonnée de la tondeuse
	 * @return boolean : validité de la position
	 */
	protected boolean isValidPosition(Lawn lawn, int xPosition, int yPosition) {
		if(lawn != null) {
			return xPosition > lawn.getWidth() || xPosition < 0 || yPosition > lawn.getLength() || yPosition < 0 ? false : true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Récupère la prochaine tondeuse et l'enlève de la liste
	 * @return Tondeuse suivante dans la queue
	 */
	public Mower pollNextMower() {
		return ((LinkedList<Mower>)mowers).poll();
	}
	
	/**
	 * Vérifie s'il reste une tondeuse
	 * @return boolean
	 */
	public boolean hasNextMower() {
		return ((LinkedList<Mower>)mowers).isEmpty();
	}
	
	protected List<Mower> getMowers() {
		return mowers;
	}
}
