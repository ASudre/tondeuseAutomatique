package fr.mowitnow.tondeuseAutomatique;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.mowitnow.tondeuseAutomatique.exceptions.FailedInitializationMowerException;
import fr.mowitnow.tondeuseAutomatique.exceptions.FileInfoException;

/**
 * Unit test for simple App.
 */
public class MowerTest 
{
	Mower mowerN;
	Mower mowerE;
	Mower mowerS;
	Mower mowerW;
	Lawn lawn;

	MowersConfigurator mowersConfiguratorN;
	MowersConfigurator mowersConfiguratorE;
	MowersConfigurator mowersConfiguratorS;
	MowersConfigurator mowersConfiguratorW;

	@Before
	public void SetUp() throws Exception {
		lawn = new Lawn(5, 5);
	}
	
	@Test
	public void initialisationMower_12N() throws Exception {
		// Position initiale
		Mower mowerN = new Mower(lawn, "", 1, 2, 'N');
		
		assertEquals(1, mowerN.getxPosition());
		assertEquals(2, mowerN.getyPosition());
		assertEquals('N', mowerN.getDirection());
	}
	
	
	@Test
	public void initialisationMowerEnDirectionInconnue_12Z_FailedException() {
		
		Throwable e = null;
		
		@SuppressWarnings("unused")
		Mower mower;
		
		try {
			mower = new Mower(null, null, 1, 2, 'Z');
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FailedInitializationMowerException);
		
	}
	
	@Test
	public void appliquerOrdreA_12N_13N() {
		try {
			mowerN = new Mower(new Lawn(5, 5), "A", 1, 2, 'N');
			mowersConfiguratorN = new MowersConfigurator(mowerN);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		//Déplacement en avant
		mowersConfiguratorN.startMowers();
		assertEquals(1, mowerN.getxPosition());
		assertEquals(3, mowerN.getyPosition());
		assertEquals('N', mowerN.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreA_12W_02W() {

		try {
			mowerW = new Mower(new Lawn(5, 5), "A", 1, 2, 'W');
			mowersConfiguratorW = new MowersConfigurator(mowerW);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		//Déplacement en avant
		mowersConfiguratorW.startMowers();
		assertEquals(0, mowerW.getxPosition());
		assertEquals(2, mowerW.getyPosition());
		assertEquals('W', mowerW.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreA_12E_22E() {
		try {
			mowerE = new Mower(new Lawn(5, 5), "A", 1, 2, 'E');
			mowersConfiguratorE = new MowersConfigurator(mowerE);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		mowersConfiguratorE.startMowers();
		assertEquals(2, mowerE.getxPosition());
		assertEquals(2, mowerE.getyPosition());
		assertEquals('E', mowerE.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreA_12S_11S() {
		//Déplacement en avant
		try {
			mowerS = new Mower(new Lawn(5, 5), "A", 1, 2, 'S');
			mowersConfiguratorS = new MowersConfigurator(mowerS);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}

		mowersConfiguratorS.startMowers();
		assertEquals(1, mowerS.getxPosition());
		assertEquals(1, mowerS.getyPosition());
		assertEquals('S', mowerS.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreD_12N_12E() {
		//Virage à droite
		try {
			mowerN = new Mower(new Lawn(5, 5), "D", 1, 2, 'N');
			mowersConfiguratorN = new MowersConfigurator(mowerN);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		mowersConfiguratorN.startMowers();
		assertEquals(1, mowerN.getxPosition());
		assertEquals(2, mowerN.getyPosition());
		assertEquals('E', mowerN.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreD_12E_12S() {
		//Virage à droite
		try {
			mowerE = new Mower(new Lawn(5, 5), "D", 1, 2, 'E');
			mowersConfiguratorE = new MowersConfigurator(mowerE);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		mowersConfiguratorE.startMowers();
		assertEquals(1, mowerE.getxPosition());
		assertEquals(2, mowerE.getyPosition());
		assertEquals('S', mowerE.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreD_12S_12W() {
		//Virage à droite
		try {
			mowerS = new Mower(new Lawn(5, 5), "D", 1, 2, 'S');
			mowersConfiguratorS = new MowersConfigurator(mowerS);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		mowersConfiguratorS.startMowers();
		assertEquals(1, mowerS.getxPosition());
		assertEquals(2, mowerS.getyPosition());
		assertEquals('W', mowerS.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreD_12W_12N() {
		//Virage à droite
		try {
			mowerW = new Mower(new Lawn(5, 5), "D", 1, 2, 'W');
			mowersConfiguratorW = new MowersConfigurator(mowerW);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		mowersConfiguratorW.startMowers();
		assertEquals(1, mowerW.getxPosition());
		assertEquals(2, mowerW.getyPosition());
		assertEquals('N', mowerW.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreG_12N_12W() {
		//Virage à gauche
		try {
			mowerN = new Mower(new Lawn(5, 5), "G", 1, 2, 'N');
			mowersConfiguratorN = new MowersConfigurator(mowerN);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		mowersConfiguratorN.startMowers();
		
		assertEquals(1, mowerN.getxPosition());
		assertEquals(2, mowerN.getyPosition());
		assertEquals('W', mowerN.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreG_12E_12N() {
		//Virage à gauche
		try {
			mowerE = new Mower(new Lawn(5, 5), "G", 1, 2, 'E');
			mowersConfiguratorE = new MowersConfigurator(mowerE);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		mowersConfiguratorE.startMowers();
		assertEquals(1, mowerE.getxPosition());
		assertEquals(2, mowerE.getyPosition());
		assertEquals('N', mowerE.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreG_12S_12E() {
		//Virage à gauche
		try {
			mowerS = new Mower(new Lawn(5, 5), "G", 1, 2, 'S');
			mowersConfiguratorS = new MowersConfigurator(mowerS);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		mowersConfiguratorS.startMowers();
		assertEquals(1, mowerS.getxPosition());
		assertEquals(2, mowerS.getyPosition());
		assertEquals('E', mowerS.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreG_12W_12S() {
		//Virage à gauche
		try {
			mowerW = new Mower(new Lawn(5, 5), "G", 1, 2, 'W');
			mowersConfiguratorW = new MowersConfigurator(mowerW);
		} catch (FailedInitializationMowerException e) {
			fail();
		}
		catch (FileInfoException e) {
			fail();
		}
		
		mowersConfiguratorW.startMowers();
		assertEquals(1, mowerW.getxPosition());
		assertEquals(2, mowerW.getyPosition());
		assertEquals('S', mowerW.getDirection());
		
	}
	
	@Test
	public void initialisationPelouse_55() {
		assertEquals(5, lawn.getWidth());
		assertEquals(5, lawn.getLength());
		
	}

}