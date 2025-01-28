CREATE TABLE IF NOT EXISTS carts (
    cart_id varchar(128),
    user_name varchar(64),
    active boolean
);

CREATE TABLE IF NOT EXISTS positions (
    id int primary key GENERATED BY DEFAULT AS IDENTITY,
    product_id varchar(128),
    cart_id varchar(128) references carts(cart_id),
    quantity int
);