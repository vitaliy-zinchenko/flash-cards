@echo off

SET base_dir=D:\zinchenko\flash-cards

echo Copy files...

rd /S /Q %tomcat_webapps%\ROOT\js
mkdir %tomcat_webapps%\ROOT\js

XCOPY %base_dir%\src\main\resources\static %base_dir%\target\classes\static /s /e /h /Q /y /D


