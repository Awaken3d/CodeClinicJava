package com.souris.codeClinic.pictureCompare;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class runner {
	static BufferedImage img, img1;
	static boolean answer = false;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		img = ImageIO.read(new File("78771293.jpg"));
		img1 = ImageIO.read(new File("78771293a.jpg"));
		int tester = img1.getRGB(100, 0);
		
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				
				if (img.getRGB(i, j) == tester) {
					System.out.println(" found one");
					break;
					
				}
			}
		}
		System.out.println(answer);
		System.out.println("tester value "+tester);
	}

	public static boolean comparePics(int x, int y, int width, int height) {
		
		int imgWidth = 0, imgHeight = 0;
		for (int i = x; i < width; i++) {
			for (int j = y; y < height; j++) {
				if(img.getRGB(i, j) != img1.getRGB(imgWidth, imgHeight)){
					return false;
				}
				imgHeight++;
			}
			imgWidth++;
		}
		return true;
	}

}
