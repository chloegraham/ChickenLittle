package chickenlittle.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import chickenlittle.gui.BoardCanvas;
import chickenlittle.gui.BoardFrame;
import chickenlittle.control.Action.Actions;

/**
 * Receives game state info and updates interface.
 * Notifies master connection of player input.
 * @author Thorbukirs
 */
public class Listener implements KeyListener, MouseListener, ActionListener {
	// Stores the direction the screen is currently being stored at. Key Events are be rotated to match, must remain in this order.
	private final Actions[] ROTATION = new Actions[]{Actions.NORTH, Actions.EAST, Actions.SOUTH, Actions.WEST};
	private int DIR = 0;
	
	private final Actions[] ACTIONS;	// All possible movements the player may make
	
	// TODO Network Connection information here ...
	private final Socket socket;
	private DataOutputStream output;
	private DataInputStream input;
	
	public Listener(Socket socket) {
		this.socket = socket;
		ACTIONS = Actions.values();
	}
	
	/**
	 * Run the game while the connection remains established.
	 * Updates the local board from the input stream and displays it.
	 */
	public void run(){
		try {
			output = new DataOutputStream(socket.getOutputStream());
			input = new DataInputStream(socket.getInputStream());
			
			// TODO read setup input
			
			// create new BoardFrame for this client
			
			boolean exit = false;
			while (!exit){												/*Game Loop*/
				// TODO read input data, update game, repaint BoardFrame
			}
			
			socket.close();
		} catch(IOException e) {
			System.err.println("I/O Error: " + e.getMessage());
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * Method for testing JFrame. May cause crashes, use with caution.
	 */
	public void testFrame(){
		BoardFrame frame = new BoardFrame("Chicken Little v0.1", new BoardCanvas(), this);
		frame.setVisible(true);
		boolean exit = false;
		while (!exit){
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	// TODO
		// Use mnemonics to perform actions on the Application Window
		// Any single key presses should be added to the END of the Actions enum set and checked for separately
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int event = e.getKeyCode();
		
		/* Player may choose to rotate screen (i.e. view from a different direction), this changes the movement keys */
		for (int i=0; i<ROTATION.length; i++){
			if (ROTATION[i].getKeyCode() == event){
				int send = i + DIR;			// If the screen has been rotated, the direction will be changed.
				if (send > 3){ send -= 4; }
				// TODO ROTATION[send].ordinal()  to be sent through connection
				return;
			}
		}

		/* All other keys must be sent directly through the server. Find the ordinal and send it. */
		for (Actions ac : ACTIONS){
			if (ac.getKeyCode() == event){
				// TODO ac.ordinal()   to be sent through connection
				return;
			}
		}
		// Any other key pressed is not applicable. Ignore it.
	}
	
	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
