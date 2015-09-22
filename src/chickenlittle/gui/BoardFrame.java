package chickenlittle.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import chickenlittle.control.Listener;

/**
 * The application window for the game. Sets up window sizes and buttons available to the user.
 * @author Thorbukirs
 */
public class BoardFrame extends JFrame {
	private final JPanel BORDER = new JPanel();
	private final Listener LISTENER;
	private final RenderPane RENDER;
	
	/* Determine size of game window */
	private static final int frameWidth = 800;
	private static final int frameHeight = 600;
	private static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static final Dimension FRAMESIZE = new Dimension(frameWidth, frameHeight);
	public static final int FRAMEX = (int) ((dim.getWidth() / 2)-(frameWidth / 2));
	public static final int FRAMEY = (int) ((dim.getHeight() / 2)-(frameHeight / 2));
	
	/**
	 * Sets up the window to display the game and all controls/menus.
	 * Adds Action, Key & Mouse listeners.
	 */
	public BoardFrame(String version, RenderPane renderingWindow, Listener listener) {
		super(version);
		
		LISTENER = listener;
		RENDER = renderingWindow;
		
		buildBorderPanel();
		addListeners();
		
		/* TODO Set up game menus */
		
		
		add(BORDER);
		
		/* Set sizes, locations and decoration options for the full frame. */
		this.setPreferredSize(FRAMESIZE);
		this.setLocation(FRAMEX, FRAMEY);	// Position in centre of screen
		//this.setUndecorated(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
	}
	
	/**
	 * Builds the Panel that displays the game graphics. Includes a solid black border around the Renderer.  
	 */
	private void buildBorderPanel(){
		BoxLayout layout = new BoxLayout(BORDER, BoxLayout.Y_AXIS);
		BORDER.setLayout(layout);
		
		// Add Renderer to the centre of the panel
		BORDER.add(Box.createVerticalGlue());
		BORDER.add(RENDER);
		BORDER.add(Box.createVerticalGlue());
		
		// Set size, alignment and background colour
		Dimension rendererSize = new Dimension(frameWidth-50, frameHeight-50);
		RENDER.setMaximumSize(rendererSize);		// leave a small border around the Renderer
		RENDER.setPreferredSize(rendererSize);
		RENDER.setAlignmentX(CENTER_ALIGNMENT);
		
		BORDER.setPreferredSize(FRAMESIZE);
		BORDER.setBackground(Color.black);
	}

	/**
	 * Require rendering window to maintain focus, and assign all listeners to it.
	 * Requests confirmation and closes the system if player tries to close the window.
	 */
	private void addListeners(){
		RENDER.setFocusable(true);
		
		RENDER.addKeyListener(LISTENER);
		RENDER.addMouseListener(LISTENER);
		RENDER.addFocusListener(new FocusAdapter() {		// Reclaim focus when lost
	          public void focusLost(FocusEvent ev) {
	            RENDER.requestFocus();
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