package project.usables;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;

/**
 * This is a class that modifies images that are fed to it.
 * It is primarily used to handle resources that are in image form.
 * 
 * @author Jack Meng
 */
public class DeImage {
  private DeImage() {
  }

  /**
   * Turns an Image read raw from a stream to be enwrapped by a BufferedImage
   * object.
   * 
   * @param image An Image from a stream.
   * @return BufferedImage A modified image that has been convertted and held in a
   *         BufferedImage object.
   */
  public static BufferedImage imagetoBI(Image image) {
    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D big = bi.createGraphics();
    big.drawImage(image, 0, 0, null);
    big.dispose();
    return bi;
  }

  /**
   * Combines a mask between a source image and a mask image.
   * 
   * @param sourceImage The image to be masked.
   * @param maskImage   The image to be used as a mask.
   * @param method      The method to be used to combine the images.
   * @return BufferedImage The combined image.
   */
  public static BufferedImage applyMask(BufferedImage sourceImage, BufferedImage maskImage, int method) {
    BufferedImage maskedImage = null;
    if (sourceImage != null) {
      int width = maskImage.getWidth();
      int height = maskImage.getHeight();
      maskedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D mg = maskedImage.createGraphics();
      int x = (width - sourceImage.getWidth()) / 2;
      int y = (height - sourceImage.getHeight()) / 2;
      mg.drawImage(sourceImage, x, y, null);
      mg.setComposite(AlphaComposite.getInstance(method));
      mg.drawImage(maskImage, 0, 0, null);
      mg.dispose();
    }
    return maskedImage;
  }

  /**
   * Writes a BufferedImage to a file.
   * @param r The BufferedImage to be written.
   * @param path The path to the file to be written.
   */
  public static void write(BufferedImage r, String path) {
    try {
      ImageIO.write(imagetoBI(r), "png", new File(path));
    } catch (IOException e) {
      // print the exception in red text
      System.err.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
    }
  }

  public static BufferedImage imageIconToBI(ImageIcon icon) {
    return imagetoBI(icon.getImage());
  }

  /**
   * Makes a gradient from left to right to an image from opacity to no opacity.
   * 
   * @param img          The source image
   * @param startOpacity The begin opacity of the gradient.
   * @param endOpacity   The end opacity of the gradient.
   * @return BufferedImage The gradient image.
   */
  public static BufferedImage createGradient(BufferedImage img, int startOpacity, int endOpacity) {
    BufferedImage alphamask = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = alphamask.createGraphics();
    LinearGradientPaint lgp = new LinearGradientPaint(
        new Point(0, 0),
        new Point(alphamask.getWidth(), 0),
        new float[] { 0, 1 },
        new Color[] { new Color(0, 0, 0, startOpacity), new Color(0, 0, 0, endOpacity) });
    g2d.setPaint(lgp);
    g2d.fillRect(0, 0, alphamask.getWidth(), alphamask.getHeight());
    g2d.dispose();
    return applyMask(img, alphamask, AlphaComposite.DST_IN);
  }

  /**
   * @param image  An ImageIcon from a stream.
   * @param width  The width to scale down to
   * @param height The height to scale down to
   * @return ImageIcon A modified image that has been scaled to width and height.
   */
  public static ImageIcon resizeImage(ImageIcon image, int width, int height) {
    Image img = image.getImage();
    Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
  }
}
