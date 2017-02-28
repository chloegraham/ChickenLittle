package chickenlittle.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Placeholder for renderer. Pretty sure I have the method names/params right, so just overwrite this and it should work. -Kirsty
 */
public class RenderPane extends JPanel {
	private char[][] staticBoard;
	private int squareSize = 25;
	
	public void setLevel(char[][] board){
		this.staticBoard = board;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (staticBoard == null){ return; }
		
		int xPos = 0;
		int yPos = 0;
		
		g.setColor(Color.blue);
		for (int y=0; y<staticBoard[0].length; y++){
			xPos = 0;
			for (int x=0; x<staticBoard.length; x++){
				g.drawRect(xPos, yPos, squareSize, squareSize);
				if (staticBoard[x][y] != 'e'){
					g.drawString(Character.toString(staticBoard[x][y]), (x+1)*squareSize-15, (y+1)*squareSize-5);
				}
				xPos += squareSize;
			}
			yPos += squareSize;
		}
	}
	
	private static final long serialVersionUID = 1L;

}
