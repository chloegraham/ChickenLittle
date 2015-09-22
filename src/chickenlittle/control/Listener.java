package chickenlittle.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import chickenlittle.gui.RenderPane;
import chickenlittle.gui.BoardFrame;
import chickenlittle.control.Action.Actions;

/**
 * Receives game state info and updates interface.
 * Notifies master connection of player input.
 * @author Thorbukirs
 */
public class Listener implements KeyListener, MouseListener, ActionListener {
	// Stores the direction the screen is currently being stored at. Key Events are rotated to match, must remain in this order.
	private final Actions[] ROTATION = new Actions[]{Actions.NORTH, Actions.EAST, Actions.SOUTH, Actions.WEST};
	private int DIR = 0;
	
	private final Actions[] ACTIONS;	// All possible movements the player may make
	
	// TODO Network Connection information here ...
	private InputTesting input;
	private BoardFrame frame;
	
	public Listener() {
		ACTIONS = Actions.values();
	}
	
	/**
	 * Run the game while the connection remains established.
	 * Updates the local board from the input stream and displays it.
	 */
	public void run(){
		input = new InputTesting();
		RenderPane renderer = new RenderPane(input.getMoveable());
		frame = new BoardFrame("Chicken Little v0.1", renderer, this);		// TODO Rendering window assigned in here
		frame.setVisible(true);

		boolean exit = false;
		while (!exit){}												/* Game Loop */
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
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
				input.performAction(ROTATION[send].ordinal());
				frame.repaint();
				return;
			}
		}

		/* All other keys must be sent directly through the server. Find the ordinal and send it. */
		for (Actions ac : ACTIONS){
			if (ac.getKeyCode() == event){
				input.performAction(ac.ordinal());
				frame.repaint();
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
