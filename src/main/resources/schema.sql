-- DROP TABLE world_cities;
CREATE TABLE library_books AS SELECT * FROM CSVREAD('classpath:library_books.csv');
CREATE TABLE users AS SELECT * FROM CSVREAD('classpath:Users.csv');