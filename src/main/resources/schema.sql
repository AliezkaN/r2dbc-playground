-- -------------------------------------------------------
-- Creating the "client" table
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS client
(
    client_id    SERIAL PRIMARY KEY,
    first_name   VARCHAR(50),
    last_name    VARCHAR(50),
    email        VARCHAR(100),
    phone_number VARCHAR(20),
    address      VARCHAR(200)
);

-- -------------------------------------------------------
-- Creating the "purchases" table
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS purchases
(
    purchase_id   SERIAL PRIMARY KEY,
    client_id     INT REFERENCES client (client_id),
    purchase_date DATE,
    product_name  VARCHAR(100),
    quantity      INT,
    price         DECIMAL(10, 2)
);