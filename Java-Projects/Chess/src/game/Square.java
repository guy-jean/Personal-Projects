package game;

public class Square {
	private SquareType type;
	private ChessPiece piece;
	
	public enum SquareType{
		LIGHT, DARK
	}
	
	public Square(SquareType type, ChessPiece piece) {
		this.type = type;
		this.piece = piece;
	}
	
	public Square(Square sq) {
		this.type = sq.getType();
		this.piece = sq.getPiece().duplicate();
	}
	
	public SquareType getType() {
		return type;
	}

	public ChessPiece getPiece() {
		return piece;
	}

	public void setPiece(ChessPiece piece) {
		this.piece = piece;
	}
}
