@echo off

echo 'Setup variables...'

SET DOCKER_TLS_VERIFY=1
SET DOCKER_HOST=tcp://104.236.116.198:2376
SET DOCKER_CERT_PATH=C:\Users\Vitaliy_Zinchenko\.docker\machine\machines\docker1
SET DOCKER_MACHINE_NAME=docker1

echo 'Building...'

call mvn clean package -P build-docker-image

if NOT %errorlevel%==0 (
	echo *************** ERROR ***************
	exit /b
)

pushd docker

echo 'Deploying...'
call docker-compose up -d

popd