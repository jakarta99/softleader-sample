create table person(
    pId   bigserial  not null,
    pName varchar(50),
    pIdno  varchar(50),
    constraint person_primary_key primary key (pId)
    )
