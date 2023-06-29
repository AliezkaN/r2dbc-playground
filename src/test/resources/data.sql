-- -----------------------------------------------------------------------------------
-- INSERT statements for the "client" table
-- -----------------------------------------------------------------------------------
INSERT INTO client (first_name, last_name, email, phone_number, address)
VALUES
    ('John', 'Doe', 'johndoe@example.com', '1234567890', '123 Main St'),
    ('Jane', 'Smith', 'janesmith@example.com', '9876543210', '456 Elm St'),
    ('Michael', 'Johnson', 'michaeljohnson@example.com', '5551234567', '789 Oak Ave'),
    ('Emily', 'Brown', 'emilybrown@example.com', '1112223333', '321 Pine Ln'),
    ('David', 'Davis', 'daviddavis@example.com', '9998887777', '987 Cedar Rd');

-- -----------------------------------------------------------------------------------
-- INSERT statements for the "purchases" table
-- -----------------------------------------------------------------------------------
INSERT INTO purchases (client_id, purchase_date, product_name, quantity, price)
VALUES
    (1, '2023-06-01', 'Product A', 2, 10.99),
    (2, '2023-06-02', 'Product B', 1, 19.99),
    (3, '2023-06-03', 'Product C', 3, 8.99),
    (4, '2023-06-04', 'Product A', 2, 10.99),
    (5, '2023-06-05', 'Product B', 1, 19.99),
    (1, '2023-06-06', 'Product C', 3, 8.99),
    (2, '2023-06-07', 'Product A', 2, 10.99),
    (3, '2023-06-08', 'Product B', 1, 19.99),
    (4, '2023-06-09', 'Product C', 3, 8.99),
    (5, '2023-06-10', 'Product A', 2, 10.99);
