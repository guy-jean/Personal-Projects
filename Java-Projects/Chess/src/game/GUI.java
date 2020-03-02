package game;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import game.ChessPiece.ChessPieceColor;
import game.ChessPiece.ChessPieceType;

public class GUI {
	Game game;
	JFrame frame;
	int size;
	ChessBoard chessBoard;
	int boardSize;
	int borderSize;
	int sqSize;
	Square[][] squares;
	Image[] pieces;
	String currTurn;
	static final String[] IMAGEFILENAMES = new String[] {
			"wpawn.png", "wrook.png", "wknight.png", "wbishop.png", "wqueen.png", "wking.png",
			"bpawn.png", "brook.png", "bknight.png", "bbishop.png", "bqueen.png", "bking.png",
	};
	
	public GUI(int size, ChessBoard chessBoard, Game game) {  
		this.size = size;
		this.chessBoard = chessBoard;
		this.pieces = loadPieces();
		frame = new JFrame("Chess");   
		this.boardSize = (3 * size) /4;
		this.borderSize = (size - boardSize) / 2;
		this.sqSize = boardSize/8;
		currTurn = "";
		
		squares = new Square[ChessBoard.MAXRANK][ChessBoard.MAXFILE];
		for (int r = 0; r < ChessBoard.MAXRANK; r++) {
	    	for (int c = 0; c < ChessBoard.MAXFILE; c++) {
	    		Image img = getImage(chessBoard.getPiece(r, c));
	    		ChessPieceColor color = ((r * 8) + c + (r % 2)) % 2 == 0 ? 
	    				ChessPieceColor.WHITE : ChessPieceColor.BLACK;
	    		int x = (c * sqSize) + borderSize;
	    		int y = (r * sqSize) + borderSize;
	    		squares[r][c] = new Square(color, img, x, y, sqSize);
	    		
	    		JButton button = new JButton(r + "" + c);
	    		button.setActionCommand(r + "" + c);
	    		button.setBounds(x, y, sqSize, sqSize);
	    		button.setOpaque(false);
	    		button.setContentAreaFilled(false);
	    		button.setBorderPainted(false);
	    		
	    		button.addActionListener(new ActionListener() { 
	    			  public void actionPerformed(ActionEvent e) { 
	    				  	JButton button = (JButton) e.getSource();
	    				    String command = button.getActionCommand();
	    				  	System.out.println(command);
	    				    currTurn += command;
	    				    System.out.println(currTurn);
	    				    if (currTurn.length() > 2) {
	    				    	game.takeTurn(currTurn);
	    				    	currTurn = "";
	    				    }
	    			  } 
	    		} );
	    		frame.add(button);
	    	}
	    }
		
		frame.add(new DrawSquare(squares));
		frame.setBounds(0,0,size,size);
		frame.setResizable(false);
		frame.setVisible(true);  
		frame.validate();
	}

	private Image getImage(ChessPiece piece) {
		if (piece == null)
			return null;
		
		ChessPieceType type = piece.getType();
		ChessPieceColor color = piece.getColor();
		
		if (color == ChessPieceColor.WHITE) {
			switch (type) {
			
			case PAWN: return this.pieces[0];
			
			case ROOK: return this.pieces[1];
			
			case KNIGHT: return this.pieces[2];
			
			case BISHOP: return this.pieces[3];
			
			case QUEEN: return this.pieces[4];
			
			case KING: return this.pieces[5];
			
			default: return null;
			
			}
		}
		else {
			switch (type) {
			
			case PAWN: return this.pieces[6];
			
			case ROOK: return this.pieces[7];
			
			case KNIGHT: return this.pieces[8];
			
			case BISHOP: return this.pieces[9];
			
			case QUEEN: return this.pieces[10];
			
			case KING: return this.pieces[11];
			
			default: return null;
			
			}
		}
	} 
	
	public Image[] loadPieces(){
		Image[] pieces = new Image[12];
		try {
			for (int i = 0; i < pieces.length; i++) {
				URL url = getClass().getResource(IMAGEFILENAMES[i]);
				File file = new File(url.getPath());
				pieces[i] = ImageIO.read(file);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pieces;
	}
	
	public void update(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
		for (int r = 0; r < ChessBoard.MAXRANK; r++) {
	    	for (int c = 0; c < ChessBoard.MAXFILE; c++) {
	    		Square square = squares[r][c];
	    		Image img = getImage(chessBoard.getPiece(r, c));
	    		square.setImage(img);
	    	}
	    }
		frame.repaint();
		frame.validate();
	}
}
