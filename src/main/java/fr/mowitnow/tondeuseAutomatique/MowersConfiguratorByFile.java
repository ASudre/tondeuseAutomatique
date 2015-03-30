package fr.mowitnow.tondeuseAutomatique;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import fr.mowitnow.tondeuseAutomatique.exceptions.FailedInitializationMowerException;
import fr.mowitnow.tondeuseAutomatique.exceptions.FileInfoException;

/**
 * Charge le fichier de configuration des tondeuses, crée les tondeuses et lance l'exécutions des instructions
 * @author Arthur
 *
 */
public class MowersConfiguratorByFile extends AbstractMowersConfigurator {
	
	/**
	 * Constructeur à partir d'un fichier
	 * @param filename nom du fichier
	 * @throws FileInfoException
	 */
	public MowersConfiguratorByFile(String filename) throws FileInfoException {
		Scanner scanner = readFile(filename);
		createMowersFromFile(scanner);
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
	 * Lit et vérifie la pertinence du fichier de configuration des tondeuses
	 * @param fileName fichier à lire
	 * @return Scanner contenant les données du fichier
	 * @throws FileInfoException
	 */
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
	
	/**
	 * Crée les tondeuses à partir du scanner obtenu après lecture du fichier de configuration
	 * @param scanner Scanner contenant les données de configuration des tondeuses
	 * @throws FileInfoException
	 */
	private void createMowersFromFile(Scanner scanner) throws FileInfoException {
		Lawn lawn = null;
		
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
		    int xPosition;
		    int yPosition;
		    Mower mower;
		    
		    // Vérification de la description de la tondeuse
			if(tabMowerInfo.length == 3) {
				
				Character direction = tabMowerInfo[2].charAt(0);
				
				if(!isValidDirection(direction)) {
					throw new FileInfoException("MOWER_DIRECTION_ERROR", "Direction d'une tondeuse invalide.");
				}
				
				try {
					xPosition = Integer.parseInt(tabMowerInfo[0]);
					yPosition = Integer.parseInt(tabMowerInfo[1]);
					
					if(!isValidPosition(lawn, xPosition, yPosition)) {
						throw new FileInfoException("ERROR_MOWER_DESCRIPTION", "Erreur dans la description d'une tondeuse.");
					}
					
				}
				catch(NumberFormatException e) {
					throw new FileInfoException("ERROR_MOWER_DESCRIPTION", "Erreur dans la description d'une tondeuse.");
				} 
			}
			else {
				throw new FileInfoException("ERROR_MOWER_DESCRIPTION", "Erreur dans la description d'une tondeuse.");
			}
			
			// Vérification des instructions de la tondeuse
			if(scanner.hasNextLine()) {
				instructionsInfo = scanner.nextLine();
				
				if(!isValidInstructionsInfo(instructionsInfo)) {
					throw new FileInfoException("MOWER_INSTRUCTIONS_ERROR", "Erreur dans les instructions d'une tondeuse.");
				}
			}
			else {
				throw new FileInfoException("ERROR_NO_MOWER_INSTRUCTION", "Pas d'instruction pour une tondeuse.");
			}
			
			try {
				mower = new Mower(lawn, instructionsInfo, xPosition, yPosition, tabMowerInfo[2].charAt(0));
			}
			catch (FailedInitializationMowerException e) {
				throw new FileInfoException(e.getMessageCourt(), e.getMessageLong());
			}

			addMower(mower);
		 
		}
		 
		scanner.close();
			
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

	/**
	 * Main
	 * @param args Contient le nom du fichier dans la première case
	 */
	public static void main(String[] args) {
		
		if(args != null && args.length == 1) {
			try {
				MowersConfiguratorByFile mowersConfigurator = new MowersConfiguratorByFile(args[0]);
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
