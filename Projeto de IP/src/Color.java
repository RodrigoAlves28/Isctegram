/**
 * Represents RGB colors.
 * RGB values are stored in a 3-position array, with values in the interval [0, 255].
 * rgb[0] - Red
 * rgb[1] - Green
 * rgb[2] - Blue
 */
public class Color {

	private final int[] rgb; // @color

	/**
	 * Creates an RGB color. Provided values have to 
	 * be in the interval [0, 255]
	 */
	Color(int r, int g, int b) {
		if(!valid(r) || !valid(g) || !valid(b))
			throw new IllegalArgumentException("invalid RGB values: " + r + ", " + g + ", " + b);
		
		this.rgb = new int[] {r, g, b};
	}

	/**
	 * Red value [0, 255]
	 */
	int getR() {
		return rgb[0];
	}

	/**
	 * Green value [0, 255]
	 */
	int getG() {
		return rgb[1];
	}

	/**
	 * Blue value [0, 255]
	 */
	int getB() {
		return rgb[2];
	}

	/**
	 * Obtains the luminance in the interval [0, 255].
	 */
	int getLuminance() {
		return (int) Math.round(rgb[0]*.21 + rgb[1]*.71 + rgb[2]*.08);
	}

	Color inverse() {
		return new Color(255-rgb[0],255-rgb[1], 255-rgb[2]); 
	}
	
	
	static boolean valid(int value) {
		return value >= 0 && value <= 255;
	}
	
	Color brighter(int value) {
		return new Color(limite(getR()+value), limite(getG()+value),limite(getB()+value));
	}
	
	int limite(int n) {
		if(n>255)
			return 255;
		if(n<0)
			return 0;
		return n;
	}
	

}