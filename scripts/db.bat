@echo off

SET base_dir=%FLASH_CARDS_HOME%

cd %base_dir%/db

psql -U postgres -c "create database flash_cards;"
psql -U postgres flash_cards < db.sql


