package bix;
import java.awt.image.BufferedImage;

public class BixelMap {
	private Bixel[][] bixels;
	private BufferedImage original  = null;
	private BufferedImage grayscale = null;
	private BufferedImage pattern   = null;
	private int state = 0;

	public BixelMap() { }
	public BixelMap(BufferedImage img) {
		bixels = new Bixel[img.getHeight()][img.getWidth()];
		for (int i = 0; i < img.getHeight(); i++)
			for (int j = 0; j < img.getWidth(); j++)
				bixels[i][j] = new Bixel(img.getRGB(j, i));
	}

	public BufferedImage original() {
		if (original != null) return original;
		int height = bixels.length * 2;
		int width = bixels[0].length * 2;
		BufferedImage original =
			new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < height/2 ; i++) {
			for (int j = 0; j < width/2 ; j++) {
				int c = bixels[i][j].argb;
				original.setRGB(j * 2	 , i * 2    , c);
				original.setRGB(j * 2	 , i * 2 + 1, c);
				original.setRGB(j * 2 + 1, i * 2    , c);
				original.setRGB(j * 2 + 1, i * 2 + 1, c); }}
		return original;
	}

	public BufferedImage grayscale() {
		if (grayscale != null) return grayscale;
		int height = bixels.length * 2;
		int width = bixels[0].length * 2;
		BufferedImage grayscale =
			new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < height/2 ; i++) {
			for (int j = 0; j < width/2 ; j++) {
				int c = bixels[i][j].middle();
				grayscale.setRGB(j * 2    , i * 2    , c);
				grayscale.setRGB(j * 2 	  , i * 2 + 1, c);
				grayscale.setRGB(j * 2 + 1, i * 2    , c);
				grayscale.setRGB(j * 2 + 1, i * 2 + 1, c); }}
		return grayscale;
	}

	public BufferedImage pattern() {
		if (pattern != null) return pattern;
		int height = bixels.length * 2;
		int width = bixels[0].length * 2;
		BufferedImage pattern =
			new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < height/2 ; i++) {
			for (int j = 0; j < width/2 ; j++) {
				pattern.setRGB(j * 2	, i * 2    , bixels[i][j].pattern(0));
				pattern.setRGB(j * 2	, i * 2 + 1, bixels[i][j].pattern(1));
				pattern.setRGB(j * 2 + 1, i * 2    , bixels[i][j].pattern(2));
				pattern.setRGB(j * 2 + 1, i * 2 + 1, bixels[i][j].pattern(3));
			}
		}
		return pattern;
	}

	public void setState(int state) {
		if (state < 0 || state > 2) return;
		this.state = state;
	}

	public BufferedImage update() {
		switch (state) {
			case 0 : return original();
			case 1 : return grayscale();
			case 2 : return pattern();
			default: return null;
		}
	}
}

class Bixel {
	public int argb;

	public Bixel() {
	        argb = 0;
	}

	public Bixel(int argb) {
		this.argb = argb;
	}

	public int getIndividual(char c) {
		switch (c) {
			case 'a': return argb & 0xFF000000 >> 24; 
			case 'r': return argb & 0x00FF0000 >> 16; 
			case 'g': return argb & 0x0000FF00 >> 8; 
			case 'b': return argb & 0x000000FF; 
			default : return        0x00000000;
		}
	}

   	public int middle() {
   		int mid = (getIndividual('r') + getIndividual('g') + getIndividual('b')) / 3;
   		// return mid + (mid << 8) + (mid << 16) + (getIndividual('a') << 24);
   		return mid + (mid << 8) + (mid << 16) + 0xFF000000;
   	}

//	public int middle() {
//		int mid = (int) (((double) getIndividual('r')) * 0.2989 + 
//		          ((double) getIndividual('g')) * 0.5870 + 
//			  ((double) getIndividual('b')) * 0.1140);
//		return mid + (mid << 8) + (mid << 16) + (getIndividual('a') << 24);
//	} 

	public int pattern(int place) {
		int black = 0xFFFFFFFF;
		int white = 0xFF000000;
		int mid = (middle() & 0x000000FF) / 52;
		switch (mid) {
			case 0: {
				return white;
			}
			case 1: {
				switch (place) {
					case 0: return black;
					default: return white;
				}
			}
			case 2: {
				switch (place) {
					case 0: return black;
					case 2: return black;
					default: return white;
				}
			}
			case 3: {
				switch (place) {
					case 0: return white;
					default: return black;
				}
			}
			case 4: return black;
		}
		return 0;
	}
}
