drop database if exists gelappdb;
create database gelappdb;
 
use gelappdb;

create table usuario (
	usuario_id	int not null auto_increment,	
	username	varchar(20) not null,
	userpass	varchar(32) not null,
	primary key (usuario_id)
);

create table sabor (
	sabor_id		int not null auto_increment,
	creation_timestamp	datetime not null default current_timestamp,
	last_modified		timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
	nombre			varchar(20) not null,
	codigo_color		varchar(20) not null,
	primary key (sabor_id)	
);

create table topping (
	topping_id		int not null auto_increment,
	creation_timestamp	datetime not null default current_timestamp,
	last_modified		timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
	nombre			varchar(20) not null,
	codigo_color		varchar(20) not null,
	primary key (topping_id)	
);

create table helado (
	helado_id			int not null auto_increment primary hey,
	autor_id			int not null,
	foreign key (autor_id) references usuario(usuario_id),
	nombre_helado			varchar(20) not null unique,
	creation_timestamp		datetime not null default current_timestamp,
	last_modified			timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
	capa_1_topping			varchar(20) not null,
	capa_2_helado			varchar(20) not null,
	capa_3_topping			varchar(20) not null,
	capa_4_helado			varchar(20) not null,
	capa_5_topping			varchar(20) not null
);

create table votos (
	id_usuario			int not null,
	id_helado 			int not null,
	foreign key(id_helado) references helado(helado_id) on delete cascade,
	foreign key(id_usuario) references usuario(usuario_id) on delete cascade,
	primary key (id_usuario, id_helado)
);
