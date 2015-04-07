CREATE DATABASE IF NOT EXISTS LoginDB;
USE LoginDB;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id          INT PRIMARY KEY UNIQUE AUTO_INCREMENT,
  user_name   VARCHAR(55)       NOT NULL,
  password    VARCHAR(55)       NOT NULL,
  email       VARCHAR(55)       NOT NULL,
  regist_time DATETIME          NOT NULL,
  isConfirmed TINYINT DEFAULT 0 NOT NULL
);

DROP TABLE IF EXISTS amdin;

CREATE TABLE admin (
  id       INT PRIMARY KEY UNIQUE AUTO_INCREMENT,
  name     VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL
);
INSERT INTO admin (name, password) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3');