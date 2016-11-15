drop schema public;

create schema data authorization data;
GRANT ALL ON SCHEMA data TO data;
--GRANT ALL ON SCHEMA public TO data;
set search_path to data;
