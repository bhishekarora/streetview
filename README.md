## Sstreetview

Street View tool

Prerequisites

HTTP Server should be installed on your system and you should have access to add new documents to httpserver document directory

A Chrome/firefox , flash enabled browser should be available on your system.

Java 6 should be installed and the jdk path should be available inside path variable ( e.g java -version should show you java version on your system)


Steps to make it work.

Copy the Street_view_base directory to any of your drive on your system.

Embedd the drive value inside the config.properties (e.g open streetview.jar with winzip and update drive and project name inside config.properties)

Now copy all the source images inside the source images folder( images should have time stamps and gps values)

Run the Streetviewtool.bat and let the program do its work.

Once done , copy the virtualtour directory from this folder and move it to your http server root.

Now open the chrome browser and point your browser to http://localhost/virtualtour , you will see the street view of the images you entered in soure images folder.

