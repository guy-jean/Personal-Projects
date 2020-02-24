package game;

import game.ChessPiece.ChessPieceColor;

public class Player {
	ChessPieceColor color;
	boolean inCheck;
	
	public Player(ChessPieceColor color) {
		this.color = color;
	}
}
