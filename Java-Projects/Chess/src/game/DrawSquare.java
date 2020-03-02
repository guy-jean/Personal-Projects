package game;

import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawSquare extends JPanel {
	Square[][] squares;
	
	public DrawSquare(Square[][] squares) {
		this.squares = squares;
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    drawSquares(g); 
	}
	
	public void drawSquares(Graphics g) {
		for (int r = 0; r < squares.length; r++) {
	    	for (int c = 0; c < squares[0].length; c++) {
	    		squares[r][c].drawSquare(g);
	    	}
	    }
	}
}


