public class EfeitosCompostos {
	
	public static final int NOISE = 0;
	public static final int CONTRAST = 1;
	public static final int VIGNETTE = 2;
	public static final int SEPIA = 3;
	public static final int BLUR = 4;
	public static final int FILM = 5;
	
	final int Max_Effects = 10;
	
	
	private int next;
	private int[][] img;
	
	EfeitosCompostos() {
		img = new int[Max_Effects][2];
		next = 0;
	}
		
	
	public void add(int efeito) {
		if(efeito < 0 || efeito > 5)
			throw new IllegalArgumentException("Fora do intervalo");
		img[next][0] = efeito;
		next++;
		}
	
	public void add(int efeito, int valor ) {
		if(efeito < 0 || efeito > 5)
			throw new IllegalArgumentException("Fora do intervalo");
		img[next][0] = efeito;
		img[next][1] = valor;
		next++;
	}
	//senão der trocar imageseditor por colorimage
	public ColorImage apply(ColorImage k) {
		
		ImagesEditor v = new ImagesEditor(k);
		
		for(int i = 0; i < next; i++) {
			
			if(img[i][0] == 0)
				v.Noise(img[i][1]);
			if(img[i][0] == 1)
				v.Contrast(img[i][1]);
			if(img[i][0] == 2)
				v.Vignette(img[i][1]);
			if(img[i][0] == 3)
				v.Sepia();
			if(img[i][0] == 4)
				v.Blur(img[i][1]);
			if(img[i][0] == 5)
				v.film(img[i][1]);
		}
		return v.getImage();
	}
	
	
	
	
				
}