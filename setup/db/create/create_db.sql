-- Role: db-inline
-- DROP ROLE "db-inline";

CREATE ROLE "db-inline" LOGIN
  ENCRYPTED PASSWORD 'md57d3d336e2dfd3a68023e00c0402e7910'
  NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE;

CREATE DATABASE "db-inline" WITH OWNER = "db-inline" ENCODING = 'UTF-8' CONNECTION LIMIT = -1 TEMPLATE template0;

GRANT ALL ON DATABASE "db-inline" TO "db-inline";
