package fr.mowitnow.tondeuseAutomatique;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.mowitnow.tondeuseAutomatique.exceptions.FileInfoException;

/**
 * Test de la classe chargeant les informations de configuration des tondeuses
 */
public class MowersConfiguratorTest 
{
	
	Lawn lawn;
	MowersConfigurator mowersConfigurator;

	/**
	 * Création du configurateur des tondeuses
	 * @throws Exception
	 */
	@Before
	public void SetUp() {
	}
	
	/**
	 * Null en argument du nom du fichier
	 */
	@Test
	public void readFile_null_FileInfoException_NO_FILE() {
		
		Throwable e = null;
		
		
		try {
			mowersConfigurator = new MowersConfigurator((String)null);
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);

		assertEquals("NO_FILE", ((FileInfoException) e).getMessageCourt());
		assertEquals("Fichier non trouvé.", ((FileInfoException) e).getMessageLong());
	}
	
	/**
	 * Fichier inexistant en argument
	 */
	@Test
	public void readFile_fichierInexistant_FileInfoException_NO_FILE() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("fichierInexistant");
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("NO_FILE", ((FileInfoException) e).getMessageCourt());
		assertEquals("Fichier non trouvé.", ((FileInfoException) e).getMessageLong());
		
	}
	
	/**
	 * Fichier vide en argument
	 */
	@Test
	public void createMowersFromFile_fichierVideTxt_FileInfoException_EMPTY_FILE() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/fichierVide.txt");
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("EMPTY_FILE", ((FileInfoException) e).getMessageCourt());
		assertEquals("Fichier vide.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void createMowersFromFile_pelouseStringDimensionsTxt_FileInfoException_LAWN_SIZE_ERROR() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/pelouseStringDimensions.txt");
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("LAWN_SIZE_ERROR", ((FileInfoException) e).getMessageCourt());
		assertEquals("Taille de la pelouse non valide.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void createMowersFromFile_pelouseUneSeuleDimensionFournieTxt_FileInfoException_LAWN_SIZE_ERROR() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/pelouseUneSeuleDimensionFournie.txt");
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("LAWN_SIZE_ERROR", ((FileInfoException) e).getMessageCourt());
		assertEquals("Erreur dans la description de la taille pelouse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void createMowersFromFile_pasInfoTondeuseTxt_FileInfoException_NO_MOWER_ERROR() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/pasInfoTondeuse.txt");
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("NO_MOWER_ERROR", ((FileInfoException) e).getMessageCourt());
		assertEquals("Pas d'information de tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void createMowersFromFile_manqueInstructionPremiereTondeuseTxt_FileInfoException_ERROR_NO_MOWER_INSTRUCTION() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/manqueInstructionPremiereTondeuse.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_NO_MOWER_INSTRUCTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Pas d'instruction pour une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void createMowersFromFile_manqueInfoDirectionPremiereTondeuseTxt_FileInfoException_ERROR_MOWER_DESCRIPTION() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/manqueInfoDirectionPremiereTondeuse.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_MOWER_DESCRIPTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Erreur dans la description d'une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void createMowersFromFile_manqueInstructionDeuxiemeTondeuseTxt_FileInfoException_ERROR_NO_MOWER_INSTRUCTION() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/manqueInstructionDeuxiemeTondeuse.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_NO_MOWER_INSTRUCTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Pas d'instruction pour une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void createMowersFromFile_manqueInfoDirectionDeuxiemeTondeuseTxt_FileInfoException_ERROR_MOWER_DESCRIPTION() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/manqueInfoDirectionDeuxiemeTondeuse.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_MOWER_DESCRIPTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Erreur dans la description d'une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	/**
	 * Test fournis des chaînes de caractères comme position de la tondeuse
	 */
	@Test
	public void createMowersFromFile_positionTondeuseStringTxt_FileInfoException_ERROR_MOWER_DESCRIPTION() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/positionTondeuseString.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_MOWER_DESCRIPTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Erreur dans la description d'une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void createMowersFromFile_directionTondeuseZTxt_FileInfoException_MOWER_DIRECTION_ERROR() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/directionTondeuseZ.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("MOWER_DIRECTION_ERROR", ((FileInfoException) e).getMessageCourt());
		assertEquals("Direction d'une tondeuse invalide.", ((FileInfoException) e).getMessageLong());
	}
	
	/**
	 * Instructions non valides pour la tondeuse
	 */
	@Test
	public void createMowersFromFile_instructionsTondeuseZTxt_FileInfoException_MOWER_INSTRUCTIONS_ERROR() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/instructionsTondeuseZ.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("MOWER_INSTRUCTIONS_ERROR", ((FileInfoException) e).getMessageCourt());
		assertEquals("Erreur dans les instructions d'une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	
	/**
	 * Pelouse 3x4
	 * Une tondeuse valide de position : 1 2 N et instructions : ADDG
	 */
	@Test
	public void createMowersFromFile_descriptionValideTondeuseTxt_12N() {
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/descriptionValideTondeuse_sansBordure.txt");
			
		} catch (Throwable ex) {
			// ne rentre pas ici
			fail();
		}
		
		Mower mower = mowersConfigurator.pollNextMower();
		
		Lawn lawn = mower.getLawn();
		assertEquals(3, lawn.getLength());
		assertEquals(4, lawn.getWidth());
		
		assertEquals(1, mower.getxPosition());
		assertEquals(2, mower.getyPosition());
		assertEquals('N', mower.getDirection());
		assertEquals("ADDG", mower.getInstructions());
		
		assertTrue(mowersConfigurator.hasNextMower());
		
	}
	
	/**
	 * Pelouse 3x4
	 * Une tondeuse valide de position : 1 2 N et instructions : ADDG
	 */
	@Test
	public void executeInstructionsFromFile_descriptionValideTondeuse_sansBordureTxt() {
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/descriptionValideTondeuse_sansBordure.txt");
			
		} catch (Throwable ex) {
			// ne rentre pas ici
			fail();
		}
		
		Mower mower = mowersConfigurator.pollNextMower();
		
		Lawn lawn = mower.getLawn();
		assertEquals(3, lawn.getLength());
		assertEquals(4, lawn.getWidth());
		
		assertEquals(1, mower.getxPosition());
		assertEquals(2, mower.getyPosition());
		assertEquals('N', mower.getDirection());
		assertEquals("ADDG", mower.getInstructions());
		
		assertTrue(mower.executeInstructions());
		assertEquals(1, mower.getxPosition());
		assertEquals(3, mower.getyPosition());
		assertEquals('E', mower.getDirection());
		
		assertTrue(mowersConfigurator.hasNextMower());
		
	}
	
	/**
	 * Pelouse 3x4
	 * Une tondeuse valide de position : 1 1 N et instructions : GAAGAADADA
	 */
	@Test
	public void executeInstructionsFromFile_descriptionValideTondeuse_avecBordureTxt() {
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/descriptionValideTondeuse_avecBordure.txt");
			
		} catch (Throwable ex) {
			// ne rentre pas ici
			fail();
		}
		
		Mower mower = mowersConfigurator.pollNextMower();
		
		Lawn lawn = mower.getLawn();
		assertEquals(3, lawn.getLength());
		assertEquals(4, lawn.getWidth());
		
		assertEquals(1, mower.getxPosition());
		assertEquals(1, mower.getyPosition());
		assertEquals('N', mower.getDirection());
		assertEquals("GAAGAADADA", mower.getInstructions());
		
		assertTrue(mower.executeInstructions());
		assertEquals(0, mower.getxPosition());
		assertEquals(1, mower.getyPosition());
		assertEquals('N', mower.getDirection());
		
		assertTrue(mowersConfigurator.hasNextMower());
	}
	
	/**
	 * Pelouse 3x4
	 * tondeuse1 : 1 1 N et instructions : GAAGAADADA
	 * tondeuse2 : 1 2 N et instructions : ADDG
	 */
	@Test
	public void executeInstructionsFromFile_descriptionValideDeuxTondeuses_avecBordureTxt() {
		
		try {
			mowersConfigurator = new MowersConfigurator("ressources/test/descriptionValideDeuxTondeuses_avecBordure.txt");
			
		} catch (Throwable ex) {
			// ne rentre pas ici
			fail();
		}
		
		Mower mower1 = mowersConfigurator.pollNextMower();;
		
		Lawn lawn = mower1.getLawn();
		assertEquals(3, lawn.getLength());
		assertEquals(4, lawn.getWidth());
		
		assertEquals(1, mower1.getxPosition());
		assertEquals(2, mower1.getyPosition());
		assertEquals('N', mower1.getDirection());
		assertEquals("ADDG", mower1.getInstructions());
		
		assertTrue(mower1.executeInstructions());
		assertEquals(1, mower1.getxPosition());
		assertEquals(3, mower1.getyPosition());
		assertEquals('E', mower1.getDirection());
		
		assertFalse(mowersConfigurator.hasNextMower());
		Mower mower2 = mowersConfigurator.pollNextMower();
		
		assertEquals(1, mower2.getxPosition());
		assertEquals(1, mower2.getyPosition());
		assertEquals('N', mower2.getDirection());
		assertEquals("GAAGAADADA", mower2.getInstructions());
		
		assertTrue(mower2.executeInstructions());
		assertEquals(0, mower2.getxPosition());
		assertEquals(1, mower2.getyPosition());
		assertEquals('N', mower2.getDirection());
		
		assertTrue(mowersConfigurator.hasNextMower());
		
	}
	
}

