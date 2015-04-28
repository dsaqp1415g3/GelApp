source GelAppdb-schema.sql;
insert into usuario (username, userpass) values('marc', MD5('cabezas'));
insert into usuario (username, userpass) values('oriol', MD5('castano'));
insert into usuario (username, userpass) values('ales', MD5('fava'));


insert into sabor (nombre, codigo_color) values('fresa', '#FA58D0');

insert into topping (nombre, codigo_color) values('caramelo', '#FF8000');


FALTA AÑADIR TABLA HELADOS Y LA DE RELACION, TAMBIEN ESCOGER SABORES Y TOPPINGS Y AÑADIRLOS
