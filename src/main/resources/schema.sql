DROP TABLE IF EXISTS CUSTOMERS,ORDERS CASCADE;

CREATE TABLE CUSTOMERS(
                          id int unique auto_increment,
                          customName varchar(20),
                          surname varchar(20),
                          age smallint check ( age >= 0 and age < 150 ),
                          phone_number  varchar(20),
                          PRIMARY KEY (id)
);

CREATE TABLE ORDERS (
                        id int unique auto_increment,
                        date date default(now()),
                        customer_id int not null,
                        product_name varchar(50),
                        amount int not null,
                        PRIMARY KEY (id),
                        FOREIGN KEY (customer_id)
                            REFERENCES CUSTOMERS(id) ON DELETE CASCADE
);



INSERT CUSTOMERS (customName, surname, age, phone_number)
VALUES ('Alexey','Petrov', 28, '68465665465');

INSERT CUSTOMERS (customName, surname, age, phone_number)
VALUES ('Vital','Dog', 25, '63512465');

INSERT CUSTOMERS (customName, surname, age, phone_number)
VALUES ('Serg','Gorelyi', 28, '684665846465');

INSERT CUSTOMERS (customName, surname, age, phone_number)
VALUES ('Ivan','Petrov', 20, '886545');

INSERT CUSTOMERS (customName, surname, age, phone_number)
VALUES ('ALEXEY','Petrov', 20, '886545');

INSERT ORDERS (customer_id, product_name, amount)
VALUES (5, 'TOPOR', 3);


INSERT ORDERS (customer_id, product_name, amount)
VALUES (1, 'Soap', 3);

INSERT ORDERS (customer_id, product_name, amount)
VALUES (2, 'Soap', 5);

INSERT ORDERS (customer_id, product_name, amount)
VALUES (3, 'Oil', 1);

INSERT ORDERS (customer_id, product_name, amount)
VALUES (1, 'Gas', 1);

INSERT ORDERS (customer_id, product_name, amount)
VALUES (4, 'Car', 2);



