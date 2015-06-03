source GelApp-schema.sql;
insert into usuario (username, userpass) values('marc', MD5('cabezas'));
insert into user_roles values (1, 'marc', 'registered');
insert into usuario (username, userpass) values('oriol', MD5('castano'));
insert into user_roles values (2,'oriol','registered');
insert into usuario (username, userpass) values('ales', MD5('fava'));
insert into user_roles values (3, 'ales','registered');

insert into sabor (nombre, codigo_color) values('fresa', '#FE2E64');
insert into sabor (nombre, codigo_color) values('nata', '#F2F2F2');
insert into sabor (nombre, codigo_color) values('vainilla', '#F3F781');
insert into sabor (nombre, codigo_color) values('chocolate', '#3B0B0B');
insert into sabor (nombre, codigo_color) values('turron', '#8A4B08');

insert into topping (nombre, codigo_color) values('caramelo', '#F7BE81');
insert into topping (nombre, codigo_color) values('chocolate_negro', '#2A0A0A');
insert into topping (nombre, codigo_color) values('chocolate_blanco', '#F6D8CE');
insert into topping (nombre, codigo_color) values('sirope_fresa', '#FF0040');
insert into topping (nombre, codigo_color) values('multicolor', '#00FF00');

insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (1,'MarceDelicious','caramelo','nata','caramelo','nata','caramelo');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (2,'DeliciousMax','chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (2,'nata','nata','turron','sirope_fresa','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (2,'chocolate','chocolate_negro','nata','caramelo','chocolate_negro','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (2,'deliciaCarnica','chocolate_negro','turron','caramelo','caramelo','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (2,'freson','sirope_fresa','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (3,'delicia de sirope','chocolate_negro','sirope_fresa','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (2,'UriMaximo','sirope_fresa','turron','sirope_fresa','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (2,'McUri','chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (3,'Chocolate y fresa','chocolate_negro','sirope_fresa','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (2,'CarameloyTurron','chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (1,'ChocolateyNata','chocolate_negro','turron','sirope_fresa','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (1,'CarameloyNata','chocolate_negro','sirope_fresa','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (1,'McMarc','sirope_fresa','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (1,'deliciadeMarc','chocolate_negro','sirope_fresa','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (1,'TuronTurron','turron','turron','sirope_fresa','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (1,'ChocolateyTurron','chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (1,'MarcusdeFresa','sirope_fresa','sirope_fresa','sirope_fresa','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (3,'TurronyCaramelo','chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (3,'SiropeyTurron','sirope_fresa','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (3,'tatata','chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (3,'gelapppppp','chocolate_negro','sirope_fresa','sirope_fresa','sirope_fresa','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (3,'heladoooooo','chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (3,'heladoconSirope','caramelo','turron','caramelo','nata','sirope_fresa');
insert into helado (autor_id, nombre_helado,capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (3,'heladerooooooo','caramelo','caramelo','caramelo','nata','caramelo');


insert into votos (id_usuario, id_helado) values (1, 1);
insert into votos (id_usuario, id_helado) values (1, 2);
insert into votos (id_usuario, id_helado) values (1, 3);
insert into votos (id_usuario, id_helado) values (2, 2);
insert into votos (id_usuario, id_helado) values (2, 4);
insert into votos (id_usuario, id_helado) values (2, 5);
insert into votos (id_usuario, id_helado) values (3, 2);
insert into votos (id_usuario, id_helado) values (3, 4);
insert into votos (id_usuario, id_helado) values (3, 7);

