package chickenlittle;

import chickenlittle.control.Server;
import chickenlittle.control.Client;
import chickenlittle.game.GameLogic;
import chickenlittle.gui.MainMenuDialog;

/**
 * Checks what options are available to the user and loads the main menu.
 * Creates a new game and establishes a network connection for the player.
 * @author Kirsty
 */
public class Main {
	private static final MainMenuDialog mainMenu = new MainMenuDialog();;
	private static boolean exitGame = false;

	public static void main(String[] args) {
		while (!exitGame){
			mainMenu.setVisible(true);
			readInput(mainMenu.getPressedButton());
		}
		System.exit(0);
	}
	
	/**
	 * @return true if a connection can be successfully established, false otherwise
	 */
	private static boolean establishConnection(){
		return false;				// TODO
	}
	
	/**
	 * Does not attempt to join the game, just checks if one is active.
	 * @return true if a game is active on the server, false otherwise
	 */
	private static boolean checkGameInProgress(){
		return false;				// TODO
	}
	
	/**
	 * Does not attempt to load the save, just checks if it's there.
	 * @return true if a game is stored on the server, false otherwise
	 */
	private static boolean checkForAvailableSave(){
		return false;				// TODO
	}
	
	/**
	 * Creates a new game state, waits for a second player, and runs it.
	 */
	private static void createNewGame(){	// TODO
		// Tell server to set up a new game
		// Create Listener
		// client.run();
		// if failed...
		connectionFailure("Unable to create a new game. Please check your connection and try again later.");
	}
	
	/**
	 * Continue the last game that was saved to the server
	 */
	private static void continueGame(){		// TODO
		connectionFailure("Unable to continue game. Please check your connection and try again later.");
	}
	
	/**
	 * Join the game currently active on the server
	 */
	private static void joinGame(){			// TODO
		connectionFailure("Unable to join game. Please check your connection and try again later.");
	}
	
	private static void connectionFailure(String message){
		mainMenu.enableButton(MainMenuDialog.CONNECT, true);
		mainMenu.enableButton(MainMenuDialog.NEWGAME, false);
		mainMenu.enableButton(MainMenuDialog.JOINGAME, false);
		mainMenu.enableButton(MainMenuDialog.CONTINUE, false);
		mainMenu.setMessage(message);
	}
	
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
			case MainMenuDialog.TESTFRAME:	// Test the game frame
				GameLogic game = new GameLogic(0, 0); 		// Create a new game state. One game state for all masters.
				Server server = new Server(game, 0);	// Establish a connection between client and server. Will need to add a socket to send data through.
				Client client = new Client(server);
				server.setSocket(client);				// Need a socket for input/output streams
				client.run(); 
				break;
			case MainMenuDialog.QUIT:
				exitGame = true;
		}
	}
	
	/**
	 * Attempt to establish a connection, update buttons and inform user of success/failure.
	 */
	private static void connectToServer(){
		if (establishConnection()){
			boolean gameInProgress = checkGameInProgress();
			mainMenu.enableButton(MainMenuDialog.CONNECT, false);
			if (gameInProgress){							// Someone is currently playing on this server
				mainMenu.enableButton(MainMenuDialog.JOINGAME, true);
			}
			else if (checkForAvailableSave()){				// No game to join, check if there is one to load
				mainMenu.enableButton(MainMenuDialog.CONTINUE, true);
			}
			if (!gameInProgress){							// No game to join, but can create a new one
				mainMenu.enableButton(MainMenuDialog.NEWGAME, true);
			}
			mainMenu.setMessage("Successfully connected to the server.");
		}
		else {							// failed to connect to the server
			mainMenu.setMessage("Failed to connect to the server. Please check your connection and try again later.");
		}
	}
	
}
