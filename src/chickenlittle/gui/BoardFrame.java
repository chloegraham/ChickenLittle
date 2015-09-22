package chickenlittle.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import chickenlittle.control.Listener;

public class BoardFrame extends JFrame {
	private final JPanel BORDER = new JPanel();
	private final Listener LISTENER;
	private final BoardCanvas CANVAS;		// Rendering window
	
	/**
	 * Sets up the window to display the Cluedo game and all controls/menus.
	 * Adds Action, Key & Mouse listeners.
	 */
	public BoardFrame(String version, BoardCanvas renderingWindow, Listener listener) {
		super(version);
		LISTENER = listener;
		CANVAS = renderingWindow;
		CANVAS.setFocusable(true);
		
		addListeners();
		
		/* TODO all layers */
		BORDER.setLayout(new BorderLayout());
		BORDER.add(CANVAS, BorderLayout.CENTER);
		this.add(BORDER);
		
		/* TODO set layer sizes, positioning & decoration */
		this.setPreferredSize(new Dimension(800, 800));

		this.pack();
	}

	/**
	 * Require rendering window to maintain focus, and assign all listeners to it.
	 * Requests confirmation and closes the system if player tries to close the window.
	 */
	private void addListeners(){
		CANVAS.addKeyListener(LISTENER);
		CANVAS.addMouseListener(LISTENER);
		CANVAS.addFocusListener(new FocusAdapter() {		// Reclaim focus when lost
	          public void focusLost(FocusEvent ev) {
	            CANVAS.requestFocus();
	          }
	        });

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		// Use a window listener to close the game
		WindowListener exitListener = new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {				// Override closing event. If OK is not selected, don't do anything.
		        int confirm = JOptionPane.showOptionDialog(null,
		        	"Are you sure you want to exit the game?\nProgress since last save will be lost.\nConnection to server will be closed.", 
		        	"Exit Game", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		           System.exit(0);
		        }
		    }
		};
		addWindowListener(exitListener);
	}
	
	private static final long serialVersionUID = 1L;

}