DROP TABLE IF EXISTS Products;

CREATE TABLE IF NOT EXISTS Products (
    id varchar(128) primary key,
    product_name varchar(256),
    description varchar(512),
    image_src varchar(256),
    quantity_state varchar(8),
    product_state varchar(16),
    rating double,
    product_category varchar(16),
    price double
);