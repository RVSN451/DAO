SELECT customName, product_name, amount FROM CUSTOMERS
JOIN ORDERS ON CUSTOMERS.id = ORDERS.customer_id
WHERE customName = UPPER(RTRIM( :name))