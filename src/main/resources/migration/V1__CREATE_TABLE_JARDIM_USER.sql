create table IF NOT EXISTS jardim_user (

id bigint not null,
primeiro_nome varchar(255) not null,
ultimo_nome varchar(255),
username varchar(25) not null,
email varchar(100) not null,
senha varchar(255) not null,
is_admin boolean not null

primary key (id)

);