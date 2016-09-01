@echo off

SET DOCKER_TLS_VERIFY=1
SET DOCKER_HOST=tcp://104.236.116.198:2376
SET DOCKER_CERT_PATH=C:\Users\Vitaliy_Zinchenko\.docker\machine\machines\docker1
SET DOCKER_MACHINE_NAME=docker1

SET base_dir=%FLASH_CARDS_HOME%

mvn clean package -P build-docker-image

