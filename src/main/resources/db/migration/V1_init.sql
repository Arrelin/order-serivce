CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        order_number VARCHAR(255) NOT NULL,
                        sku_code VARCHAR(255) NOT NULL,
                        price NUMERIC(19, 2) NOT NULL,
                        quantity INTEGER NOT NULL
);