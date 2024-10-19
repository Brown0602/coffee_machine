create table drink_ingredients(
    id int primary key generated always as identity,
    drink_id int constraint fk_drink_id references drinks(id) on delete cascade,
    ingredient_id int constraint fk_ingredient_id references ingredients(id) on delete cascade,
    amount decimal not null
);