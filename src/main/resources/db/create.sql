CREATE DATABASE news_api;
\c news_api

CREATE TABLE departments (
id SERIAL PRIMARY KEY,
name VARCHAR,
description VARCHAR,
user INT
);

CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR,
details VARCHAR,
role VARCHAR,
position VARCHAR,
);

CREATE TABLE news (
id SERIAL PRIMARY KEY,
name VARCHAR,
content VARCHAR,
dpt_id INTEGER
);

CREATE TABLE departments_users(
id SERIAL PRIMARY KEY,
dpt_id INTEGER,
user_id INTEGER
);

CREATE DATABASE news_api_test WITH TEMPLATE news_api;