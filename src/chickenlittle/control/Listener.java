package chickenlittle.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import chickenlittle.control.Action.Actions;
import chickenlittle.gui.RenderPane;

/**
 * Notifies master connection of player input.
 * Organises camera rotation.
 * @author Kirsty
 */
public class Listener extends Thread implements KeyListener, MouseListener, ActionListener {
	protected final RenderPane renderer = new RenderPane();
	// Stores the direction the screen is currently being shown at. Key Events are rotated to match, must remain in this order.
	private final Actions[] ROTATION = new Actions[]{Actions.NORTH, Actions.EAST, Actions.SOUTH, Actions.WEST};
	private int DIR = 0;
	
	private final Actions[] ACTIONS = Actions.values();	// All possible movements the player may make
	private Client slave;		// To be replaced with the output stream once it is available
	
	public Listener() {}		// Contructor should take socket
	
	/**
	 * Needs a socket through which the key presses can be sent. Move to constructor when available.
	 */
	public void setOutputStream(Client slave){
		this.slave = slave;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Use mnemonics to perform actions on the Application Window
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int event = e.getKeyCode();
		
		/* First let's check for camera rotations. Does not yet rotate the actual view. */
		if (Actions.CAMROTATELEFT.getKeyCode() == event){
			DIR--;
			if (DIR < 0) DIR = ROTATION.length-1;
			return;
		}
		if (Actions.CAMROTATERIGHT.getKeyCode() == event){
			DIR--;
			if (DIR >= ROTATION.length) DIR = 0;
			return;
		}
		
		/* Change the movement direction (if player has chosen to rotate the screen) */
		for (int i=0; i<ROTATION.length; i++){
			if (ROTATION[i].getKeyCode() == event){
				int send = i + DIR;			// If the screen has not been rotated, the direction will not be changed.
				if (send > 3){ send -= 4; }
				slave.performAction(ROTATION[send].ordinal());
				return;
			}
		}
		
		/* Make sure the user actually wants to exit if they've pressed Escape */
		if (event == Actions.EXIT.getKeyCode()){
			exitGame();
		}

		/* All other keys must be sent directly through the server. Find the ordinal and send it. */
		for (Actions ac : ACTIONS){
			if (ac.getKeyCode() == event){
				slave.performAction(ac.ordinal());
				return;
			}
		}
		// Any other key pressed is not applicable. Ignore it.
	}
	
	/**
	 * Checks that the user really wants to exit the game. If so, sends for the connection to be close.
	 */
	private void exitGame(){
		int confirm = JOptionPane.showOptionDialog(null,
			"Are you sure you want to exit the game?\nProgress since last save will be lost.\nConnection to server will be closed.", 
			"Exit Game", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			slave.performAction(Actions.EXIT.ordinal());
		}
		else { return; }
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}

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
