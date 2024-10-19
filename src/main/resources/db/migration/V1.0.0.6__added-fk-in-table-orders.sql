alter table orders
add column drink_id int
constraint fk_drink_id
references drinks(id)
on delete cascade