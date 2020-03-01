package game;
import java.awt.Color;  
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

import game.ChessPiece.ChessPieceColor;  

public class Square extends JComponent{
	public String img;
	public ChessPieceColor color;
	public int size;
	public int x;
	public int y;
	public Square(ChessPieceColor color, int x, int y, int size) {
		this.img = null;
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public Square(ChessPieceColor color, String img, int x, int y, int size) {
		this.color = color;
		this.img = img;
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	
	public void drawSquare(Graphics g) {  
		
		if (color == ChessPieceColor.BLACK) {
			g.setColor(Color.gray);  
        	g.fillRect(x, y, size, size);
		}
		else {
			g.setColor(Color.white);  
			g.fillRect(x, y, size, size);
		}
		
		if (img != null) {
			Toolkit t = Toolkit.getDefaultToolkit();  
			Image i = t.getImage(img);  
			g.drawImage(i, x, y,this);  
		}
      }
}
