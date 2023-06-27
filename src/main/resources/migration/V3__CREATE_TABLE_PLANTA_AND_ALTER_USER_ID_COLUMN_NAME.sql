create table IF NOT EXISTS plantas (

planta_id bigint primary key not null,
user_id long not null,
nome varchar(255) not null,
nome_cientifico varchar(255),
tipo_de_planta varchar(255) not null,
imagem_referencia varchar(255),
descricao varchar(max)

);

ALTER TABLE jardim_user
RENAME COLUMN id TO user_id;