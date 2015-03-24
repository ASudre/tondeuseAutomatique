package fr.mowitnow.tondeuseAutomatique;

import static org.junit.Assert.*;

import java.util.LinkedList;

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
	Lawn lawn;

	@Before
	public void SetUp() throws Exception {
		mowerN = new Mower(1, 2, 'N');
		mowerE = new Mower(1, 2, 'E');
		mowerS = new Mower(1, 2, 'S');
		mowerW = new Mower(1, 2, 'W');
		
		lawn = new Lawn(5, 5);
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
		
		@SuppressWarnings("unused")
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
	
	@Test
	public void appliquerOrdreD_12E_12S() {
		//Déplacement en avant
		mowerE.turnRight();
		assertEquals(1, mowerE.getxPosition());
		assertEquals(2, mowerE.getyPosition());
		assertEquals('S', mowerE.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreD_12S_12W() {
		//Déplacement en avant
		mowerS.turnRight();
		assertEquals(1, mowerS.getxPosition());
		assertEquals(2, mowerS.getyPosition());
		assertEquals('W', mowerS.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreD_12W_12N() {
		//Déplacement en avant
		mowerW.turnRight();
		assertEquals(1, mowerW.getxPosition());
		assertEquals(2, mowerW.getyPosition());
		assertEquals('N', mowerW.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreG_12N_12W() {
		//Déplacement en avant
		mowerN.turnLeft();
		assertEquals(1, mowerN.getxPosition());
		assertEquals(2, mowerN.getyPosition());
		assertEquals('W', mowerN.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreG_12E_12N() {
		//Déplacement en avant
		mowerE.turnLeft();
		assertEquals(1, mowerE.getxPosition());
		assertEquals(2, mowerE.getyPosition());
		assertEquals('N', mowerE.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreG_12S_12E() {
		//Déplacement en avant
		mowerS.turnLeft();
		assertEquals(1, mowerS.getxPosition());
		assertEquals(2, mowerS.getyPosition());
		assertEquals('E', mowerS.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreG_12W_12S() {
		//Déplacement en avant
		mowerW.turnLeft();
		assertEquals(1, mowerW.getxPosition());
		assertEquals(2, mowerW.getyPosition());
		assertEquals('S', mowerW.getDirection());
		
	}
	
	@Test
	public void initialisationPelouse_55() {
		assertEquals(5., lawn.getWidth(), 0.001);
		assertEquals(5., lawn.getLength(), 0.001);
		
	}
	
	@Test
	public void ajoutPelouse2Tondeuses_12Wpuis12S() {
		Lawn lawn = new Lawn(5, 5);
		
		lawn.addMower(mowerW);
		lawn.addMower(mowerS);
		lawn.addMower(mowerN);
		
		Mower firstMower = ((LinkedList<Mower>)lawn.getMowers()).poll();
		// 1ère tondeuse de direction ouest
		assertEquals(1, firstMower.getxPosition());
		assertEquals(2, firstMower.getyPosition());
		assertEquals('W', firstMower.getDirection());
		
		Mower secondMower = ((LinkedList<Mower>)lawn.getMowers()).poll();
		// 2ème tondeuse de direction sud
		assertEquals(1, secondMower.getxPosition());
		assertEquals(2, secondMower.getyPosition());
		assertEquals('S', secondMower.getDirection());
		
		Mower thirdMower = ((LinkedList<Mower>)lawn.getMowers()).poll();
		// 3ème tondeuse de direction nord
		assertEquals(1, thirdMower.getxPosition());
		assertEquals(2, thirdMower.getyPosition());
		assertEquals('N', thirdMower.getDirection());
		
		assertNull(((LinkedList<Mower>)lawn.getMowers()).poll());
		
	}
}
