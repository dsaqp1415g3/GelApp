source GelAppdb-schema.sql;
insert into usuario (username, userpass) values('marc', MD5('cabezas'));
insert into usuario (username, userpass) values('oriol', MD5('castano'));
insert into usuario (username, userpass) values('ales', MD5('fava'));


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

insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values (1,2,1,2,1);

#FALTA AÑADIR TABLA HELADOS Y LA DE RELACION, TAMBIEN ESCOGER SABORES Y TOPPINGS Y AÑADIRLOS
