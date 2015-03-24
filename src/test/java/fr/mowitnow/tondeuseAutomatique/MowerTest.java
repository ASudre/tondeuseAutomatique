package fr.mowitnow.tondeuseAutomatique;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MowerTest 
{
	Mower mower1;

	@Before
	public void SetUp() {
		mower1 = new Mower(1, 2, 'N');
	}
	
	@Test
	public void initialisationMower() {
		Mower mower = new Mower(1, 2, 'N');
		
		// Position initiale
		assertEquals(1, mower.getxPosition());
		assertEquals(2, mower.getyPosition());
		assertEquals('N', mower.getDirection());
	}
	
	@Test
	public void appliquerOrdreAAvecUneDirectionNord() {
		
		//Déplacement en avant
		mower1.move('A');
		assertEquals(1, mower1.getxPosition());
		assertEquals(3, mower1.getyPosition());
		assertEquals('N', mower1.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreAAvecUneDirectionOuest() {
		Mower mower = new Mower(1, 2, 'W');
		
		//Déplacement en avant
		mower.move('A');
		assertEquals(0, mower.getxPosition());
		assertEquals(2, mower.getyPosition());
		assertEquals('W', mower.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreAAvecUneDirectionSud() {
		Mower mower = new Mower(1, 2, 'E');
		
		//Déplacement en avant
		mower.move('A');
		assertEquals(2, mower.getxPosition());
		assertEquals(2, mower.getyPosition());
		assertEquals('E', mower.getDirection());
		
	}
	
	@Test
	public void appliquerOrdreAAvecUneDirectionEst() {
		Mower mower = new Mower(1, 2, 'S');
		
		//Déplacement en avant
		mower.move('A');
		assertEquals(1, mower.getxPosition());
		assertEquals(1, mower.getyPosition());
		assertEquals('S', mower.getDirection());
		
	}
}
