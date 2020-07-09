public class History {
	
	
	static final int size = 10;
	

	private ColorImage[] img;
	
	private int pointer = -1;
	 
	
	public History() {
		img = new ColorImage[size];
		 
	 }
	 
	 
	 public void add(ColorImage k) {
		 if(pointer == img.length) {
			 ColorImage[] v = new ColorImage[2*img.length];
			 for(int i = 0; i != img.length;i++) {
				 v[i] = img[i];
			 }
			 img = v;
		 }
		 img[pointer+1] = k.copy();
		 pointer++;
	 }
	 
	 ColorImage getUndo() {
		 pointer--;
		return img[pointer];
		
		
	}
	
	
	ColorImage getRedo() {
		pointer++;
		return img[pointer];
	}
			 

}