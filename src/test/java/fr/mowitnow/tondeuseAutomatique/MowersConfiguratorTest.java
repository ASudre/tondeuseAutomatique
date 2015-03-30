package fr.mowitnow.tondeuseAutomatique;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import fr.mowitnow.tondeuseAutomatique.exceptions.FileInfoException;

/**
 * Test de la classe chargeant les informations de configuration des tondeuses
 */
public class MowersConfiguratorTest 
{
	
	Lawn lawn;
	MowersConfiguratorByFile mowersConfigurator;
	
	/**
	 * Null en argument du nom du fichier
	 */
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnNullValueOnParameter() {
		
		Throwable e = null;
		
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile((String)null);
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
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnMissingFileOnParameter() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("fichierInexistant");
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
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnEmptyFileOnParameter() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/fichierVide.txt");
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("EMPTY_FILE", ((FileInfoException) e).getMessageCourt());
		assertEquals("Fichier vide.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnInvalideLawnSizeInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/pelouseStringDimensions.txt");
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("LAWN_SIZE_ERROR", ((FileInfoException) e).getMessageCourt());
		assertEquals("Taille de la pelouse non valide.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnMissingOneLawnDimensionInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/pelouseUneSeuleDimensionFournie.txt");
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("LAWN_SIZE_ERROR", ((FileInfoException) e).getMessageCourt());
		assertEquals("Erreur dans la description de la taille pelouse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnMissingMowerInformationInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/pasInfoTondeuse.txt");
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("NO_MOWER_ERROR", ((FileInfoException) e).getMessageCourt());
		assertEquals("Pas d'information de tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnMissing1stMowerDirectionInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/manqueInfoDirectionPremiereTondeuse.txt");
			
		} catch (Throwable ex) {
			e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_MOWER_DESCRIPTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Erreur dans la description d'une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnMissing1stMowerInstructionsInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/manqueInstructionPremiereTondeuse.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_NO_MOWER_INSTRUCTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Pas d'instruction pour une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnMissing2ndMowerDirectionInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/manqueInfoDirectionDeuxiemeTondeuse.txt");
			
		} catch (Throwable ex) {
			e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_MOWER_DESCRIPTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Erreur dans la description d'une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnMissing2ndMowerInstructionsInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/manqueInstructionDeuxiemeTondeuse.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_NO_MOWER_INSTRUCTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Pas d'instruction pour une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	/**
	 * Test fournis des chaînes de caractères comme position de la tondeuse
	 */
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnMissing1stMowerPositionInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/positionTondeuseString.txt");
			
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FileInfoException);
		
		assertEquals("ERROR_MOWER_DESCRIPTION", ((FileInfoException) e).getMessageCourt());
		assertEquals("Erreur dans la description d'une tondeuse.", ((FileInfoException) e).getMessageLong());
	}
	
	@Test
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnWrong1stMowerDirectionInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/directionTondeuseZ.txt");
			
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
	public void constructorMowersConfiguratorByFile_FileInfoExceptionThrownOnWrong1stMowerInstructionsInFile() {
		
		Throwable e = null;
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/instructionsTondeuseZ.txt");
			
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
	public void constructorMowersConfiguratorByFile_validMowerDescriptionInFile() {
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/descriptionValideTondeuse_sansBordure.txt");
			
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
	public void executeInstructions_validMowerDescriptionInFileNeverHitsBorders_finalPositionOK() {
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/descriptionValideTondeuse_sansBordure.txt");
			
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
	 * En testant l'arrêt de la tondeuse aux bordures de la pelouse
	 */
	@Test
	public void executeInstructions_validMowerDescriptionInFileWithTestBorders_finalPositionOK() {
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/descriptionValideTondeuse_avecBordure.txt");
			
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
		assertEquals("GAAGAADADAAAADAAAAADAAAA", mower.getInstructions());
		
		assertTrue(mower.executeInstructions());
		assertEquals(4, mower.getxPosition());
		assertEquals(0, mower.getyPosition());
		assertEquals('S', mower.getDirection());
		
		assertTrue(mowersConfigurator.hasNextMower());
	}
	
	/**
	 * Pelouse 3x4
	 * tondeuse1 : 1 1 N et instructions : GAAGAADADA
	 * tondeuse2 : 1 2 N et instructions : ADDG
	 */
	@Test
	public void executeInstructions_validTwoMowersDescriptionsInFileWithTestBorders_finalPositionOK() {
		
		try {
			mowersConfigurator = new MowersConfiguratorByFile("ressources/test/descriptionValideDeuxTondeuses_avecBordure.txt");
			
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

