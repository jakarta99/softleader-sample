create table cperson
(id bigserial not null, 
name varchar(20), 
idno varchar(10) not null
PRIMARY KEY (id,idno)
);

create table country
(
countryid bigint not null,
name varchar(20),
size varchar(10),
personid bigint not null
);