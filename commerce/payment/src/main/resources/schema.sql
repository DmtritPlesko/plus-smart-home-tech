CREATE TABLE IF NOT EXISTS payments (
    payment_id varchar(128) primary key,
    total_payment double,
    delivery_total double,
    free_total double,
    payment_state varchar(64)
);