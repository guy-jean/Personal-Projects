package game;

import java.util.ArrayList;

import game.ChessPiece.ChessPieceColor;

public class Game {
	ArrayList<ChessBoard> boardStates;
	int move;
	Player whitePlayer;
	Player blackPlayer;
	
	public Game() {
		boardStates = new ArrayList<ChessBoard>();
		move = 0;
		boardStates.add(new ChessBoard());
		whitePlayer = new Player(ChessPieceColor.WHITE);
		blackPlayer = new Player(ChessPieceColor.BLACK);
	}
}
