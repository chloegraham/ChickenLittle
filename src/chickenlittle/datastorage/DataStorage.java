package chickenlittle.datastorage;

import chickenlittle.game.GameLogic;

/**
 * Save and load game state. Save to host computer for now.
 */
public class DataStorage {
	public void setSaveState(String str){
		if (str == null) return;
		// Parameters yet to be determined.
	}
	
	public GameLogic getLoadState(){ return null; }
}
