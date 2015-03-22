create database inline;
create user inline@localhost identified by 'inline';
grant usage on *.* to inline@localhost identified by 'inline';
grant all privileges on inline.* to inline@localhost;

ALTER SCHEMA inline  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_bin;
