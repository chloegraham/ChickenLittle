package chickenlittle.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import chickenlittle.control.MoveableTemp;

/**
 * Placeholder for renderer while building user interface.
 * @author Thorbukirs
 */
public class BoardCanvas extends JPanel {
	// Players draw themselves
	private final MoveableTemp obj;
	
	// Original board
	private int[][] array = new int[20][10];
	private final int squareSize = 25;
	
	public BoardCanvas(MoveableTemp obj) {
		super();
		setBackground(Color.white);
		this.obj = obj;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int xPos = 0;
		int yPos = 0;
		
		g.setColor(Color.blue);
		for (int y=0; y<array[0].length; y++){
			xPos = 0;
			for (int x=0; x<array.length; x++){
				g.drawRect(xPos, yPos, squareSize, squareSize);
				xPos += squareSize;
			}
			yPos += squareSize;
		}
		g.setColor(Color.green);
		g.drawRect(obj.getX()*squareSize, obj.getY()*squareSize, squareSize, squareSize);
	}
	
	private static final long serialVersionUID = 1L;

}
