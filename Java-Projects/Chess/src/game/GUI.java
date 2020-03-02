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
import javax.swing.JLabel;

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
	String currCoor;
	Square selectedSquare;
	JLabel turnLabel;
	JLabel dirLabel;
	JLabel checkLabel;
	
	static final String[] IMAGEFILENAMES = new String[] {
			"wpawn.png", "wrook.png", "wknight.png", "wbishop.png", "wqueen.png", "wking.png",
			"bpawn.png", "brook.png", "bknight.png", "bbishop.png", "bqueen.png", "bking.png",
	};
	
	public GUI(int size, Game game) {  
		this.size = size;
		this.game = game;
		this.chessBoard = game.getCurrBoard();
		this.pieces = loadPieces();
		frame = new JFrame("Chess");   
		this.boardSize = (3 * size) /4;
		this.borderSize = (size - boardSize) / 2;
		this.sqSize = boardSize/8;
		currCoor = "";
		squares = new Square[ChessBoard.MAXRANK][ChessBoard.MAXFILE];
		
		turnLabel = new JLabel("WHITE's turn.");
		turnLabel.setBounds((size / 2) - (size/8), borderSize/8, size/4, borderSize/4);
		turnLabel.setHorizontalAlignment(JLabel.CENTER);
		
		dirLabel = new JLabel();
		dirLabel.setBounds((size / 2) - (size/8), borderSize/3, size/4, borderSize/4);
		dirLabel.setHorizontalAlignment(JLabel.CENTER);
		
		checkLabel = new JLabel();
		checkLabel.setBounds((size / 2) - (size/8), size - borderSize, size/4, borderSize/4);
		checkLabel.setHorizontalAlignment(JLabel.CENTER);
		
		frame.add(turnLabel);
		frame.add(dirLabel);
		frame.add(checkLabel);
		
		for (int r = 0; r < ChessBoard.MAXRANK; r++) {
	    	for (int c = 0; c < ChessBoard.MAXFILE; c++) {
	    		Image img = getImage(chessBoard.getPiece(r, c));
	    		ChessPieceColor color = ((r * 8) + c + (r % 2)) % 2 == 0 ? 
	    				ChessPieceColor.WHITE : ChessPieceColor.BLACK;
	    		int x = (c * sqSize) + borderSize;
	    		int y = (r * sqSize) + borderSize;
	    		squares[r][c] = new Square(color, img, x, y, sqSize);
	    		
	    		JButton button = new JButton();
	    		button.setActionCommand(r + "" + c);
	    		button.setBounds(x, y, sqSize, sqSize);
	    		button.setOpaque(false);
	    		button.setContentAreaFilled(false);
	    		button.setBorderPainted(false);
	    		
	    		button.addActionListener(new ActionListener() { 
	    			  public void actionPerformed(ActionEvent e) { 
	    				  	JButton button = (JButton) e.getSource();
	    				    String command = button.getActionCommand();


	
	    				    if (currCoor.length() > 0) {
	    				    	selectedSquare.selected = false;
	    				    	game.takeTurn(currCoor + command);
	    				    	currCoor = "";
	    				    	update();
	    				    	return;
	    				    }
	    				    
	    				    chessBoard = game.getCurrBoard();
	    				    ChessPiece piece = chessBoard.getPiece(Character.getNumericValue(command.charAt(0)), 
	    				    		Character.getNumericValue(command.charAt(1)));
	    				    if (piece != null && piece.getColor() == game.getTurnColor()){
	    				    	currCoor = command;
	    				    	selectedSquare = squares[Character.getNumericValue(currCoor.charAt(0))][Character.getNumericValue(currCoor.charAt(1))];
	    				    	selectedSquare.selected = true;
	    				    }
	    				    else {
	    				    	dirLabel.setText("Invalid Selection");
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
	
	public void update() {
		this.chessBoard = game.getCurrBoard();
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
