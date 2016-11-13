drop schema public;

create schema data authorization data;
GRANT ALL ON SCHEMA data TO data;
set search_path to data;
