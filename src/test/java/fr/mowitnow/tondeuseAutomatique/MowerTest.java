package fr.mowitnow.tondeuseAutomatique;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MowerTest 
{
	Mower mowerN;
	Mower mowerE;
	Mower mowerS;
	Mower mowerW;

	@Before
	public void SetUp() throws Exception {
		mowerN = new Mower(1, 2, 'N');
		mowerE = new Mower(1, 2, 'E');
		mowerS = new Mower(1, 2, 'S');
		mowerW = new Mower(1, 2, 'W');
	}
	
	@Test
	public void initialisationMower_12N() throws Exception {
		// Position initiale
		assertEquals(1, mowerN.getxPosition());
		assertEquals(2, mowerN.getyPosition());
		assertEquals('N', mowerN.getDirection());
	}
	
	
	@Test
	public void initialisationMowerEnDirectionInconnue_12Z_FailedException() {
		
		Throwable e = null;
		Mower mower;
		
		try {
			mower = new Mower(1, 2, 'Z');
		} catch (Throwable ex) {
		  e = ex;
		}
		
		assertTrue(e instanceof FailedInitializationException);
		
	}
	
	@Test
	public void appliquerOrdreA_12N_13N() {
		
		//Déplacement en avant
		mowerN.moveForward();
		assertEquals(1, mowerN.getxPosition());
		assertEquals(3, mowerN.getyPosition());
		assertEquals('N', mowerN.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreA_12W_02W() {
		
		//Déplacement en avant
		mowerW.moveForward();
		assertEquals(0, mowerW.getxPosition());
		assertEquals(2, mowerW.getyPosition());
		assertEquals('W', mowerW.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreA_12E_22E() {
		//Déplacement en avant
		mowerE.moveForward();
		assertEquals(2, mowerE.getxPosition());
		assertEquals(2, mowerE.getyPosition());
		assertEquals('E', mowerE.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreA_12S_11S() {
		//Déplacement en avant
		mowerS.moveForward();
		assertEquals(1, mowerS.getxPosition());
		assertEquals(1, mowerS.getyPosition());
		assertEquals('S', mowerS.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreD_12N_12E() {
		//Déplacement en avant
		mowerN.turnRight();
		assertEquals(1, mowerN.getxPosition());
		assertEquals(2, mowerN.getyPosition());
		assertEquals('E', mowerN.getDirection());
		
	}
}
