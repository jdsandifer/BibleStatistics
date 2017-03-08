REM Drag a .java file onto this batch file to
REM compile it and it's test file, and run the test.

@ECHO OFF
ECHO.
ECHO CompileAndTest.bat, v1.0.0 for Windows 7
ECHO Compile the provided file, it's test file, and run test.
ECHO.
ECHO Usage: Drag file onto this batch to run.
ECHO.
ECHO Written by J.D. Sandifer, http://github.com/jdsandifer
ECHO.

SETLOCAL

REM Parse file name to create test file and test names.
SET className=%~n1
SET "fileName=%className%.java"
SET "testFileName=%className%Test.java"
SET "testName=%className%Test"

REM Check names...
SET fileName
SET testFileName
SET testName

@ECHO ON
javac %fileName%
javac -cp .;junit-4.12.jar %testFileName%
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore %testName%
@ECHO OFF

ENDLOCAL

ECHO.
PAUSE