@echo off

SET base_dir=%FLASH_CARDS_HOME%

cd %base_dir%

mvn clean package spring-boot:run


