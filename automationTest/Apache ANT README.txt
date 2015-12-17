Summary: The ant file is used to manage compile source files, as well the test cases located in test/testcases and running them. 
It also will be able to clean the generated files. In order to make sure the latest generated files are being used. It also 
generates and deletes JavaDocs. Use the ant file to run the test cases. 

Note: DO NOT run the ant file in a different directory, it will be unable to find browser driver files, which are essential 
for the testcases to be run.
Ex: ant file and source file located in C:\eclipse\automationTest
    command terminal current directory: C:\user\testUser

    Running the ant file from the current directory in the command terminal will cause it to fail.

To run the ant file properly make sure the command terminal current directory is the same as where the ant and source files are located
Ex: ant and source file location: C:\eclipse\automationTest
    command terminal current directory: C:\eclipse\automationTest


-Ant file commands: 
1) Deletes the bin folder, copies source files to bin folder, compile the files, run the tests
ant -f <filename> run

2) Deletes the bin folder, copies source files to bin folder, compile the files, run the tests. Stops execution when an assertion as failed, or unable to find web element/s
ant -f <filename> run -DexitOnFail=true

3) Deletes the bin folder, deletes the doc/Javadocs folder
ant -f <filename> clean

4) Generates Javadocs for the source files.
ant -f <filename> javadocs


-To compile source files and run the tests, use the following steps.
Open command terminal.
Change directory to where the ant file is located.
Type the following: 
ant -f <ant filename> run

-To compile source files and run the tests and stop running the tests when the assertion fails, or unable to find web elements, use the following steps: 
Open command terminal
Change directory to where the ant file is located.
Type the following: 
ant -f <ant filename> run -DexitOnFail=true

-To clean compile folders and generated JavaDocs, use the following commands
Open command terminal
Change directory to where the ant file is located
Type the following: 
ant -f <ant filename> clean

-To generate Javadocs, use the following commands
Open command terminal
Change directory to where the ant file is located
Type the following: 
ant -f <ant filename> javadocs