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

insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('caramelo','nata','caramelo','nata','caramelo');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('nata','turron','sirope_fresa','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','nata','caramelo','chocolate_negro','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','turron','caramelo','caramelo','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('sirope_fresa','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','sirope_fresa','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('sirope_fresa','turron','sirope_fresa','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','sirope_fresa','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','turron','sirope_fresa','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','sirope_fresa','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('sirope_fresa','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','sirope_fresa','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('turron','turron','sirope_fresa','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('sirope_fresa','sirope_fresa','sirope_fresa','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('sirope_fresa','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','sirope_fresa','sirope_fresa','sirope_fresa','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('chocolate_negro','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('caramelo','turron','caramelo','nata','sirope_fresa');
insert into helado (capa_1_topping,capa_2_helado,capa_3_topping,capa_4_helado,capa_5_topping) values ('caramelo','caramelo','caramelo','nata','caramelo');



