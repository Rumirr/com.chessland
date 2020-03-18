package com.chessland.model.pojo;

import java.sql.Timestamp;

public class Game {

	private Integer id;
	private Integer idUserBlack;
	private Integer idUserWhite;
	private Byte winer;
	private Timestamp dateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUserBlack() {
		return idUserBlack;
	}

	public void setIdUserBlack(Integer idUserBlack) {
		this.idUserBlack = idUserBlack;
	}

	public Integer getIdUserWhite() {
		return idUserWhite;
	}

	public void setIdUserWhite(Integer idUserWhite) {
		this.idUserWhite = idUserWhite;
	}

	public Byte getWiner() {
		return winer;
	}

	public void setWiner(Byte winer) {
		this.winer = winer;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

}
