/*    */ package virtualtour;
/*    */ 
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Date;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class getProperties {
/*    */   public String getPropValues() throws IOException {
/* 15 */     String result = "";
/* 16 */     Properties prop = new Properties();
/* 17 */     String propFileName = "config.properties";
/* 19 */     InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
/* 21 */     if (inputStream != null) {
/* 22 */       prop.load(inputStream);
/*    */     } else {
/* 24 */       throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
/*    */     } 
/* 27 */     Date time = new Date(System.currentTimeMillis());
/* 30 */     String drive = prop.getProperty("drive");
/* 31 */     String projectname = prop.getProperty("projectname");
/* 33 */     result = "proplist  List = " + drive + ", " + projectname;
/* 34 */     System.out.println(String.valueOf(result) + "\nProject name " + projectname + " drive=" + drive);
/* 35 */     return result;
/*    */   }
/*    */   
/*    */   public String getdrive() throws IOException {
/* 40 */     String result = "";
/* 41 */     Properties prop = new Properties();
/* 42 */     String propFileName = "config.properties";
/* 44 */     InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
/* 46 */     if (inputStream != null) {
/* 47 */       prop.load(inputStream);
/*    */     } else {
/* 49 */       throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
/*    */     } 
/* 52 */     Date time = new Date(System.currentTimeMillis());
/* 55 */     String drive = prop.getProperty("drive");
/* 58 */     return drive;
/*    */   }
/*    */   
/*    */   public String getprojectname() throws IOException {
/* 67 */     String result = "";
/* 68 */     Properties prop = new Properties();
/* 69 */     String propFileName = "config.properties";
/* 71 */     InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
/* 73 */     if (inputStream != null) {
/* 74 */       prop.load(inputStream);
/*    */     } else {
/* 76 */       throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
/*    */     } 
/* 79 */     Date time = new Date(System.currentTimeMillis());
/* 83 */     String projectname = prop.getProperty("projectname");
/* 85 */     return projectname;
/*    */   }
/*    */ }


/* Location:              /home/abhishek/custom/playground/java/streetview/Street_View_Base/streetview.jar!/virtualtour/getProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */