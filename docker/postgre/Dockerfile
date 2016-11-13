FROM postgres:latest

ENV POSTGRES_USER data
ENV POSTGRES_PASSWORD data
ENV POSTGRES_DB postgres

ADD sql/*.sql /docker-entrypoint-initdb.d/