-- Create database (if not exists)
CREATE DATABASE IF NOT EXISTS pahanaedu CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pahanaedu;

-- Users
CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(64) PRIMARY KEY,
  password_hash VARCHAR(128) NOT NULL,
  full_name VARCHAR(128) NOT NULL
);

-- Customers
CREATE TABLE IF NOT EXISTS customers (
  account_number BIGINT PRIMARY KEY,
  name VARCHAR(128) NOT NULL,
  address VARCHAR(255) NOT NULL,
  telephone VARCHAR(32) NOT NULL,
  units_consumed INT DEFAULT 0
);

-- Items
CREATE TABLE IF NOT EXISTS items (
  id BIGINT PRIMARY KEY,
  name VARCHAR(128) NOT NULL,
  description VARCHAR(255),
  unit_price DECIMAL(12,2) NOT NULL
);

-- Bills
CREATE TABLE IF NOT EXISTS bills (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customer_account_number BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_account_number) REFERENCES customers(account_number)
);

-- Bill Items
CREATE TABLE IF NOT EXISTS bill_items (
  bill_id BIGINT NOT NULL,
  item_id BIGINT NOT NULL,
  item_name VARCHAR(128) NOT NULL,
  unit_price DECIMAL(12,2) NOT NULL,
  quantity INT NOT NULL,
  line_total DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (bill_id, item_id),
  FOREIGN KEY (bill_id) REFERENCES bills(id),
  FOREIGN KEY (item_id) REFERENCES items(id)
);

-- Default admin (password hash of 'admin123' using SHA-256)
INSERT INTO users(username, password_hash, full_name)
VALUES ('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'Administrator')
ON DUPLICATE KEY UPDATE full_name=VALUES(full_name);
