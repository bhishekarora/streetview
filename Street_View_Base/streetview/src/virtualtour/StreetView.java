package virtualtour;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

public class StreetView {
  public static void main(String[] args) throws IOException {
    getProperties properties = new getProperties();
    String drive = properties.getdrive();
    String projectname = properties.getprojectname();
    String BASE = String.valueOf(drive) + "\\Street_View_Base";
    File projectNameDirectory = new File(String.valueOf(BASE) + "\\virtualtour\\panoramas\\" + projectname);
    boolean projectdirectoryCreationResult = projectNameDirectory.mkdir();
    projectNameDirectory.setWritable(true);
    System.out.println("================Project directory created ===========");
    String source_images = String.valueOf(drive) + "\\Street_View_Base\\source_images";
    String ImageName = "";
    StringBuffer indexFile = new StringBuffer("<?xml version='1.0' encoding='utf-8'?><!--\t--><SaladoPlayer>\t<global debug='false'/>\t<panoramas>");
    StringBuffer waypointBuffer = new StringBuffer();
    File dir = new File(
        
        source_images);
    byte b;
    int i;
    String[] arrayOfString;
    for (i = (arrayOfString = dir.list()).length, b = 0; b < i; ) {
      String fn = arrayOfString[b];
      File file = new File(String.valueOf(source_images) + "/" + fn);
      MetaDataReader reader = new MetaDataReader();
      ImageName = reader.readData(file.getAbsolutePath());
      ImageName = ImageName.trim();
      ImageName = ImageName.replaceAll("\\s", "");
      ImageName = ImageName.replace("\"", ",");
      ImageName = ImageName.substring(0, ImageName.length() - 1);
      FileInputStream fis = new FileInputStream(file);
      BufferedImage image = ImageIO.read(fis);
      int rows = 1;
      int cols = 4;
      int chunks = rows * cols;
      String suffix = "";
      int chunkWidth = image.getWidth() / cols;
      int chunkHeight = image.getHeight() / rows;
      int count = 0;
      BufferedImage[] imgs = new BufferedImage[chunks];
      for (int x = 0; x < rows; x++) {
        for (int y = 0; y < cols; y++) {
          imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
          Graphics2D gr = imgs[count++].createGraphics();
          gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
          gr.dispose();
        } 
      } 
      System.out.println("Splitting done");
      System.out.println(count);
      try {
        File imageDirectory = new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName);
        boolean directoryCreationResult = imageDirectory.mkdir();
        imageDirectory.setWritable(true);
        System.out.print("Creating main directory for panorama " + directoryCreationResult);
      } catch (Exception e) {
        e.printStackTrace();
      } 
      for (int j = 0; j < imgs.length; j++) {
        switch (j) {
          case 0:
            suffix = "f";
            break;
          case 1:
            suffix = "r";
            break;
          case 2:
            suffix = "b";
            break;
          case 3:
            suffix = "l";
            break;
        } 
        try {
          File imageDirectory = new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName + "\\" + ImageName + "_" + suffix);
          imageDirectory.setWritable(true);
          File imageDirectory9 = new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName + "\\" + ImageName + "_" + suffix + "\\9");
          imageDirectory9.setWritable(true);
          boolean directoryCreationResult = imageDirectory.mkdir();
          boolean directoryCreationResult9 = imageDirectory9.mkdir();
          imageDirectory.setWritable(true);
          System.out.print("\ncreating subdirectory for each panorama " + directoryCreationResult);
          System.out.print("\ncreating subdirectory for each sub panorama  " + directoryCreationResult9);
        } catch (Exception e) {
          e.printStackTrace();
        } 
        ImageIO.write(imgs[j], "jpg", new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName, String.valueOf(ImageName) + "_" + suffix + "\\9\\0_0.jpg"));
        try {
          File upDirectory = new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName + "\\" + ImageName + "_u");
          upDirectory.setWritable(true);
          File upDirectory9 = new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName + "\\" + ImageName + "_u\\9");
          upDirectory9.setWritable(true);
          boolean updirectoryCreationResult = upDirectory.mkdir();
          boolean updirectoryCreationResult9 = upDirectory9.mkdir();
          upDirectory.setWritable(true);
          System.out.print("\ncreating up directory " + updirectoryCreationResult);
          System.out.print("\ncreating subdirectory for up direcotory  " + updirectoryCreationResult9);
        } catch (Exception e) {
          e.printStackTrace();
        } 
        try {
          File downDirectory = new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName + "\\" + ImageName + "_d");
          downDirectory.setWritable(true);
          File downDirectory9 = new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName + "\\" + ImageName + "_d\\9");
          downDirectory9.setWritable(true);
          boolean downdirectoryCreationResult = downDirectory.mkdir();
          boolean downdirectoryCreationResult9 = downDirectory9.mkdir();
          downDirectory.setWritable(true);
          System.out.print("\ncreating up directory " + downdirectoryCreationResult);
          System.out.print("\ncreating subdirectory for up direcotory  " + downdirectoryCreationResult9);
        } catch (Exception e) {
          e.printStackTrace();
        } 
        ImageIO.write(imgs[j], "jpg", new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName, String.valueOf(ImageName) + "_u\\9\\0_0.jpg"));
        ImageIO.write(imgs[j], "jpg", new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName, String.valueOf(ImageName) + "_d\\9\\0_0.jpg"));
      } 
      File file1 = new File(String.valueOf(BASE) + "\\template.xml");
      File file2 = new File(String.valueOf(BASE) + "\\processed_images\\" + ImageName + "\\" + ImageName + "_f.xml");
      copyFileUsingStream(file1, file2);
      indexFile.append("<panorama id='" + ImageName + "' path='/virtualtour/panoramas/" + projectname + "/" + ImageName + "\\" + ImageName + "_f.xml" + "'camera='minVerticalFov:-35,maxVerticalFov:35' direction='55'/>");
      System.out.println("\n------Inserting panorama  at " + ImageName);
      waypointBuffer.append("<waypoint target='" + ImageName + "'" + getGeolocation(ImageName));
      System.out.println("\n------Inserting  waypoint to google maps");
      System.out.println("\nMini images created for the Main Pano");
      b++;
    } 
    indexFile.append("</panoramas><modules><ButtonBar path='/virtualtour/modules/ButtonBar-1.2.swf'>\t\t\t<buttons path='/virtualtour/modules/images/buttonbar/buttons_30x30_black.png'>\t\t\t\t<extraButton name='b' action='jsgooglemapToggle'/>\t\t\t</buttons>\t\t</ButtonBar>\t\t<JSGoogleMap path='/virtualtour/modules/JSGoogleMap-1.0.swf'>\t\t\t<settings open='true' onOpen='jsgooglemapOnOpen' onClose='jsgooglemapOnClose'/><waypoints markerOn='/virtualtour/modules/images/jsgooglemap/marker_on.png' markerOff='/virtualtour/modules/images/jsgooglemap/marker_off.png'>");
    indexFile.append(waypointBuffer);
    indexFile.append("</waypoints>\t</JSGoogleMap>\t</modules><actions>\t\t<action id='jsgooglemapToggle' content='JSGoogleMap.toggleOpen()' />\t\t<action id='jsgooglemapOnOpen' content='ButtonBar.setActive(b,true)' />\t\t<action id='jsgooglemapOnClose' content='ButtonBar.setActive(b,false)' />\t\t\t</actions></SaladoPlayer>");
    BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(String.valueOf(BASE) + "\\index.xml")));
    bwr.write(indexFile.toString());
    bwr.flush();
    bwr.close();
    System.out.println("\nMain Index file for the street view created ");
    File source = new File(String.valueOf(BASE) + "\\index.xml");
    File destination = new File(String.valueOf(BASE) + "\\virtualtour\\index.xml");
    copyFileUsingStream(source, destination);
    System.out.println("\nMain Index file for the street view moved to virtual tour ");
    File sourcedirectory = new File(String.valueOf(BASE) + "\\processed_images\\");
    File destdirectory = new File(String.valueOf(BASE) + "\\virtualtour\\panoramas\\" + projectname + "\\");
    copyDirectory(sourcedirectory, destdirectory);
  }
  
  private static String getGeolocation(String imageName) {
    String[] s = imageName.split(",");
    String geolocation = "geolocation='latitude:" + s[0] + ",longitude:" + s[1] + "'/>";
    return geolocation;
  }
  
  private static void copyFileUsingStream(File source, File dest) throws IOException {
    InputStream is = null;
    OutputStream os = null;
    try {
      is = new FileInputStream(source);
      os = new FileOutputStream(dest);
      byte[] buffer = new byte[1024];
      int length;
      while ((length = is.read(buffer)) > 0)
        os.write(buffer, 0, length); 
    } finally {
      is.close();
      os.close();
    } 
  }
  
  public static void copyFile(File source, File dest) throws IOException {
    if (!dest.exists())
      dest.createNewFile(); 
    InputStream in = null;
    OutputStream out = null;
    try {
      in = new FileInputStream(source);
      out = new FileOutputStream(dest);
      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0)
        out.write(buf, 0, len); 
    } finally {
      in.close();
      out.close();
    } 
  }
  
  public static void copyDirectory(File sourceDir, File destDir) throws IOException {
    if (!destDir.exists())
      destDir.mkdir(); 
    File[] children = sourceDir.listFiles();
    byte b;
    int i;
    File[] arrayOfFile1;
    for (i = (arrayOfFile1 = children).length, b = 0; b < i; ) {
      File sourceChild = arrayOfFile1[b];
      String name = sourceChild.getName();
      File destChild = new File(destDir, name);
      if (sourceChild.isDirectory()) {
        copyDirectory(sourceChild, destChild);
      } else {
        copyFile(sourceChild, destChild);
      } 
      b++;
    } 
  }
}

