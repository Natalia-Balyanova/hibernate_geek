DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), price int, PRIMARY KEY(id));
INSERT INTO products (title, price) VALUES ('Bread', 100), ('Bread Delux', 200), ('Apple', 100), ('Milk', 70), ('Eggs', 100);

DROP TABLE customers IF EXISTS;
CREATE TABLE IF NOT EXISTS customers (id bigserial, name VARCHAR(255), PRIMARY KEY(id));
INSERT INTO customers (name) VALUES ('Bob'), ('Max'), ('John');

DROP TABLE customers_products IF EXISTS;
CREATE TABLE IF NOT EXISTS customers_products (customer_id bigint, product_id bigint, FOREIGN KEY (customer_id) REFERENCES customers(id), FOREIGN KEY (product_id) REFERENCES products(id));
INSERT INTO customers_products (customer_id, product_id) VALUES (1, 3), (1, 2), (2, 4), (2, 1), (3, 1), (3, 2);

DROP TABLE orders IF EXISTS;
CREATE TABLE IF NOT EXISTS orders (order_id bigserial, customer_id bigint, product_id bigint, price int, PRIMARY KEY(order_id), FOREIGN KEY(customer_id) REFERENCES customers(id), FOREIGN KEY(product_id) REFERENCES products(id));
INSERT INTO orders (customer_id, product_id, price) VALUES (1, 3, 100), (1, 2, 200), (2, 4, 70), (2, 1, 100), (3, 1, 100), (3, 2, 200);
