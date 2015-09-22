package chickenlittle.game;

import chickenlittle.control.Action.Actions;

/**
 * Holds the current state of the game including the tile arrangement, players and special game pieces.
 * Don't think any of the logic classes I've got are yours, so replace it with yours if you have it, and
 * make sure you have all the methods (refactor the names so other classes still call them). -Kirsty
 * @author 
 */
public class GameLogic {
	private final char[][] level = new char[20][20];	// char array containing e, w and p
	private int playerX = 10;							// moveable piece
	private int playerY = 10;
	
	/**
	 * Currently creates a 2D array with a randomly designed game board.
	 * Level being sent through for the test is currently 0.
	 * We don't need levels yet so you can ignore it if you want, nothing will happen.
	 * @param userID integer ID of the first player in the game
	 * @param lev
	 */
	public GameLogic(int userID, int lev){
		char[] objects = new char[]{'e', 'e', 'e', 'w'};	// create random game board
		for (int y=0; y<level[0].length; y++){
			for (int x=0; x<level.length; x++){
				level[x][y] = objects[(int) (Math.random()*objects.length)];
			}
		}
		level[playerX][playerY] = 'e';
	}
	
	/**
	 * Create a new game from a saved game state.
	 * @param userID
	 * @param saved 
	 */
	public GameLogic(int userID, GameLogic saved){}
	
	/**
	 * Step 1: Copy or create a 2D char array duplicate of the level
	 * Step 2: Place all moveable objects into their appropriate coordinates in new array
	 * Step 3: return new array 
	 * @return 2D char array containing 'e' for an empty tile, 'w' for a wall, and 'p' for a player
	 */
	public char[][] getLevel(){
		char[][] temp = new char[20][20];
		for (int y=0; y<level[0].length; y++){		// copy all unmovable tiles into temp array
			for (int x=0; x<level.length; x++){
				temp[x][y] = level[x][y];
			}
		}
		temp[playerX][playerY] = 'p';					// place all movable objects into temp array
		return temp;
	}

	/**
	 * Match up ordinal with Actions.ENUM.ordinal. The userID will tell you which player needs to move.
	 * userID may be any unknown integer, but two players will not have the same userID integer.
	 * @param ordinal
	 * @param userID
	 */
	public void parseAction(int ordinal, int userID){
		if (Actions.NORTH.ordinal() == ordinal && playerY > 0 && level[playerX][playerY-1] == 'e'){ playerY--; }
		else if (Actions.WEST.ordinal() == ordinal && playerX > 0 && level[playerX-1][playerY] == 'e'){ playerX--; }
		else if (Actions.SOUTH.ordinal() == ordinal && playerY < level[0].length-1 && level[playerX][playerY+1] == 'e'){ playerY++; }
		else if (Actions.EAST.ordinal() == ordinal && playerX < level.length-1 && level[playerX+1][playerY] == 'e'){ playerX++; }
	}
	
	/**
	 * Need to decide what gets saved and how to send it. This will get sent straight to the DataStorage class so
	 * doesn't need to go through the server connection. 
	 */
	public String getSaveData(){
		return null;
	}
	
	/**
	 * Registers the second player. The server is responsible for ensuring only two players end up in the game.
	 * @param userID
	 */
	public void registerPlayerTwo(int userID){}

}
