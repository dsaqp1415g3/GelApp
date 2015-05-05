drop user 'gelapp'@'localhost';
create user 'gelapp'@'localhost' identified by 'gelapp';
grant all privileges on gelappdb.* to 'gelapp'@'localhost';
flush privileges;
