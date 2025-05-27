-- Active: 1693905023571@@127.0.0.1@3306
PRAGMA foreign_keys = OFF;
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS order_item;
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS "order"(
    ord_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    c_id int,
    "status" VARCHAR(255) DEFAULT "Initial",
    total_cost double DEFAULT 0
);

CREATE TABLE IF NOT EXISTS order_item(
    orditem_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
    ord_id int NOT NULL,
    p_id int,
    quantity int DEFAULT 1,
    total_cost double,
    FOREIGN KEY (ord_id) REFERENCES "order"(ord_id)
);

-- Trigger after an item is INSERTED
CREATE TRIGGER UpdateOrderTotal
    AFTER INSERT ON order_item
BEGIN
    UPDATE "order"
    SET total_cost = (
    SELECT SUM(order_item.total_cost)
    FROM order_item
    WHERE order_item.ord_id = NEW.ord_id
    )
    WHERE "order".ord_id = NEW.ord_id;
END;

-- Trigger after an item is UPDATED
CREATE TRIGGER UpdateOrderTotalAfterUpdate
    AFTER UPDATE ON order_item
BEGIN
    UPDATE "order"
    SET total_cost = (
        SELECT SUM(total_cost)
        FROM order_item
        WHERE ord_id = NEW.ord_id
    )
    WHERE ord_id = NEW.ord_id;
END;

-- Trigger after an item is DELETED
CREATE TRIGGER UpdateOrderTotalAfterDelete
    AFTER DELETE ON order_item
BEGIN
    UPDATE "order"
    SET total_cost = (
        SELECT SUM(total_cost)
        FROM order_item
        WHERE ord_id = OLD.ord_id
    )
    WHERE ord_id = OLD.ord_id;
END;

-- Trigger after order_item quantity becomes 0
CREATE TRIGGER DeleteItemIfQuantityZero
    AFTER UPDATE OF quantity ON order_item
    FOR EACH ROW
BEGIN
    DELETE FROM order_item WHERE orditem_id = NEW.orditem_id AND quantity = 0;
END;

-- Trigger if order has no associated items
CREATE TRIGGER DeleteOrderIfNoItems
    AFTER DELETE ON order_item
    FOR EACH ROW
BEGIN
    DELETE FROM "order" WHERE ord_id = OLD.ord_id AND NOT EXISTS (
        SELECT 1 FROM order_item WHERE ord_id = OLD.ord_id
    );
END;

-- Create an order
INSERT OR IGNORE INTO "order"(ord_id, c_id)
VALUES (1, 1);

-- Create an order
INSERT OR IGNORE INTO "order"(ord_id, c_id, "status")
VALUES (2, 2, "Processed");

-- Create order items
INSERT OR IGNORE INTO order_item(orditem_id, ord_id, p_id, quantity, total_cost)
VALUES (1, 1, 3, 3, 29);

INSERT OR IGNORE INTO order_item(orditem_id, ord_id, p_id, quantity, total_cost)
VALUES (2, 1, 6, 2, 20);

INSERT OR IGNORE INTO order_item(orditem_id, ord_id, p_id, quantity, total_cost)
VALUES (3, 2, 6, 1, 10);