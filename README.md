# CS370-CutListProject
 
This is a group project for my software engineering class. The goal is to create a program that makes an optimum cut
list for a sheet of plywood. 

- _**Architectural Spike:**_ The part of the project where we try to find an approach to create a program that produces an optimum cut list. This involves research libraries and experimenting with them to see what sticks.
- _**src:**_ The main source code directory for the project. This will be used to build the project.
- _**Cycle1:**_ The code for cycle 1 of the project
- _**Cycle2:**_ The code for cycle 2 of the project.
- _**Cycle3:**_ The code for cycle 3 of the project

## Build Instructions

1. Install Java runtime environment and Java developer kit.
   1. Download the FULL JDK and JRE for your OS here: https://bell-sw.com/pages/downloads/#jdk-8-lts
   2. Click on the drop down menu for your OS and select Full JDK. 
   3. Download the installer.
   4. Run the installers
2. Download and install IntelliJ
   1. Download Gradle from https://www.jetbrains.com/idea/download/
   2. Scroll down until you see the Community Edition download
   3. Press download
   4. Run the installer and follow the instructions
      1. For Linux, extract the tarball in /opt
3. Open the project
   1. Open IntelliJ
   2. Click on open
   3. Select the cloned repo from the file browser and click open
   4. Wait for IntelliJ to index JDK files, download libraries, and generate Gradle files
4. Build the project
   1. Click on the gear in the top right corner of the IntelliJ window
   2. Click on project structure
   3. Click on artifacts on the left side of the setting window
   4. Click on the plus on the top of the window, then hover over JavaFX application, then click on "From Module..."
   5. Scroll down until you see CS370-CutListProject.Cycle3.main, click on okay
   6. Click on the plus inside the output layout section of the artifact page
   7. Click on directory contents, then select /path/to/repo/Cycle3/main/resources. This will include the fxml file for gui layout.
   8. Click on the JavaFX tab
   9. Click on the folder icon next to Application Class. Select "Main (cs370.cutlist_project.Cycle3".
   10. Scroll down until you see "Native Bundle"
   11. Click on the drop-down and select "all"
   12. Click on okay on the ok button on the bottom right corner of the window
   13. On the top of the IDE window, click on the build menu, then click on build artifacts
   14. Click on the name of the artifact that you made
   15. The application is now built in /path/to/repo/out/artifacts/name-of-artifact/