package chickenlittle.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Creates the Main Menu dialog window, manages the user's input until a game state is entered
 * 
 * @author Thorbukirs
 */
public class MainMenuDialog extends JDialog implements ActionListener {

	/* All actions available to user.
	 * Each should have a JButton at the matching ordinal. */
	private JButton[] buttonArr = new JButton[]{ new JButton("Connect"), new JButton("New Game"),
							new JButton("Continue"), new JButton("Join Game"), new JButton("Quit"), new JButton("Test Frame") };
		public static final int CONNECT = 0;
		public static final int NEWGAME = 1;
		public static final int CONTINUE = 2;
		public static final int JOINGAME = 3;
		public static final int QUIT = 4;
		public static final int TESTFRAME = 5;

	private static final Color borderCol = new Color(0, 20, 70);
	private static final Color enabledCol = new Color(20, 20, 50);
	private static final Color disabledCol = new Color(10, 10, 20);
	private static final Color textCol = Color.white;
	private static final Color bgCol = Color.black;
	
	private static final JFrame FRAME = new JFrame();
	private static final JTextPane TEXT = new JTextPane();
	private int input = -1;
	
	public MainMenuDialog() {
		super(FRAME, true);
		
		// Assign all window sizes
		final int btnHeight = 40;
		final int textHeight = btnHeight*2;
		final int menuWidth = 300;
		final int menuHeight = buttonArr.length * btnHeight;
		final int frameWidth = menuWidth + 500;
		final int frameHeight = menuHeight + textHeight + 300;
		
		// Set up JTextPane
		TEXT.setMaximumSize(new Dimension(menuWidth, textHeight));
		TEXT.setForeground(textCol);
		TEXT.setBackground(bgCol);
		TEXT.setEditable(false);
		TEXT.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		// Create display panel (buttons, text and background)
		JPanel buttonPane = createButtonPane(menuWidth, menuHeight, new Dimension(menuWidth, btnHeight));
		JPanel infoPane = createInfoPane(menuWidth, menuHeight+textHeight, buttonPane);
		JPanel displayPane = createDisplayPane(menuWidth, frameHeight, infoPane);
		this.setContentPane(displayPane);

		// Decoration settings
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int centreX = (int) (dim.getWidth() / 2);
		int centreY = (int) (dim.getHeight() / 2);
		this.setLocation(centreX-(frameWidth/2), centreY-(frameHeight/2));	// Position in centre of screen
		this.setUndecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
	}
	
	/**
	 * Short message to be displayed to user.
	 * Will display maximum of 37cols, 2 rows of characters, 
	 * @param text to be displayed
	 */
	public void setMessage(String text){
		TEXT.setText(text);
	}
	
	/**
	 * Gets the button the user pressed.
	 * All button options match public fields.
	 * @return -1 if no button pressed, or >= 0 if a button was pressed
	 */
	public int getPressedButton(){
		return input;
	}
	
	/**
	 * Enable or disable the button with the given ordinal.
	 * Does nothing if no matching ordinal.
	 * @param btn button to change state of
	 * @param value true to enable button, false to disable
	 */
	public void enableButton(int btn, boolean value){
		if (btn < buttonArr.length){
			JButton button = buttonArr[btn];
			button.setEnabled(value);
			if (value) button.setBackground(enabledCol);
			else button.setBackground(disabledCol);
		}
	}
	
	/**
	 * Create Pane containing all user's options and background
	 * @param width
	 * @param height
	 * @param infoPane holds all options available to user
	 * @return completed panel
	 */
	private JPanel createDisplayPane(int width, int height, JPanel infoPane){
		// Read background image file
		Image img = null;
		try {
			img = ImageIO.read(new File("MainMenuBG.png"));
		} catch (IOException e1) { e1.printStackTrace(); }
		final Image image = img;
		
		// Create panel and set image to be drawn
		JPanel pane = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) g.drawImage(image, 0, 0, null);	// Draws background image. Will not scale.
			}
			private static final long serialVersionUID = 1L;
		};
		
		// Align content in centre
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.add(Box.createVerticalGlue());
		pane.add(infoPane);
		pane.add(Box.createVerticalGlue());
		infoPane.setAlignmentX(CENTER_ALIGNMENT);

		pane.setBackground(bgCol);	// Colour if image doesn't load, or is too small to fill window
		pane.setPreferredSize(new Dimension(width, height));
		return pane;
	}
	
	/**
	 * Create panel containing buttons and Text pane.
	 * Components will fill panel.
	 * @param width
	 * @param height
	 * @param buttonPane must contain all available buttons
	 * @return completed panel
	 */
	private JPanel createInfoPane(int width, int height, JPanel buttonPane){
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		pane.add(buttonPane);
		pane.add(TEXT);

		buttonPane.setAlignmentX(LEFT_ALIGNMENT);
		TEXT.setAlignmentX(LEFT_ALIGNMENT);

		pane.setMaximumSize(new Dimension(width, height));
		return pane;
	}
	
	/**
	 * Assigns listeners and decorations to all buttons, and assigns buttons to panel
	 * @param width
	 * @param height
	 * @param size button size
	 * @return completed panel
	 */
	private JPanel createButtonPane(int width, int height, Dimension size){
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		for (JButton btn : buttonArr){
			btn.addActionListener(this);
			btn.setPreferredSize(size);
			btn.setMaximumSize(size);
			btn.setForeground(textCol);
			btn.setBackground(enabledCol);
			btn.setFocusPainted(false);
			btn.setBorder(new LineBorder(borderCol, 1));
			pane.add(btn);
		}
		enableButton(NEWGAME, false);		// Disable buttons that can't be used until connected to server
		enableButton(CONTINUE, false);
		enableButton(JOINGAME, false);
		
		pane.setMaximumSize(new Dimension(width, height));
		return pane;
	}
	
	/**
	 * Ask user if they really want to quit. If yes, set input to QUIT; if no, ignore.
	 */
	private void quit(){
		int test = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?", "Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		if (test==0){
			input = QUIT;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		input = -1;
		String value = e.getActionCommand();
		switch (value){
			case "Connect":
				input = CONNECT;
				break;
			case "New Game":
				input = NEWGAME;
				break;
			case "Continue":
				input = CONTINUE;
				break;
			case "Join Game":
				input = JOINGAME;
				break;
			case "Test Frame":
				input = TESTFRAME;
				break;
			case "Quit":
				quit();
		}
		dispose();
	}
		
	private static final long serialVersionUID = 1L;
}
