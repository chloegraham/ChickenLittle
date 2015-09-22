package chickenlittle.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import chickenlittle.control.MoveableTemp;

/**
 * Placeholder for renderer while building user interface.
 * @author Thorbukirs
 */
public class RenderPane extends JPanel {
	// Players draw themselves
	private final MoveableTemp obj;
	
	// Original board
	private int[][] array = new int[100][100];
	private final int squareSize = 25;
	
	public RenderPane(MoveableTemp obj) {
		super();
		setBackground(Color.black);
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
