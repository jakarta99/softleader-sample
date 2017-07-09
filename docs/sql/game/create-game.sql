create table game (
    id bigserial,
    name varchar(50),
    type varchar(50),
    p_id bigserial ,
  constraint game_id_pk primary key (id),
  constraint person_p_id_fk foreign key (p_id) references person(p_id)
    )

