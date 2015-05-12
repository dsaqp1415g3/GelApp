drop database if exists gelappdb;
create database gelappdb;
 
use gelappdb;

create table usuario (
	usuario_id	int not null auto_increment primary key,	
	username	varchar(20) not null,
	userpass	varchar(32) not null	
);

create table sabor (
	sabor_id	int not null auto_increment,
	nombre		varchar(20) not null,
	codigo_color	varchar(20) not null,
	primary key (sabor_id, nombre)	
);

create table topping (
	topping_id	int not null auto_increment,
	nombre		varchar(20) not null,
	codigo_color	varchar(20) not null,
	primary key (topping_id, nombre)	
);

create table helado (
	helado_id			int not null auto_increment,
	creation_timestamp		datetime not null default current_timestamp,
	last_modified			timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
	capa_1_topping			varchar(20) not null,
	foreign key(capa_1_topping) references topping(nombre) on delete cascade,
	capa_2_helado			varchar(20) not null,
	foreign key(capa_2_helado) references sabor(nombre) on delete cascade,
	capa_3_topping			varchar(20) not null,
	foreign key(capa_3_topping) references topping(nombre) on delete cascade,
	capa_4_helado			varchar(20) not null,
	foreign key(capa_4_helado) references sabor(nombre) on delete cascade,
	capa_5_topping			varchar(20) not null,
	foreign key(capa_5_topping) references topping(nombre) on delete cascade,
	primary key (capa_1_topping, capa_2_helado, capa_3_topping, capa_4_helado, capa_5_topping, helado_id)
);

create table relacion_helado_usuario (
	id_usuario			int not null,
	id_helado 			int not null,
	foreign key(id_helado) references helado(helado_id) on delete cascade,
	foreign key(id_usuario) references usuario(usuario_id) on delete cascade,
	primary key (id_usuario, id_helado)
);

create table votos (
	id_usuario			int not null,
	id_helado 			int not null,
	foreign key(id_helado) references helado(helado_id) on delete cascade,
	foreign key(id_usuario) references usuario(usuario_id) on delete cascade,
	primary key (id_usuario, id_helado)
);
