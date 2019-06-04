import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Beneficio {
	public Random aleatorio = new Random();
	public BufferedImage img;
	public Game game;
	
	public int posX;
	public int posY;
	public int raio;
	public int raio2;

	public Beneficio() {
		try {
			img = ImageIO.read(getClass().getResource("imgs/tank1.png"));

		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
			
		}
		
		this.posX = aleatorio.nextInt(Principal.LARGURA_TELA - 100);
		this.posY = aleatorio.nextInt(Principal.ALTURA_TELA - 100);
		this.raio = 20;

	}
}
