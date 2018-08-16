SET SCHEMA 'banking_app';

CREATE TABLE accounts (
	accountnumber VARCHAR(10) PRIMARY KEY,
	balance INTEGER,
	accounttype VARCHAR(20)
	);

CREATE TABLE users (
	username VARCHAR(10) PRIMARY KEY,
	password VARCHAR(15),
	firstname VARCHAR(15),
	lastname VARCHAR(15),
	admin VARCHAR(8)
);

CREATE TABLE users_accounts (
	username VARCHAR(10) REFERENCES users(username),
	accountnumber VARCHAR(10) REFERENCES accounts(accountnumber),
	PRIMARY KEY (username, accountnumber)
);

CREATE TABLE user_transaction_history (
	accountnumber VARCHAR(10) REFERENCES accounts(accountnumber),
	transactionhistory VARCHAR(40)
);