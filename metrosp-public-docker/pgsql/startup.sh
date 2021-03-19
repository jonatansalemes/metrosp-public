#!/bin/bash
pg_ctlcluster 12 main start
psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "postgres" <<-EOSQL

    CREATE USER bot WITH PASSWORD 'bot';
	CREATE DATABASE bot;
	
	\connect bot
	CREATE SCHEMA bot AUTHORIZATION bot;
	
EOSQL
