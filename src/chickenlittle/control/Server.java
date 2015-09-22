package chickenlittle.control;

import chickenlittle.control.Action.Actions;
import chickenlittle.datastorage.DataStorage;
import chickenlittle.game.GameLogic;

/**
 * Placeholder imitating the client-server connection.
 * Receives data from the client side and relays that to the game logic. Relays game state changes to the client on a timer.
 * @author Kirsty
 */
public class Server {
	private final int userID;							// So the game logic knows which player a request is from
	private final DataStorage data = new DataStorage();	// Handles both saving and loading of the game.
	private GameLogic game;						// Interprets user input and updates game state from it
	private Client socket;// This needs to be replaced with the Socket containing the input/output streams
	// input stream
	// output stream
	
	public Server(GameLogic logic, int userID){
		this.game = logic;
		this.userID = userID;
	}
	
	//run(){
		// Needs to send the initial game board through the socket 
		// while true
			// if input stream has data available ...
				// input: performAction(int)
			// output: updateGraphics()
	
	/**
	 * Needs to be retrieved from socket. Actions this class can perform should be retrieved here.
	 * @param ordinal
	 */
	public void performAction(int ordinal){
		if (ordinal == Actions.EXIT.ordinal()){
			System.exit(0);	// Exits should be handled more gracefully than this
		}
		else if (Actions.SAVE.ordinal() == ordinal){ saveGame(); }
		else if (Actions.LOAD.ordinal() == ordinal){ loadGame(); }
		else { game.parseAction(ordinal, userID); }
		updateGraphics();		// graphics should be updated in the run method once a connection is established, not here
	}
	
	/** Socket should go in the constructor, once it's available */
	public void setSocket(Client socket){ this.socket = socket; }
	
	/** char[][] will need to be retrieved from GameLogic and sent through the socket */
	public void updateGraphics(){ socket.updateGraphics(game.getLevel()); }

	/* Need to be know what information needs to be transferred between DataStorage and GameLogic, and how. */
	private void saveGame(){ data.setSaveState(game.getSaveData()); }
	private void loadGame(){ game = new GameLogic(userID, data.getLoadState()); }
	
}
