alter table drinks
add column ingredient_id int
constraint fk_ingredient_id
references ingredients(id)
on delete cascade