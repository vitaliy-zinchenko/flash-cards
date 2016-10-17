-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: 0002.xml
-- Ran at: 10/17/16 10:08 PM
-- Against: null@offline:postgresql
-- Liquibase version: 3.5.3
-- *********************************************************************

-- Changeset 0002.xml::1476730766171-1::Vitaliy_Zinchenko (generated)
CREATE TABLE user_provider (id BIGINT NOT NULL, code VARCHAR(255));

-- Changeset 0002.xml::1476730766171-2::Vitaliy_Zinchenko (generated)
ALTER TABLE user_info ADD external_id VARCHAR(255);

-- Changeset 0002.xml::1476730766171-3::Vitaliy_Zinchenko (generated)
ALTER TABLE user_info ADD user_provider_id BIGINT;

-- Changeset 0002.xml::1476730766171-4::Vitaliy_Zinchenko (generated)
ALTER TABLE user_provider ADD CONSTRAINT user_provider_pkey PRIMARY KEY (id);

-- Changeset 0002.xml::1476730766171-5::Vitaliy_Zinchenko (generated)
ALTER TABLE user_info ADD CONSTRAINT fkh5b4i7tm0aker6y05qbqj725t FOREIGN KEY (user_provider_id) REFERENCES user_provider (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

