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

	AbstractMowersConfigurator mowersConfiguratorN;
	AbstractMowersConfigurator mowersConfiguratorE;
	AbstractMowersConfigurator mowersConfiguratorS;
	AbstractMowersConfigurator mowersConfiguratorW;

	@Before
	public void SetUp() throws Exception {
		lawn = new Lawn(5, 5);
	}
	
	@Test
	public void initializationMower_12N() throws Exception {
		// Position initiale
		Mower mowerN = new Mower(lawn, "", 1, 2, 'N');
		
		assertEquals(1, mowerN.getxPosition());
		assertEquals(2, mowerN.getyPosition());
		assertEquals('N', mowerN.getDirection());
	}
	
	
	@Test
	public void initializationMower_12ZAsParams_FailedExceptionThrown() {
		
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
	public void startMowers_12NAsMowerDescriptionAndMove1Forward_13NPositionExpected() {
		try {
			mowerN = new Mower(new Lawn(5, 5), "A", 1, 2, 'N');
			mowersConfiguratorN = new TestMowersConfigurator(mowerN);
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
	public void startMowers_12WAsMowerDescriptionAndMove1Forward_02WPositionExpected() {

		try {
			mowerW = new Mower(new Lawn(5, 5), "A", 1, 2, 'W');
			mowersConfiguratorW = new TestMowersConfigurator(mowerW);
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
	public void startMowers_12EAsMowerDescriptionAndMove1Forward_22EPositionExpected() {
		try {
			mowerE = new Mower(new Lawn(5, 5), "A", 1, 2, 'E');
			mowersConfiguratorE = new TestMowersConfigurator(mowerE);
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
	public void startMowers_12SAsMowerDescriptionAndMove1Forward_11SPositionExpected() {
		//Déplacement en avant
		try {
			mowerS = new Mower(new Lawn(5, 5), "A", 1, 2, 'S');
			mowersConfiguratorS = new TestMowersConfigurator(mowerS);
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
	public void startMowers_12NAsMowerDescriptionAndTurnRight_12EPositionExpected() {
		//Virage à droite
		try {
			mowerN = new Mower(new Lawn(5, 5), "D", 1, 2, 'N');
			mowersConfiguratorN = new TestMowersConfigurator(mowerN);
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
	public void startMowers_12EAsMowerDescriptionAndTurnRight_12SPositionExpected() {
		//Virage à droite
		try {
			mowerE = new Mower(new Lawn(5, 5), "D", 1, 2, 'E');
			mowersConfiguratorE = new TestMowersConfigurator(mowerE);
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
	public void startMowers_12SAsMowerDescriptionAndTurnRight_12WPositionExpected() {
		//Virage à droite
		try {
			mowerS = new Mower(new Lawn(5, 5), "D", 1, 2, 'S');
			mowersConfiguratorS = new TestMowersConfigurator(mowerS);
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
	public void startMowers_12WAsMowerDescriptionAndTurnRight_12NPositionExpected() {
		//Virage à droite
		try {
			mowerW = new Mower(new Lawn(5, 5), "D", 1, 2, 'W');
			mowersConfiguratorW = new TestMowersConfigurator(mowerW);
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
	public void startMowers_12NAsMowerDescriptionAndTurnLeft_12WPositionExpected() {
		//Virage à gauche
		try {
			mowerN = new Mower(new Lawn(5, 5), "G", 1, 2, 'N');
			mowersConfiguratorN = new TestMowersConfigurator(mowerN);
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
	public void startMowers_12EAsMowerDescriptionAndTurnLeft_12NPositionExpected() {
		//Virage à gauche
		try {
			mowerE = new Mower(new Lawn(5, 5), "G", 1, 2, 'E');
			mowersConfiguratorE = new TestMowersConfigurator(mowerE);
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
	public void startMowers_12SAsMowerDescriptionAndTurnLeft_12EPositionExpected() {
		//Virage à gauche
		try {
			mowerS = new Mower(new Lawn(5, 5), "G", 1, 2, 'S');
			mowersConfiguratorS = new TestMowersConfigurator(mowerS);
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
	public void startMowers_12WAsMowerDescriptionAndTurnLeft_12SPositionExpected() {
		//Virage à gauche
		try {
			mowerW = new Mower(new Lawn(5, 5), "G", 1, 2, 'W');
			mowersConfiguratorW = new TestMowersConfigurator(mowerW);
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
	public void initializationLawn_55() {
		assertEquals(5, lawn.getWidth());
		assertEquals(5, lawn.getLength());
		
	}

}