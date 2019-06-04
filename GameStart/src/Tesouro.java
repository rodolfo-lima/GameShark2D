import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Tesouro {	
	public int posX;
	public int posY;
	public int raio;
	public int raio2;

	public Random aleatorio = new Random();
	public BufferedImage img;
	public Game game;

	public Tesouro() {
		try {
			img = ImageIO.read(getClass().getResource("imgs/tesouro1.png"));

		} catch (IOException e) {

		}

		this.posX = aleatorio.nextInt(Principal.LARGURA_TELA - 100);
		this.posY = aleatorio.nextInt(Principal.ALTURA_TELA - 100);
		this.raio = 20;
		
	}
}
