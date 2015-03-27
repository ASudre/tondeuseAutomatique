package fr.mowitnow.tondeuseAutomatique;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import fr.mowitnow.tondeuseAutomatique.exceptions.FailedInitializationMowerException;
import fr.mowitnow.tondeuseAutomatique.exceptions.FileInfoException;

/**
 * Charge le fichier de configuration des tondeuses, crée les tondeuses et lance l'exécutions des instructions
 * @author Arthur
 *
 */
public class MowersConfigurator {
	
	/**
	 * Tondeuses ajoutées
	 */
	List<Mower> mowers = new LinkedList<Mower>();
	
	public MowersConfigurator(String filename) throws FileInfoException {
		Scanner scanner = readFile(filename);
		createMowersFromFile(scanner);
	}
	
	public MowersConfigurator(Mower mower) throws FileInfoException {
		addMower(mower);
	}
	
	private void addMower(Mower mower) throws FileInfoException {
		
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
	
	private Scanner readFile(String fileName) throws FileInfoException {
		Scanner scanner = null;

		if(fileName == null) {
			throw new FileInfoException("NO_FILE", "Fichier non trouvé.");
		}
		
		try {
			scanner = new Scanner(new File(fileName));
		} 
		catch (FileNotFoundException e) {
			throw new FileInfoException("NO_FILE", "Fichier non trouvé.");
		}
		
		if(!scanner.hasNextLine()) {
			throw new FileInfoException("EMPTY_FILE", "Fichier vide.");
		}
		
		return scanner;
	}
	
	private void createMowersFromFile(Scanner scanner) throws FileInfoException {
		Lawn lawn = null;
		
		try {
		
			String line = scanner.nextLine();
			String[] lawnSize = line.split(" ");
			
			if(lawnSize.length == 2) {
				try {
					lawn = new Lawn(Integer.parseInt(lawnSize[0]), Integer.parseInt(lawnSize[1]));
				}
				catch(NumberFormatException e) {
					throw new FileInfoException("LAWN_SIZE_ERROR", "Taille de la pelouse non valide.");
				} 
			}
			else {
				throw new FileInfoException("LAWN_SIZE_ERROR", "Erreur dans la description de la taille pelouse.");
			}
			
			if(!scanner.hasNextLine()) {
				throw new FileInfoException("NO_MOWER_ERROR", "Pas d'information de tondeuse.");
			}
			
			// On ajoute chaque tondeuse lue
			while (scanner.hasNextLine()) {
			    String mowerInfo = scanner.nextLine();
			    String[] tabMowerInfo = mowerInfo.split(" ");
			    String instructionsInfo;
			    Mower mower;
			    
			    if(scanner.hasNextLine()) {
				    instructionsInfo = scanner.nextLine();
			    }
			    else {
					throw new FileInfoException("ERROR_NO_MOWER_INSTRUCTION", "Pas d'instruction pour une tondeuse.");
			    }
			    
				if(tabMowerInfo.length == 3) {
					
					Character direction = tabMowerInfo[2].charAt(0);
					
					if(!isValidDirection(direction)) {
						throw new FileInfoException("MOWER_DIRECTION_ERROR", "Direction d'une tondeuse invalide.");
					}
					
					if(!isValidInstructionsInfo(instructionsInfo)) {
						throw new FileInfoException("MOWER_INSTRUCTIONS_ERROR", "Erreur dans les instructions d'une tondeuse.");
					}
					
					try {
						int xPosition = Integer.parseInt(tabMowerInfo[0]);
						int yPosition = Integer.parseInt(tabMowerInfo[1]);
						
						if(!isValidPosition(lawn, xPosition, yPosition)) {
							throw new FileInfoException("ERROR_MOWER_DESCRIPTION", "Erreur dans la description d'une tondeuse.");
						}
						
						mower = new Mower(lawn, instructionsInfo, xPosition, yPosition, tabMowerInfo[2].charAt(0));
					}
					catch(NumberFormatException e) {
						throw new FileInfoException("ERROR_MOWER_DESCRIPTION", "Erreur dans la description d'une tondeuse.");
					} 
				}
				else {
					throw new FileInfoException("ERROR_MOWER_DESCRIPTION", "Erreur dans la description d'une tondeuse.");
				}
				
				addMower(mower);
			 
			}
			 
			scanner.close();
			
		}
		catch (FailedInitializationMowerException e) {
			throw new FileInfoException("MOWER_CONFIG_ERROR", "Erreur dans la configuration de la tondeuse.");
		}
	}

	/**
	 * Vérifie la validité d'une instruction
	 * @param instruction
	 * @return
	 */
	private boolean isValidInstructionsInfo(String instructions) {
		
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
	 * @param instruction
	 * @return
	 */
	private boolean isValidDirection(char direction) {
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
	 * @param instruction
	 * @return
	 */
	private boolean isValidPosition(Lawn lawn, int xPosition, int yPosition) {
		if(lawn != null) {
			return xPosition > lawn.getWidth() || xPosition < 0 || yPosition > lawn.getLength() || yPosition < 0 ? false : true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Récupère la prochaine tondeuse et l'enlève de la liste
	 * @return
	 */
	public Mower pollNextMower() {
		return ((LinkedList<Mower>)mowers).poll();
	}
	
	/**
	 * Vérifie s'il reste une tondeuse
	 * @return
	 */
	public boolean hasNextMower() {
		return ((LinkedList<Mower>)mowers).isEmpty();
	}

	public static void main(String[] args) {
		
		if(args != null && args.length == 1) {
			try {
				MowersConfigurator mowersConfigurator = new MowersConfigurator(args[0]);
				System.out.println(mowersConfigurator.startMowers());
			} catch (FileInfoException e) {
				System.out.println(e.getMessageLong());
			}
		}
		else {
			System.out.println("Veuillez fournir un nom de fichier valide en argument.");
		}
	}
		
}
