drop user 'gelapp'@'localhost';
create user 'gelapp'@'localhost' identified by 'gelapp';
grant all privileges on beeterdb.* to 'gelapp'@'localhost';
flush privileges;
