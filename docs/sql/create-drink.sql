drop table drink;
create table drink (
	id	   bigserial,
	p_id   bigint,
	name   varchar(100),
	color  varchar(50)
);

drop table d_person;
create table d_person (
	id bigserial,
	name varchar(100),
	idno varchar(20)
)

