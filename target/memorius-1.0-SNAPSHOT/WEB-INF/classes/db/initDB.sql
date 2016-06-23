DROP TABLE goals IF EXISTS;

CREATE TABLE goals (
  id INTEGER auto_increment ,
  name VARCHAR (200),
  description VARCHAR(2000),
  deadline DATE,
  creator VARCHAR (50),
  notificationFrequency VARCHAR (50),
  status VARCHAR (10),
  participants VARCHAR (45)
);

DROP TABLE users IF EXISTS;

CREATE TABLE users (
  id INTEGER auto_increment ,
  userName VARCHAR (45),
  email VARCHAR(100),
);