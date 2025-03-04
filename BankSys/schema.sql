CREATE TABLE bank_accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOTNULL,
    balance DECIMAL(15,2) NOTNULL,
	acc_type VARCHAR NOTNULL,
    interest_amount DECIMAL(15,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- for test
INSERT INTO bank_accounts (name, balance,acc_type) VALUES ('iyadh', 5000.00,'saving');

SELECT * FROM bank_accounts;

-- drop table bank_accounts;