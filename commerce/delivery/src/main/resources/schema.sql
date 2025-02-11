CREATE TABLE IF NOT EXISTS addresses (
    address_id varchar(128) PRIMARY KEY,
    country varchar(128),
    city varchar(128),
    street varchar(128),
    house varchar(128),
    flat varchar(128),
    UNIQUE(country, city, street, house, flat)
);

CREATE TABLE IF NOT EXISTS delivers (
    delivery_id varchar(128) primary key,
    from_address_id varchar(128) references addresses(address_id),
    to_address_id varchar(128) references addresses (address_id),
    order_id varchar(128),
    delivery_state varchar(16)
);