osgi-updater
============

Simple configurable bundle, that updates other bundles from different sources, as: http, https, git, etc.

Folders structure:
-----------------------------
* **bin** - Contains Apache Felix jar executable (felix.jar).
* **bundle** - Contains all 3rd party bundles (Apache Felix bundles, Apache Commons bundles, Log4j, etc.) that are loaded automatically when Felix is starting.
* **conf** - Felix configuration file
* **licence** - Apache Felix license files
* **my-bundle** - Deployment folder for our bundles. 
* **my-projects** - Our Netbeans projects.

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
All what is needed is to run **Clean and Build** in Netbeans IDE. This will automatically create new bundle,
deploy this bundle inside **my-bundle** folder and start this bundle in felix, IF Felix IS RUNNING. This automatic
start is thanks to Apache Felix File Installer bundle.

In a new project, you MUST change **build.xml** file - look at already existing projects.
