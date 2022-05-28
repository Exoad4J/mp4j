package project.connection.resource;

import java.io.File;
import java.io.FileWriter;

import java.awt.image.BufferedImage;
import project.constants.ProjectManager;
import project.usables.DeImage;
import strict.RuntimeConstant;

public class ResourceFolder {
  private ResourceFolder() {
  }

  public static final PropertiesManager pm = new PropertiesManager(ProjectManager.getDefaultPropertiesCustomization(),
      ProjectManager.getAllowedPropertiesCustomization(), ProjectManager.EXT_RSC_FOLDER + "/mp4j.properties");
  static {
    pm.open();
  }

  private static String getRandomString(int length) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append((char) (Math.random() * 26 + 'a'));
    }
    return sb.toString();
  }

  public static void checkResourceFolder() {
    File folder = new File(ProjectManager.EXT_RSC_FOLDER);
    if (!folder.isDirectory()) {
      if (!folder.mkdir()) {
        System.out.println("LOG > Resource folder creation failed.");
      } else {
        System.out.println("LOG > Resource folder created.");
      }
    }
  }

  public static String writeBufferedImageCacheFile(BufferedImage bi) {
    DeImage.write(bi, ProjectManager.EXT_RSC_FOLDER + RuntimeConstant.FILE_SLASH + getRandomString(10) + ".png");
    return ProjectManager.EXT_RSC_FOLDER + RuntimeConstant.FILE_SLASH + getRandomString(10) + ".png";
  }
}
