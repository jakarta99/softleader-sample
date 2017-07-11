create table person(
    id bigserial,
    pname varchar(50),
    ptype varchar(50),
    pid bigserial ,
  constraint game_id_pk primary key (id),
  constraint person_p_id_fk foreign key (p_id) references person(p_id)
    )

