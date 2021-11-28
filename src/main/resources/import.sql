DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), price int, PRIMARY KEY(id));
INSERT INTO products (title, price) VALUES ('Bread', 100), ('Bread Delux', 200), ('Apple', 100), ('Milk', 70), ('Eggs', 100);
