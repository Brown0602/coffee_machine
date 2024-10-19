create table ingredients(

    id int primary key generated always as identity,
    name varchar(64) not null,
    quantity int not null
);