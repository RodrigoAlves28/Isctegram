public class ImagesEditor {
	
	private History history;
	
	
	private ColorImage img;
	
	 ImagesEditor (ColorImage img) {
		this.img = img.copy();
		this.history = new History();
		 history.add(img);
	 }
	
	
	public ColorImage getImage() {
		return img;
	}
			
	
	//Efeito Noise
	
	public  void Noise(int intensidade) {
		for(int x=0; x<img.getWidth(); x++) {
			for(int y=0; y<img.getHeight();y++){
				//sortear se o pixel é alterado, neste caso se for menor ou igual a 4, então é alterado
				if(Math.random()*10<=4) {
					//sortear se o pixel será clareado ou escurecido, neste caso, se for <= 4 é escurecido, se for > é clareado
					if(Math.random()*10<=4) 
						img.setColor(x,y,img.getColor(x,y).brighter(limite(-intensidade)));
					else
						//ou seja >4
						img.setColor(x,y,img.getColor(x,y).brighter(limite(intensidade)));
				}
			 
			}
		}
		
		history.add(img);
	}
	//se n der o debug é porque isto tá private, mesma coisa para a média
	private int limite(int n) {
		if(n>255)
			return 255;
		if(n<0)
			return 0;
		return n;
	}
	
	
	//Efeito Contrast
	
	public void Contrast(int intensidade) {
		for(int x=0; x<img.getWidth(); x++) {
			for(int y=0; y<img.getHeight();y++){
				if(img.getColor(x,y).getLuminance()<128)
					img.setColor(x,y,img.getColor(x,y).brighter(limite(-intensidade)));
				if(img.getColor(x,y).getLuminance()>128)
					img.setColor(x,y,img.getColor(x,y).brighter(limite(intensidade)));
			}
		}
		history.add(img);
	}
	
	
	//Vignette

	public void Vignette(int dist) {
		//o "a" é a abcissa e o "b" a ordenada
		int a = img.getWidth()/2;
		int b = img.getHeight()/2;
		int[] center = {a,b};
		for(int x=0; x<img.getWidth(); x++) {
			for(int y=0; y<img.getHeight();y++){
				//h é a distância ao centro
				double h = Math.sqrt((x-center[0])*(x-center[0])+(y-center[1])*(y-center[1]));
				if(h>dist)
					img.setColor(x,y,img.getColor(x,y).brighter((int)(dist-h)));
			}
		}
		
		history.add(img);
	}
	
	
	//Sepia
		
		public void Sepia() {
			//vamos ter de percorrer cada pixel de forma a alterar a sua cor
			for(int x=0; x<img.getWidth(); x++) {
				for(int y=0; y<img.getHeight();y++){
					//vamos agora "calcular" os valores rgb
					int r = img.getColor(x,y).getR();
					int g = img.getColor(x,y).getG();
					int b = img.getColor(x,y).getB();
					//agora proede-seao cálculo do novo rgb, de forma a resultar o efeito sépia
					int R = limite((int)(0.4*r+0.77*g+0.2*b));
					int G = limite((int)(0.35*r+0.69*g+0.17*b));
					int B = limite((int)(0.27*r+0.53*g+0.13*b));
					Color k = new Color(R,G,B);
					img.setColor(x,y,k);
				}
			}
			
			history.add(img);
		}
		
		
		//Blur

		public void Blur(int r) { // r é o "raio" ou seja a abrangência da vizinhança de cada píxel da nova imagem.
			// criação de uma imagem com as dimensões da original, vamos chamá-la de
			//img2(pois esta vai ser uma imagem diferente, mas com as dimensões da original)
			int j = r; //o j tem o valor do raio
			ColorImage img2 = new ColorImage(img.getWidth(),img.getHeight());
				for(int x=0; x<img2.getWidth(); x++) {
					for(int y=0; y<img2.getHeight();y++){
						img2.setColor(x,y,média(img,x,y,j));
					}
					
				}
				img.paste(img2,0,0);
				history.add(img);
		}
		
						//o "a" é o meu x e o "b" o meu y
			
			private Color média(ColorImage k, int a, int b, int raio) {
				int xSup = Math.max(0, a - raio);
				int xInf = Math.min(k.getWidth(), a + raio);
				int	ySup = Math.max(0, b - raio);
				int yInf = Math.min(k.getHeight(), b + raio);
				int R = 0;
				int G = 0;
				int B = 0;
				int n = 0; //vai ser o meu contador, vai contar o número de pixeis
				for(int i = xSup; i < xInf; i++)
					for(int j = ySup; j < yInf; j++) {
						Color z = img.getColor(i,j);
						//calcular o R,G,B vizinhos,vou somar os "R" vizinhos, assim como "G" e "B" para depois fazer a média de cada um deles
						 R = R + z.getR();
						 G = G + z.getG();
						 B = B + z.getB();
						 n++;
					}
				R = R/n; //média do R 
				G = G/n; //média do G
				B = B/n; //média do B
				Color cor = new Color(R,G,B);
				return cor;
			}
		
			
			//Film
			
			public void film(int largura) {
				Color b = new Color (0,0,0); //preto
				Color w = new Color (255,255,255); //branco
				//começamos por criar a primeira barra preta
				for(int x = 0; x < largura; x++) {
					for(int y = 0; y < img.getHeight(); y++) {
						img.setColor(x,y,b);
					}
				}
				// agora cria-se a segunda barra preta
				
				for(int x = img.getWidth()- 1; x > img.getWidth() - 1 - largura; x--) {
					for(int y = 0; y < img.getHeight(); y++) {
						img.setColor(x,y,b);
					}
				}
				//pintar os quadrados na 1º barra preta
			for(int n = 0; n < img.getHeight() ; n+= img.getHeight()/4) {
				for(int x = largura/4; x < largura - largura/4; x++) {
					for(int y = largura/4 + n; y < img.getHeight()/4 + n; y++) {
						img.setColor(x,y,w);
					}
				}
			}
			
				//pintar os quadrados na 2º barra preta
				for(int n = 0; n < img.getHeight() ; n+= img.getHeight()/4){
					for(int x = img.getWidth() - 1 - largura/4 ; x > img.getWidth() - 1 - largura + largura/4; x--) {
						for(int y = largura/4 + n; y < img.getHeight()/4 + n ; y++) {
							img.setColor(x,y,w);

					
				}
					}
					
		}
				//pintar agora no fundo da imagem de preto, de forma a que fica com a mesma distância da extremidade inferior
				// que o retângulo inicial da extremidade superior
				for(int x = img.getWidth() - 1 - largura/4 ; x > img.getWidth() - 1 - largura + largura/4; x--) {
					for(int y = img.getHeight() - largura/4; y < img.getHeight(); y++) {
						img.setColor(x,y,b);
					}
				}
				
				for(int x = largura/4; x < largura - largura/4; x++) {
					for(int y = img.getHeight() - largura/4; y < img.getHeight(); y++) {
					img.setColor(x,y,b);
				}
					
				}
				history.add(img);
			}
			
			//histórico
			
		public void undo() {
				ColorImage z = history.getUndo();
				for(int i = 0; i != img.getWidth(); i++) {
					for(int j = 0; j != img.getWidth(); j++) {
						img.setColor(i,j,z.getColor(i,j));
		}
				}
		}
		
		public void redo() {
			ColorImage z = history.getRedo();
			for(int i = 0; i != img.getWidth(); i++) {
				for(int j = 0; j != img.getHeight(); j++) {
					img.setColor(i,j,z.getColor(i,j));
				}
			}
		}				
			
		
		//efeitos compostos
		
		public void apply2(EfeitosCompostos r) { 
			img = r.apply(img);
			 history.add(img);
		}	
		
		public void retro() {
			EfeitosCompostos x = new EfeitosCompostos();
			x.add(EfeitosCompostos.CONTRAST,30);
			x.add(EfeitosCompostos.BLUR, 10);
			x.add(EfeitosCompostos.VIGNETTE, 20);
		}
			
		public void old() {
			EfeitosCompostos x = new EfeitosCompostos();
			x.add(EfeitosCompostos.NOISE,30);
			x.add(EfeitosCompostos.SEPIA, 10);
			x.add(EfeitosCompostos.VIGNETTE, 20);
		}
		
		
		static void teste() {
			ColorImage x = new ColorImage("simp.jpg");
			ImagesEditor v = new ImagesEditor(x);
			EfeitosCompostos k = new EfeitosCompostos();
			k.add(EfeitosCompostos.SEPIA);
			k.add(EfeitosCompostos.NOISE,15);
			v.apply2(k);
		}
			
	}

		
	
	
				
						
	
	
	

	
	
	
	
	
	
	
	
	
