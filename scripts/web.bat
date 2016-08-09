@echo off

SET base_dir=%FLASH_CARDS_HOME%

echo Copy files...

XCOPY %base_dir%\src\main\resources\static %base_dir%\target\classes\static /s /e /h /Q /y /D


