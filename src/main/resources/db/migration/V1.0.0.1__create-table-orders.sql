create table orders(
    id int primary key generated always as identity,
    drink varchar(64) not null,
    datetime timestamp

);