@echo off
if "%1" == "" goto error
call ant
mkdir ..\s2dao-www\download\%1
copy target\*.zip ..\s2dao-www\download\%1
goto end
:error
echo �������w�肵�Ă��������B
:end
