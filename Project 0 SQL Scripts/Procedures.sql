-- PL/SQL

--------------------------------------------------------------------------------------------------------------------------------
-- create_user
-- *remember that users can only have even number ids

CREATE OR REPLACE PROCEDURE create_user(username VARCHAR2, password VARCHAR2) IS
BEGIN

INSERT INTO users VALUES (user_seq.nextval, username, password);

END;
/


-- test calls

CALL create_user('tyrannorex', 'iamtheking');
CALL create_user('trisarahtops', 'wyofficialstatedino');
CALL create_user('raptorgurl92', 'jurassicpark!');

SELECT * FROM users;
SELECT username FROM users;

SELECT * FROM users WHERE id = 4;

--------------------------------------------------------------------------------------------------------------------------------
-- create_superuser
-- *remember that superusers can only have odd number ids

CREATE OR REPLACE PROCEDURE create_superuser(username VARCHAR2, password VARCHAR2) IS
BEGIN

INSERT INTO users VALUES (superuser_seq.nextval, username, password);

END;
/


-- test calls

CALL create_superuser('drmalcolm', 'chaotician');

SELECT * FROM users;

SELECT * FROM users WHERE mod(id,2) = 1;

--------------------------------------------------------------------------------------------------------------------------------
-- create_account
-- *remember that accounts can only have ids divisble by 3

CREATE OR REPLACE PROCEDURE create_checking_account(balance NUMBER, user_id NUMBER) IS
BEGIN

INSERT INTO accounts VALUES (account_seq.nextval, 'Checking', balance, user_id);

END;
/

CREATE OR REPLACE PROCEDURE create_savings_account(balance NUMBER, user_id NUMBER) IS
BEGIN

INSERT INTO accounts VALUES (account_seq.nextval, 'Savings', balance, user_id);

END;
/

CREATE OR REPLACE PROCEDURE create_loan_account(balance NUMBER, user_id NUMBER) IS
BEGIN

INSERT INTO accounts VALUES (account_seq.nextval, 'Loan', balance, user_id);

END;
/


-- test calls

SELECT * FROM accounts;

--------------------------------------------------------------------------------------------------------------------------------
-- play!

SELECT * FROM accounts WHERE type = 'Checking' AND user_id = 81;