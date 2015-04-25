DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS stock;

CREATE TABLE people (
  person_id  INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(20),
  last_name  VARCHAR(20),
  PRIMARY KEY (person_id)
);

CREATE TABLE stock (
  stock_id  INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20),
  price  DECIMAL(10,2),
  PRIMARY KEY (stock_id)
);