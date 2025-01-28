CREATE TABLE IF NOT EXISTS products (
    product_id varchar(128) primary key,
    fragile boolean,
    width double,
    height double,
    depth double,
    weight double,
    quantity int
);