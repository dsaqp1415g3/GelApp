drop database if exists gelappdb;
create database gelappdb;
 
use gelappdb;

create table usuario (
	id		int not null primary key,	
	username	varchar(20) not null,
	userpass	varchar(32) not null	
);

create table sabor (
	id		int not null primary key,
	nombre		varchar(20) not null,
	codigo_color	varchar(20) not null	
);

create table topping (
	id		int not null primary key,
	nombre		varchar(20) not null,
	codigo_color	varchar(20) not null	
);

create table helado (
	id		int not null primary key,
	creation_timestamp		datetime not null default current_timestamp,
	last_modified			timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
	capa_1_topping			int not null,
	foreign key(capa_1_topping) references topping(id) on delete cascade,
	capa_2_helado			int not null,
	foreign key(capa_2_helado) references sabor(id) on delete cascade,
	capa_3_topping			int not null,
	foreign key(capa_3_topping) references topping(id) on delete cascade,
	capa_4_helado			int not null,
	foreign key(capa_4_helado) references sabor(id) on delete cascade,
	capa_5_topping			int not null,
	foreign key(capa_5_topping) references topping(id) on delete cascade
);

create table relacion_helado_usuario (
	id_usuario			int not null,
	id_helado 			int not null,
	foreign key(id_helado) references helado(id) on delete cascade,
	foreign key(id_usuario) references usuario(id) on delete cascade,
	primary key (username, rolename)
);

create table votos (
	id_usuario			int not null,
	id_helado 			int not null,
	foreign key(id_helado) references helado(id) on delete cascade,
	foreign key(id_usuario) references usuario(id) on delete cascade,
	primary key (username, rolename)
);
