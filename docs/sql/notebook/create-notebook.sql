create table notebook(
id bigserial primary key,
brand varchar(50),
specification varchar(50),
quotation numeric(10, 0),
w_person_id bigint references w_person(id)
);