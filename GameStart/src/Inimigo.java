import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class Inimigo {
	public BufferedImage direita;
	public BufferedImage cima;
	public BufferedImage baixo;
	public BufferedImage esquerda;
	public BufferedImage bola_parada;
	public BufferedImage esquerda_cima;
	public BufferedImage esquerda_baixo;
	public BufferedImage direita_cima;
	public BufferedImage direita_baixo;
	public BufferedImage img;
	public Random aleatorio = new Random();

	public int posX;
	public int posY;
	public int velX;
	public int contTempo = 0;
	public int iniciador = 0;
	public double pontuacao = 0;
	public double pontos = 0;
	public boolean verificar;
	public int velY;
	public int raio;

	public Timer tempo = new Timer();
	public TimerTask tarefa = new TimerTask() {

		@Override
		public void run() {
			iniciador = aleatorio.nextInt(2);
			velX = iniciador * -1;
			velY = iniciador;
			contTempo++;

			if (contTempo < 4) {
				iniciador = aleatorio.nextInt(4);
				velX = iniciador;
				velY = iniciador * -1;
			} else if (contTempo < 8) {
				iniciador = aleatorio.nextInt(8);
				velX = iniciador;
				velY = iniciador;
			} else if (contTempo < 10) {
				iniciador = aleatorio.nextInt(10);
				velX = iniciador * -1;
				velY = iniciador;
			} else if (contTempo < 12) {
				iniciador = aleatorio.nextInt(12);
				velX = iniciador;
				velY = iniciador * -1;
			} else if (contTempo < 15) {
				iniciador = aleatorio.nextInt(15);
				velX = iniciador * -1;
				velY = iniciador;
			}

		}
	};

	public void Start() {
		tempo.scheduleAtFixedRate(tarefa, 1000, 7000);
		
	}

	TimerTask tarefapontos = new TimerTask() {

		@Override
		public void run() {
			pontuacao += 100 * 0.001;

		}
	};

	public void StartTarefaPontos() {
		tempo.scheduleAtFixedRate(tarefapontos, 1000, 100);
	}

	public Inimigo() {

		try {
			this.esquerda = ImageIO.read(getClass().getResource("imgs/tubarao2esquerda.png"));
			this.cima = ImageIO.read(getClass().getResource("imgs/tubarao2cima.png"));
			this.direita = ImageIO.read(getClass().getResource("imgs/tubarao2direita.png"));
			this.baixo = ImageIO.read(getClass().getResource("imgs/tubarao2baixo.png"));
			this.esquerda_cima = ImageIO.read(getClass().getResource("imgs/tubarao2esquerdacima.png"));
			this.esquerda_baixo = ImageIO.read(getClass().getResource("imgs/tubarao2esquerdabaixo.png"));
			this.direita_cima = ImageIO.read(getClass().getResource("imgs/tubarao2direitocima.png"));
			this.direita_baixo = ImageIO.read(getClass().getResource("imgs/tubarao2direitobaixo.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
			
		}
		
		this.raio = 50;
		this.posX = aleatorio.nextInt(Principal.LARGURA_TELA - 100);
		this.posY = aleatorio.nextInt(Principal.ALTURA_TELA - 100);
	}
}
