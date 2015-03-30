package fr.mowitnow.tondeuseAutomatique;

import fr.mowitnow.tondeuseAutomatique.exceptions.FileInfoException;

public class TestMowersConfigurator extends AbstractMowersConfigurator {

	public TestMowersConfigurator(Mower mower) throws FileInfoException {
		addMower(mower);
	}
	
}
