package com.pupuly.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.pupuly.service.QRcodeGenerator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * User: wbmark
 * Author: marks@126.com
 * Date: Jul 31, 2011
 * Time: 11:53:13 AM
 */
public class QRcodeGeneratorImpl implements QRcodeGenerator {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
          for (int y = 0; y < height; y++) {
            image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
          }
        }
        return image;
  }

    public  void writeToFile(BitMatrix matrix, String format, File file)
          throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ImageIO.write(image, format, file);
    }

  /**
   * Writes a {@link BitMatrix} to a stream.
   *
   * @see #toBufferedImage(BitMatrix)
   */
  public  void writeToStream(BitMatrix matrix, String format, OutputStream stream)
          throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ImageIO.write(image, format, stream);
  }

//    public  static  void main(String arg[]){
//        BitMatrix bitMatrix;
//        String str = "com form lin";
//        try{
//            bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE,200,200);
//            File file = new File("E:/qrcode/test.png");
//            writeToFile(bitMatrix,"png",file);
//        } catch (WriterException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//    }
//
}
