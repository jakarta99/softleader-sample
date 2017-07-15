create table Fcompany( 
 id  bigserial,
 name varchar(100),
 UNo varchar(50)
);

create table Fman( 
 id  bigserial,
 name varchar(100),
 idNo varchar(50),
 cid bigint
);

CREATE TABLE fruit( 
    id	   bigserial,
	name   varchar(100),
	color  varchar(50),
	pid  bigint
    )