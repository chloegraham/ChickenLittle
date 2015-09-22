package chickenlittle;

import chickenlittle.control.Listener;
import chickenlittle.gui.MainMenuDialog;

/**
 * Loads Main Menu and game board for the client, and allows player to establish a connection.
 * 
 * @author Thorbukirs
 */
public class Main {
	private static final MainMenuDialog MENU = new MainMenuDialog();;
	private static boolean EXIT = false;

	public static void main(String[] args) {
		while (!EXIT){
			MENU.setVisible(true);
			readInput(MENU.getPressedButton());
		}
		// TODO check for network connection and safely close it
		MENU.dispose();
		System.exit(0);
	}
	
	/**
	 * Establishes a connection with the server.
	 * This server is where a saved game may be stored and loaded from.
	 * @return true if a connection was successfully established, false otherwise
	 */
	private static boolean establishConnection(){
		return false;				// TODO
	}
	
	/**
	 * Checks whether a game is currently active on the server.
	 * Does not attempt to join the game.
	 * REQUIRES: an active network connection
	 * @return true if a game is active on the server, false otherwise
	 */
	private static boolean checkGameInProgress(){
		return false;				// TODO
	}
	
	/**
	 * Checks whether progress in a game is currently saved on the server.
	 * Does not attempt to load the save.
	 * REQUIRES: an active network connection
	 * @return true if a game is stored on the server, false otherwise
	 */
	private static boolean checkForAvailableSave(){
		return false;				// TODO
	}
	
	/**
	 * Creates a new game state.
	 * REQUIRES: an active network connection
	 * REQUIRES: no game active on the server
	 */
	private static void createNewGame(){	// TODO
		// Tell server to set up a new game
		// Create Listener
		// listener.run();
	}
	
	/**
	 * Continue the last game that was saved to the server
	 * REQUIRES: an active network connection
	 * REQUIRES: no game active on the server
	 * REQUIRES: a saved game on the server
	 */
	private static void continueGame(){		// TODO
		// Tell server to set up a new game from the last save it has stored
		// Create Listener
		// listener.run();
	}
	
	/**
	 * Join the game currently active on the server
	 * REQUIRES: an active network connection
	 * REQUIRES: a game active on the server
	 */
	private static void joinGame(){			// TODO
		// Join game as second player
		// if successful ...
			// Create Listener
			// listener.run();
	}
	
/*
	private static void connectionLost(){
		menu.enableButton(buttons.CONNECT, true);
		menu.enableButton(buttons.NEWGAME, false);
		menu.enableButton(buttons.JOINGAME, false);
		menu.enableButton(buttons.CONTINUE, false);
		menu.setMessage("Connection to the server lost.");
	}
*/
	
	/**
	 * Checks which button a user has pressed and responds
	 * @param input int  ordinal from button pushed
	 */
	private static void readInput(int input){
		if (input == -1){ return; }
		switch (input){
			case MainMenuDialog.CONNECT:
				connectToServer();
				break;
			case MainMenuDialog.NEWGAME:
				createNewGame();
				break;
			case MainMenuDialog.CONTINUE:
				continueGame();
				break;
			case MainMenuDialog.JOINGAME:
				joinGame();
				break;
			case MainMenuDialog.TESTFRAME:	// Test the game frame. May cause crashes.
				new Listener(null).testFrame();
				break;
			case MainMenuDialog.QUIT:
				EXIT = true;
		}
	}
	
	/**
	 * Attempt to establish a connection, update buttons and inform user of success/failure.
	 */
	private static void connectToServer(){
		if (establishConnection()){		// successfully connected to server
			boolean gameInProgress = checkGameInProgress();
			MENU.enableButton(MainMenuDialog.CONNECT, false);
			if (gameInProgress){							// Someone is currently playing on this server
				MENU.enableButton(MainMenuDialog.JOINGAME, true);
			}
			else if (checkForAvailableSave()){				// No game to join, check if there is one to load
				MENU.enableButton(MainMenuDialog.CONTINUE, true);
			}
			if (!gameInProgress){							// No game to join, but can create a new one
				MENU.enableButton(MainMenuDialog.NEWGAME, true);
			}
			MENU.setMessage("Successfully connected to the server.");
		}
		else {							// failed to connect to the server
			MENU.setMessage("Failed to connect to the server. Please check your connection and try again later.");
		}
	}
	
}
