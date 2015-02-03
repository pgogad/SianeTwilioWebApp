CREATE TABLE pay_pal_transactions  ( 
	ID           	bigint(20) AUTO_INCREMENT NOT NULL,
	APPROVAL_OBJ 	varchar(500) NULL,
	USER_ID      	varchar(40) NULL,
	AMOUNT       	varchar(20) NULL,
	APPROVAL_DATE	datetime NULL,
	INSERT_DATE  	varchar(20) NULL,
	PRIMARY KEY(ID)
)
GO
CREATE TABLE user_name  ( 
	EMAIL_ID 	varchar(100) NOT NULL,
	USER_NAME	varchar(50) NOT NULL,
	PASSWORD 	varchar(50) NOT NULL,
	PRIMARY KEY(EMAIL_ID)
)
GO

