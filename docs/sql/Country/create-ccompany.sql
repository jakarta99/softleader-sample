create table ccompany(
id bigserial not null,
name varchar(20) unique,
PRIMARY KEY (id)
);

create table cperson(
id bigserial not null,
name varchar(20),
idno varchar(10) not null unique,
c_id bigint not null REFERENCES ccompany(id),
PRIMARY KEY(id)
);

create table country(
id bigserial not null,
name varchar(20),
size varchar(10),
p_id bigint not null REFERENCES cperson(id)
)

