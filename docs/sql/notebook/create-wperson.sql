create table w_person(
id bigserial primary key,
idno varchar(20),
name varchar(50),
w_company_id bigint references w_company(id)
);