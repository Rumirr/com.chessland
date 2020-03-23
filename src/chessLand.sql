CREATE DATABASE CHESS CHARACTER SET utf8 COLLATE utf8_general_ci;
USE CHESS;

CREATE TABLE `User`(
	id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    email VARCHAR(1000) not null unique,
    `name` VARCHAR(255),
    surname VARCHAR(255),
    imgUrl VARCHAR(1000),
    locale VARCHAR(4)
) CHARACTER SET utf8;

CREATE TABLE Game(
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	idUserBlack INT NOT NULL,
    idUserWhite INT NOT NULL,
    winer TINYINT(1) UNSIGNED, -- 0 = ganado negras, 1 = ganado blancas, 3 = empate
    `dateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT USER_BLACK_PIECE FOREIGN KEY (idUserBlack) REFERENCES `User`(id),
    CONSTRAINT USER_WHITE_PIECE FOREIGN KEY (idUserWhite) REFERENCES `User`(ID)
) CHARACTER SET utf8;

CREATE TABLE GameMoves(
	idGame INT NOT NULL,
    moveNum INT NOT NULL,
    pieceMove VARCHAR(10),
    
    CONSTRAINT GAME_ID FOREIGN KEY (idGame) REFERENCES `Game`(id)
) CHARACTER SET utf8;

INSERT INTO `CHESS`.`User`
(`email`,`name`,`surname`)
VALUES
('gustavokurpel2@gmail.com','Gustavo','Kurpel'),
('gustavokurpel@gmail.com','Gustavo','Kurpel');
