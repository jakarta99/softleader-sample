CREATE TABLE mperson( 
    id	   bigserial not null primary key,
	name   varchar(20),
	idno  varchar(10) not null,
	moviename1 varchar(50),
	moviename2 varchar(50),
	moviename3 varchar(50),
	);

CREATE TABLE movie(
	id	bigserial not null primary key,
	name varchar(50),
	price varchar(10),
)
	
	
