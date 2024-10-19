create table drinks(
    id int primary key generated always as identity,
    name varchar(64) not null,
    price int not null,
    volume int not null
);