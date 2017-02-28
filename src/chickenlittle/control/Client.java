package chickenlittle.control;

import chickenlittle.gui.GameInterface;

/**
 * Placeholder imitating client-server connection.
 * Receives server side information and sends updates to the user interface
 * @author Kirsty
 */
public class Client extends Listener {		// Extends Thread through listener
	private final GameInterface frame = new GameInterface(this, renderer);
	private final Server socket;			// Needs a socket to send information to the server, replace this
	
	public Client(Server master){
		super();
		this.socket = master;
		setOutputStream(this);		// Need to send an output stream from the socket to listener
	}
	
	public void run(){
		socket.updateGraphics();	// read initial game state through the socket 
		
		frame.setVisible(true);
		boolean exit = false;
		while (!exit){												/* Game Loop */
			// if input stream has data available ...
				// Read input (graphics update should be sent through here)
		}
	}
	
	/**
	 * Updates the level that's displayed to the user.
	 * Once an input stream is available, this should create a char array from the input instead.
	 * @param level  2D char array, all values should be 'e', 'w' or 'p'
	 */
	public void updateGraphics(char[][] level){
		renderer.setLevel(level);
		frame.repaint();
	}
	
	/**
	 * Once an output stream is available we can delete this method.
	 */
	public void performAction(int ordinal){
		socket.performAction(ordinal);
	}

}
