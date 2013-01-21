osgi-updater
============

Simple configurable bundle, that updates other bundles from different sources, as: http, https, git, etc.

Folders structure:
-----------------------------
* **bin** - contains apache felix jar executable (felix.jar)
* **bundle** - contains all 3rd party bundles (apache felix commnad line, apache commons, Log4j, etc.); they are loaded automatically when felix starts
* **conf** - felix configuration file
* **licence** - apache felix license files
* **my-bundle** - deployment folder for our bundles
* **my-projects** - our Netbeans projects

How to run:
-----------------------------
Start felix by **felix.jar** located in a root folder. When running, folder **felix-cache** is created. Never include this cache folder into git!

How to create a new project:
-----------------------------
All our projects are located in **my-projects** folder. When a project requires 3rd party library, whish is an OSGi
bundle, put this bundle inside **<root>/bundle** folder and reference it from here. Only libraries that are specific
only for one project pu in **<project-name>/lib** folder. 

How to run a new project:
-----------------------------
All what is needed is to run **Clean and Build** in Netbeans IDE. This will automatically create a new bundle,
deploy this bundle inside **my-bundle** folder and start this bundle in felix (ONLY IF Felix IS RUNNING).
Automatic restart of newly deployed bundles is thanks to Apache Felix File Installer bundle.

In a new project, you MUST change **build.xml** file - look at already existing projects.
