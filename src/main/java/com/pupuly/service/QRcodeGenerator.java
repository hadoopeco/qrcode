package com.pupuly.service;

import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/8/11
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface QRcodeGenerator {
    public  void writeToFile(BitMatrix matrix, String format, File file)
          throws IOException;
    public  void writeToStream(BitMatrix matrix, String format, OutputStream stream)
          throws IOException;
}
