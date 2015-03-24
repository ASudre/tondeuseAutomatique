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
	public void appliquerOrdreA() {
		
		//Déplacement en avant
		mower1.move('A');
		assertEquals(1, mower1.getxPosition());
		assertEquals(3, mower1.getyPosition());
		assertEquals('N', mower1.getDirection());
		
	}
}
