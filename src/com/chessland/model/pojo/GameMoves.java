package com.chessland.model.pojo;

public class GameMoves {

	private Integer idGame;
	private Integer moveNum;
	private String pieceMove;

	public Integer getIdGame() {
		return idGame;
	}

	public void setIdGame(Integer idGame) {
		this.idGame = idGame;
	}

	public Integer getMoveNum() {
		return moveNum;
	}

	public void setMoveNum(Integer moveNum) {
		this.moveNum = moveNum;
	}

	public String getPieceMove() {
		return pieceMove;
	}

	public void setPieceMove(String pieceMove) {
		this.pieceMove = pieceMove;
	}

}
