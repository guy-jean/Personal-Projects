package game;

import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

import game.ChessPiece.ChessPieceColor;

public class GUI {
	JFrame frame;
	int size;
	public GUI(int size){  
	      this.size = size;
 
	  
	    /*
	    for (int r = 0; r < ChessBoard.MAXRANK; r++) {
	    	for (int c = 0; c < ChessBoard.MAXFILE; c++) {
	    		JButton button = new JButton(new ImageIcon("/Users/blue/Downloads/Chess_bdt60.png"));
	    		button.setMargin(new Insets(0,0,0,0));
	    		Border emptyBorder = BorderFactory.createEmptyBorder();
	    		button.setBorder(emptyBorder);
	    		frame.add(button);
	    	}
	    }
	    frame.setLayout(new GridLayout(8,8,20,20));  

	  
	    frame.setSize(400,400);  
	    frame.setVisible(true);  
	    */
		
		frame = new JFrame();   
		int sqSize = size/8;
		Square[][] squares = new Square[ChessBoard.MAXRANK][ChessBoard.MAXFILE];
		for (int r = 0; r < ChessBoard.MAXRANK; r++) {
	    	for (int c = 0; c < ChessBoard.MAXFILE; c++) {
	    		System.out.println("hey");
	    		String img = "/Users/blue/Downloads/Chess_bdt60.png";
	    		ChessPieceColor color = ((r * 8) + c) % 2 == 0 ? ChessPieceColor.WHITE : ChessPieceColor.BLACK;

	    		int x = c * sqSize;
	    		int y = r * sqSize;
	    		//System.out.println(x);
	    		//System.out.println(y);
	    		//System.out.println(sqSize);
	    		squares[r][c] = new Square(color, img, x, y, sqSize);
	    	}
	    }
		
		frame.add(new DrawSquare(squares));
		frame.setSize(size,size);  
		frame.setVisible(true);  
	} 
	

}
