CREATE TABLE IF NOT EXISTS employees(
    id bigserial primary key,
    name varchar(255),
    country varchar(50),
    created timestamp
);