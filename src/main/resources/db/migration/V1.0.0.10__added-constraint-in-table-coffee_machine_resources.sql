alter table coffee_machine_resources
add constraint resource_fk
foreign key(resource_id) references resources(id)