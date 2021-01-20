-- One User can have many Accounts (one-to-many relationship)

--------------------------------------------------------------------------------------------------------------------------------
COMMIT;
ROLLBACK;

--------------------------------------------------------------------------------------------------------------------------------
-- CREATE TABLES

DROP TABLE users;
CREATE TABLE users (
    id NUMBER(10) PRIMARY KEY,
    username VARCHAR2(20) UNIQUE,
    password VARCHAR2(20)
);

DROP TABLE accounts;
CREATE TABLE accounts (
    id NUMBER(10) PRIMARY KEY,
    type VARCHAR2(10),
    balance NUMBER(20,2),
    user_id NUMBER(10),
    CONSTRAINT account_type CHECK (type IN ('Checking', 'Loan', 'Savings'))
);

--------------------------------------------------------------------------------------------------------------------------------
-- ADD FOREIGN KEYS

ALTER TABLE accounts DROP CONSTRAINT fk_account_user;

ALTER TABLE accounts ADD CONSTRAINT fk_account_user FOREIGN KEY
(user_id) REFERENCES users(id) ON DELETE CASCADE;

--------------------------------------------------------------------------------------------------------------------------------
-- ADD CONSTRAINT

ALTER TABLE users MODIFY (username NOT NULL);

ALTER TABLE users MODIFY (password NOT NULL);

--------------------------------------------------------------------------------------------------------------------------------
-- CREATE SEQUENCES

DROP SEQUENCE user_seq;
DROP SEQUENCE superuser_seq;
DROP SEQUENCE account_seq;

CREATE SEQUENCE user_seq
    START WITH 2
    INCREMENT BY 2;
    
CREATE SEQUENCE superuser_seq
    START WITH 1
    INCREMENT BY 2;

CREATE SEQUENCE account_seq
    START WITH 111
    INCREMENT BY 3;