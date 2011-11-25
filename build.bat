@ECHO OFF

set GITDIR=%~dp0
set MCPDIR=%GITDIR%\..

CALL :APPLYPATCH
CALL :COPYCLIENT

CALL :COMPILE
CALL :COPYTEXTURES

pause

GOTO :EOF

REM ---------------------------------------------------------------------------

:APPLYPATCH
GOTO :EOF

REM ---------------------------------------------------------------------------

:COMPILE
	set OLDCD=%CD%
	cd %MCPDIR%
	cmd /C recompile.bat
	cd %OLDCD%
	set OLDCD=
GOTO :EOF

REM ---------------------------------------------------------------------------

:COPYCLIENT
	xcopy /Y /E %GITDIR%\src\*.java %MCPDIR%\src\minecraft\net\minecraft\src
GOTO :EOF

REM ---------------------------------------------------------------------------

:COPYSERVER
GOTO :EOF

REM ---------------------------------------------------------------------------

:COPYTEXTURES
GOTO :EOF
