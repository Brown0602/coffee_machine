CREATE TABLE coffee_machine(
    id int primary key generated always as identity,
    volume_water int,
    volume_milk int
);