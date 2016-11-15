FROM postgres:latest

ENV POSTGRES_USER data
ENV POSTGRES_PASSWORD data
ENV POSTGRES_DB DataDB

ADD sql/*.sql /docker-entrypoint-initdb.d/