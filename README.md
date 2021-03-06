## Street view Project

Street View Creator 

![Front](https://github.com/bhishekarora/streetview/blob/master/Street_View_Base/virtualtour/images/streetview.PNG)

## How it was done

I collected the panoramic images of various spots in  my city every 100 meter apart, my friends also helped.
I had a 360 degree bubblescope attached to my phone and i fixed  this with a selfie stick on my car and roamed 
around the city clicking pictures and all the pictures had EXIF data also embedded which contains the lat long of 
every spot where that picture was taken.

Now  pic (EXIF) --> Fed to my custom code will generate the panaromic  xml ==> Salado player in chrome will play it.
Also google map was integrated so that people can click on googlemap and the pano player will show them the 360 view
of the spot.

Lot of work was done and it worked out well.



<img src="https://github.com/bhishekarora/streetview/blob/master/Street_View_Base/virtualtour/images/bubblescope.jpg" width="200" height="300px">

So this tool actual did  splitting,geosync,googleintegration for the street view player.

The codebase is from an old windows laptop so java code is contains window based paths, created this for a hackathon and was 
a **winner** , i am not putting effort now to refactor it because, modern browsers has disabled flash now.


## Prerequisites



Apache2 /var/www/ should be available for testing purpose,in real environment you will push the virtualtour directory in the repo to the target server deployment root.

A Chrome/firefox , flash enabled browser should be available on your system, it used to work on old browsers back in 2015 when chrome allowed external flash objects to run in the dom.

Java 6 should be installed and the jdk path should be available inside path variable ( e.g java -version should show you java version on your system)


## Steps to make it work.

First Read the Screenshots.doc folder in the repo, you need to take panoramic pictures of the spots either via a 360 degree
camera or take 4 different photos and stitch them via Hugin software, its a panoramiz stitcher.


Copy the Street_view_base directory to any of your drive on your windows system.

Embedd the drive value inside the config.properties (e.g open streetview.jar with winzip and update drive and project name inside config.properties)

Now copy all the source images inside the source images folder( images should have time stamps and gps values)

If you don't have location data on your images, you can us gpx master (IOS app ) for collecting wavepoints and use geosetter to map the images with the wavepoints, check the screen shots doc for more info, it will be easy if you capture images with inbuilt EXIF data.

Run the Streetviewtool.bat and let the program do its work.

Once done , copy the virtualtour directory from this folder and move it to your http server root.

Now open the chrome browser and point your browser to http://localhost/virtualtour , you will see the street view of the images you entered in soure images folder.

## Deployment

Deply it to cloud or your server and enjoy the view.



