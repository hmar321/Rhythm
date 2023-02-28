DROP DATABASE if EXISTS db_rhythm;
create database db_rhythm;
DROP USER if EXISTS 'springuser'@'%';
create user 'springuser'@'%' identified by 'ThePassword';
grant all on db_rhythm.* to 'springuser'@'%';