DROP TABLE goals IF EXISTS;

CREATE TABLE goals (
  id INTEGER auto_increment ,
  name VARCHAR (50),
  description VARCHAR(1000),
  deadline DATE,
  creator VARCHAR (50),
  notificationFrequency VARCHAR (50),
  status VARCHAR (10)
);