package com.shitao.qrcode.factory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeFactory {
	private final static String QRCODE_PATH = "F:/QRcode/";
	private final static String FILE_SUFFIX = ".png";
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
	
	/*
	 * 生成二维码并返回路径
	 * BitMatrix 生成一个二维位矩阵
	 * x 为列
	 * y 为行
	 * 
	 */
	public String createQRcode(String content,String filename)
	{
		
		MultiFormatWriter write = new MultiFormatWriter();
		BitMatrix matrix = null;
		try 
		{
			Map<EncodeHintType,String> hints = new HashMap<EncodeHintType,String>();
		    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			matrix = write.encode(content, BarcodeFormat.QR_CODE, 300, 300,hints);
			File file = new File(QRCODE_PATH+filename+FILE_SUFFIX);
			writeToFile(matrix, "png", file);
			return file.getPath();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
   private BufferedImage toBufferedImage(BitMatrix matrix) {
	     int width = matrix.getWidth();
	     int height = matrix.getHeight();
	     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	     for (int x = 0; x < width; x++) {
	       for (int y = 0; y < height; y++) {
	         image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
	       }
	     }
	     return image;
   }
   
   private void writeToFile(BitMatrix matrix, String format, File file)
	       throws IOException {
	     BufferedImage image = toBufferedImage(matrix);
	     if (!ImageIO.write(image, format, file)) {
	       throw new IOException("Could not write an image of format " + format + " to " + file);
	     }
	   }
}
