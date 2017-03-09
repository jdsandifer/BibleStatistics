@ECHO OFF

REM Drag a .java file onto this batch file to
REM compile it and it's test file, and run the test.

SETLOCAL

REM Parse file name to create test file and test names.
SET className=%~n1
SET "fileName=%className%.java"
SET "testFileName=%className%Test.java"
SET "testName=%className%Test"

@ECHO ON
javac %fileName%
javac -cp .;junit-4.12.jar;hamcrest-core-1.3.jar %testFileName%
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore %testName%
@ECHO OFF

ENDLOCAL

REM Keep cmd window open... (is there a better way?)
PAUSE