	/**
	 * Represents color images.
	 * Image data is represented as a matrix:
	 * - the number of lines corresponds to the image height (data.length)
	 * - the length of the lines corresponds to the image width (data[*].length)
	 * - pixel color is encoded as integers (ARGB)
	 */
	public	class ColorImage {

		private int[][] data; // @colorimage

		ColorImage(String file) {
			this.data = ImageUtil.readColorImage(file);
		}

		ColorImage(int width, int height) {
			data = new int[height][width];
		}

		int getWidth() {
			return data[0].length;
		}

		int getHeight() {
			return data.length;
		}

		void setColor(int x, int y, Color c) {
			data[y][x] = ImageUtil.encodeRgb(c.getR(), c.getG(), c.getB());
		}

		Color getColor(int x, int y) {
			int[] rgb = ImageUtil.decodeRgb(data[y][x]);
			return new Color(rgb[0], rgb[1], rgb[2]);
		}
		
		ColorImage copy() {
			 ColorImage v = new ColorImage(getWidth(),getHeight());
			 for(int w=0; w<getWidth(); w++) {
					for(int h=0; h<getHeight();h++)
						v.setColor(w,h,getColor(w,h));
		
		
			 }
			 return v;

		}
		
		
	public	void paste(ColorImage ci, int x, int y) {
			for(int w=0; w<ci.getWidth(); w++)
				for(int h=0; h<ci.getHeight();h++)
					setColor(x+w,y+h,ci.getColor(w,h));
		}
		
		static void teste() {
			ColorImage f = new ColorImage("simp.jpg");
			ColorImage t = new ColorImage("sonic.png");
			f.paste(t,t.getWidth(),t.getHeight());
		}
	}