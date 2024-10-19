alter table coffee_machine
add column order_id int
constraint fk_order_id
references orders(id)
on delete cascade