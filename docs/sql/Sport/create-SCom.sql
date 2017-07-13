CREATE TABLE sport( 
	id     bigserial,
	name   varchar(100),
	people  varchar(100),
	personid     bigint
	
    );
    
CREATE TABLE SPerson( 
	id     bigserial,
	name   varchar(100),
	idnum  varchar(100),
	comid bigint
    );


CREATE TABLE SCom( 
	id     bigserial,
	name   varchar(100)
	
    );
