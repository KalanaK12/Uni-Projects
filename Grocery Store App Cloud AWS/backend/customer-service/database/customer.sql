-- Active: 1693905292143@@127.0.0.1@3306
PRAGMA foreign_keys = OFF;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS "Address";
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS Customer(
    c_id INTEGER PRIMARY KEY AUTOINCREMENT,
    addr_id int,
    c_fname VARCHAR(255),
    c_lname VARCHAR(255),
    c_email VARCHAR(255),
    c_phnum VARCHAR(255),
    c_passwd VARCHAR(255),
    FOREIGN KEY (addr_id) REFERENCES Address (addr_id)
);

CREATE TABLE IF NOT EXISTS "Address"(
    addr_id INTEGER PRIMARY KEY AUTOINCREMENT,
    addr_strtnum int,
    addr_street VARCHAR(255),
    addr_city VARCHAR(255),
    addr_postcode VARCHAR(255),
    addr_state VARCHAR(255),
    addr_country VARCHAR(255)
);

-- Create addresses
INSERT OR IGNORE INTO "Address"(addr_strtnum, addr_street, addr_city, addr_postcode, addr_state, addr_country)
VALUES (17, "Kalorama Street", "Tarneit", "3029", "Victoria", "Australia");

INSERT OR IGNORE INTO "Address"(addr_strtnum, addr_street, addr_city, addr_postcode, addr_state, addr_country)
VALUES (15, "Bezos Street", "Tarneit", "3029", "Victoria", "Australia");

INSERT OR IGNORE INTO "Address"(addr_strtnum, addr_street, addr_city, addr_postcode, addr_state, addr_country)
VALUES (14, "Gates Street", "Tarneit", "3029", "Victoria", "Australia");

-- Create users
INSERT OR IGNORE INTO "Customer"(addr_id, c_fname, c_lname, c_email, c_phnum, c_passwd)
VALUES (1, "Salmaan", "Nagoormira", "salmaan@nagoormira.com", "9323747232", "somehashhere");

INSERT OR IGNORE INTO "Customer"(addr_id, c_fname, c_lname, c_email, c_phnum, c_passwd)
VALUES (2, "Jeff", "Bezos", "salmaan@nagoormira.com", "9323747232", "somehashhere");

INSERT OR IGNORE INTO "Customer"(addr_id, c_fname, c_lname, c_email, c_phnum, c_passwd)
VALUES (3, "Bill", "Gates", "salmaan@nagoormira.com", "9323747232", "somehashhere");

