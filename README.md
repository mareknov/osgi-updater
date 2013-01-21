osgi-updater
============

Simple configurable bundle, that updates other bundles from different sources, as: http, https, git, etc.

Folders structure:
-----------------------------
* **bin** - contains apache felix jar executable (felix.jar)
* **bundle** - contains all 3rd party bundles (apache felix commnad line, apache commons, log4j, etc.); they are loaded automatically when felix starts
* **conf** - felix configuration file
* **licence** - apache felix license files
* **my-bundle** - deployment folder for our bundles
* **my-projects** - our Netbeans projects

How to run:
-----------------------------
Start felix by **felix.jar** located in a root folder. When running, folder **felix-cache** is created. Never include this cache folder into git!

How to create a new project:
-----------------------------
All our projects are located in **my-projects** folder. When a project requires a 3rd party library, which is an OSGi
bundle, put this bundle inside **bundle** folder and reference it from there. Only libraries that are specific
for one particular project and will not be used anywhere else, put in **lib** folder under that specific project
(there is no **lib** folder under root) .

How to run a new project:
-----------------------------
All what is needed is to run **Clean and Build** in Netbeans IDE. This will automatically create a new bundle,
deploy this bundle inside **my-bundle** folder and start this bundle in felix (ONLY IF FELIX IS RUNNING).
Automatic restart of newly deployed bundles is managed by Apache Felix File Installer bundle.

In a new project, you MUST change **build.xml** file to support this automatic deployment behaviour - look at already existing projects.
