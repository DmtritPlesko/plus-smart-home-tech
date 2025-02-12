CREATE TABLE IF NOT EXISTS orders (
    order_id varchar(128) primary key,
    username varchar(64),
    shopping_cart_id varchar(128),
    payment_id varchar(128),
    delivery_id varchar(128),
    state varchar(64),
    delivery_weight double,
    delivery_volume double,
    fragile boolean,
    total_price double,
    delivery_price double,
    product_price double
);

create TABLE IF NOT EXISTS products_orders(
order_id varchar(128) ,
product_id varchar(128) NOT NULL,
quantity INTEGER NOT NULL,
CONSTRAINT products_orders_pk PRIMARY KEY (order_id, product_id),
CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(id) ON delete CASCADE
);