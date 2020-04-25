package virtualtour;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;

public class MetaDataReader {
  public String readData(String file) {
    String latlong = "";
    String latitude = "";
    String longitude = "";
    if (file == null) {
      System.out.println("Usage: Test <image-file>");
      System.exit(0);
    } 
    System.out.println("Filename: " + file);
    try {
      File jpgFile = new File(file);
      Metadata metadata = ImageMetadataReader.readMetadata(jpgFile);
      for (Directory directory : metadata.getDirectories()) {
        for (Tag tag : directory.getTags()) {
          if (tag.getTagName().equals("GPS Latitude") || tag.getTagName().equals("GPS Longitude")) {
            latlong = String.valueOf(latlong) + convertHourToDecimal(tag.getDescription()) + ",";
            System.out.println(tag);
          } 
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return latlong;
  }
  
  public double convertHourToDecimal(String lat) {
    String str = lat;
    String[] temp = null;
    String dtemp = null;
    temp = str.split("[\"]|[']|[);
    dtemp = str.replace("\"", ");
    System.out.println("Formated DCM : " + dtemp);
    return dump(temp);
  }
  
  public double dump(String[] s) {
    double decimaldms = 0.0D;
    for (int i = 0; i < s.length; i++) {
      System.out.println("\ndegree : " + s[0]);
      System.out.println("\nminutes : " + s[1]);
      System.out.println("\nsecond : " + s[2]);
      String deg = s[0];
      int ndeg = Integer.parseInt(deg);
      String min = s[1];
      double nmin = Double.parseDouble(min);
      String sec = s[2];
      double nsec = Double.parseDouble(sec);
      decimaldms = ndeg + nmin / 60.0D + nsec / 3600.0D;
      System.out.println("\nfinaldecimal : " + decimaldms);
    } 
    return decimaldms;
  }
}

