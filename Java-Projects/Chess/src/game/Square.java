package game;
import java.awt.Color;  
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;
import game.ChessPiece.ChessPieceColor;  

@SuppressWarnings("serial")
public class Square extends JComponent {
	public Image img;
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
	
	public Square(ChessPieceColor color, Image img, int x, int y, int size) {
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
			//String workingDirectory = System.getProperty("user.dir");
			//File f = new File(img);  
			
			//Image i = new ImageIcon(this.getClass().getResource(img)).getImage();
			//g.drawImage(i, x, y,this);
			//Image i;
			//Image i = new ImageIcon(this.getClass().getResource(img)).getImage();
			//g.drawImage(i, x, y,this); 
			Image image = img.getScaledInstance(size, size, Image.SCALE_DEFAULT);
			g.drawImage(image, x, y,this);
		}
	}
	
	public void setImage(Image img) {
		this.img = img;
	}
}
