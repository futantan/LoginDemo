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
)