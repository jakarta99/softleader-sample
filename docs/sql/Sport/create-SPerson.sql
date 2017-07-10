CREATE TABLE sport( 
	id     bigserial,
	name   varchar(100),
	people  varchar(100)
	
    );
    
CREATE TABLE SPerson( 
	id     bigserial,
	name   varchar(100),
	idnum  varchar(100),
	sportid  bigserial
    );