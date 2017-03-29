# User schema

# --- !Ups

create table password_info (
    provider_id character varying NOT NULL,
    provider_key character varying NOT NULL,
    hasher character varying NOT NULL,
    password character varying NOT NULL,
    salt character varying,
    CONSTRAINT pk PRIMARY KEY (provider_id, provider_key)
);