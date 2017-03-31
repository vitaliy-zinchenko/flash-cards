# User schema

# --- !Ups

ALTER TABLE card ADD level smallint NOT NULL;
ALTER TABLE card ADD guess_date date NOT NULL;
ALTER TABLE card ADD train_date date NOT NULL;