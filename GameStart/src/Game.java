import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {
	public int v_padraomenor = -3;
	public int v_padraomaior = 3;
	private boolean k_cima = false;
	private boolean k_baixo = false;
	private boolean k_esquerdo = false;
	private boolean k_direito = false;

	private BufferedImage imgAtual;
	private BufferedImage imgAtualtubarao1;
	private BufferedImage imgAtualtubarao2;
	private Inimigo inimigo;
	private Inimigo segundoinimigo;
	private Beneficio beneficio;
	private Tesouro tesouro;
	
	public DecimalFormat df = new DecimalFormat("0.0");
	public Fundo bg;
	public Bola bola;

	public Game() {
		try {
			bg = new Fundo("imgs/bg1.png", 0);

		} catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!");

		}

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					k_cima = false;
					break;
				case KeyEvent.VK_DOWN:
					k_baixo = false;
					break;
				case KeyEvent.VK_LEFT:
					k_esquerdo = false;
					break;
				case KeyEvent.VK_RIGHT:
					k_direito = false;
					break;

				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					k_cima = true;
					break;
				case KeyEvent.VK_DOWN:
					k_baixo = true;
					break;
				case KeyEvent.VK_LEFT:
					k_esquerdo = true;
					break;
				case KeyEvent.VK_RIGHT:
					k_direito = true;
					break;

				}
			}
		});

		bola = new Bola();
		inimigo = new Inimigo();
		segundoinimigo = new Inimigo();
		segundoinimigo.Start();
		inimigo.Start();
		inimigo.StartTarefaPontos();
		beneficio = new Beneficio();
		tesouro = new Tesouro();
		setFocusable(true);
		setLayout(null);

		new Thread(new Runnable() {
			@Override
			public void run() {
				gameloop();
			}
		}).start();
	}

	// GAMELOOP -------------------------------
	public void gameloop() {
		while (true) { // representa as repetiçõs quadro a quadro
			handlerEvents();
			update();
			render();

			try {
				Thread.sleep(17);

			} catch (InterruptedException ex) {
				Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);

			}
		}
	}

	public void handlerEvents() {
		bola.velX = 0;
		bola.velY = 0;
		imgAtual = bola.bola_parada;
		imgAtualtubarao1 = inimigo.esquerda;
		imgAtualtubarao2 = segundoinimigo.esquerda;

		if (inimigo.velY < 0 && inimigo.velX == 0) {
			imgAtualtubarao1 = inimigo.cima;
		} else if (inimigo.velY > 0 && inimigo.velX == 0) {
			imgAtualtubarao1 = inimigo.baixo;
		} else if (inimigo.velY < 0 && inimigo.velX > 0) {
			imgAtualtubarao1 = inimigo.direita_cima;
		} else if (inimigo.velY > 0 && inimigo.velX > 0) {
			imgAtualtubarao1 = inimigo.direita_baixo;
		} else if (inimigo.velY < 0 && inimigo.velX < 0) {
			imgAtualtubarao1 = inimigo.esquerda_cima;
		} else if (inimigo.velY > 0 && inimigo.velX < 0) {
			imgAtualtubarao1 = inimigo.esquerda_baixo;
		} else if (inimigo.velY == 0 && inimigo.velX > 0) {
			imgAtualtubarao1 = inimigo.direita;
		}

		if (segundoinimigo.velY < 0 && segundoinimigo.velX == 0) {
			imgAtualtubarao2 = segundoinimigo.cima;
		} else if (segundoinimigo.velY > 0 && segundoinimigo.velX == 0) {
			imgAtualtubarao2 = segundoinimigo.baixo;
		} else if (segundoinimigo.velY < 0 && segundoinimigo.velX > 0) {
			imgAtualtubarao2 = segundoinimigo.direita_cima;
		} else if (segundoinimigo.velY > 0 && segundoinimigo.velX > 0) {
			imgAtualtubarao2 = segundoinimigo.direita_baixo;
		} else if (segundoinimigo.velY < 0 && segundoinimigo.velX < 0) {
			imgAtualtubarao2 = segundoinimigo.esquerda_cima;
		} else if (segundoinimigo.velY > 0 && segundoinimigo.velX < 0) {
			imgAtualtubarao2 = segundoinimigo.esquerda_baixo;
		} else if (segundoinimigo.velY == 0 && segundoinimigo.velX > 0) {
			imgAtualtubarao2 = segundoinimigo.direita;
		}

		if (k_cima) {
			bola.velY = v_padraomenor;
			imgAtual = bola.cima;
			if (k_esquerdo) {
				bola.velX = v_padraomenor;
				imgAtual = bola.esquerda_cima;
			}
			if (k_direito) {
				bola.velX = v_padraomaior;
				imgAtual = bola.direita_cima;
			}

		} else if (k_baixo) {
			bola.velY = v_padraomaior;
			imgAtual = bola.baixo;
			if (k_esquerdo) {
				bola.velX = v_padraomenor;
				imgAtual = bola.esquerda_baixo;
			}
			if (k_direito) {
				bola.velX = v_padraomaior;
				imgAtual = bola.direita_baixo;
			}
			
		} else if (k_esquerdo) {
			bola.velX = v_padraomenor;
			imgAtual = bola.esquerda;
			
		} else if (k_direito) {
			bola.velX = v_padraomaior;
			imgAtual = bola.direita;
		}
	}

	public void update() {
		bola.posX = bola.posX + bola.velX;
		bola.posY = bola.posY + bola.velY;
		inimigo.posX = inimigo.posX + inimigo.velX;
		inimigo.posY = inimigo.posY + inimigo.velY;
		segundoinimigo.posX = segundoinimigo.posX + segundoinimigo.velX;
		segundoinimigo.posY = segundoinimigo.posY + segundoinimigo.velY;
		testeColisoes();

	}

	public void render() {
		repaint();
		
	}

	public void testeColisoes() {

		// muda a posição da bola quando colide com as laterais da tela
		if (bola.posX + (bola.raio * 2) >= Principal.LARGURA_TELA || bola.posX <= 0) {
			bola.posX = bola.posX - bola.velX;
		}
		if (inimigo.posX + (inimigo.raio * 2) >= Principal.LARGURA_TELA || inimigo.posX <= 0) {
			inimigo.velX = inimigo.velX * -1;
		}
		if (segundoinimigo.posX + (segundoinimigo.raio * 2) >= Principal.LARGURA_TELA || segundoinimigo.posX <= 0) {
			segundoinimigo.velX = segundoinimigo.velX * -1;
		}
		if (bola.posY + (bola.raio * 2) >= Principal.ALTURA_TELA || bola.posY <= 0) {
			bola.posY = bola.posY - bola.velY;
		}
		if (inimigo.posY + (inimigo.raio * 2) >= Principal.ALTURA_TELA || inimigo.posY <= 0) {
			inimigo.velY = inimigo.velY * -1;
		}
		if (segundoinimigo.posY + (segundoinimigo.raio * 2) >= Principal.ALTURA_TELA || segundoinimigo.posY <= 0) {
			segundoinimigo.velY = segundoinimigo.velY * -1;
		}

		int CatetoH = bola.posX - inimigo.posX;
		int CatetoV = bola.posY - inimigo.posY;
		double hipotenusa = Math.sqrt(Math.pow(CatetoH, 2) + Math.pow(CatetoV, 2));
		
		/** Verifica quando há colisão da bola com o inimigo, se houve, mostra a 
		 * pontuação e fecha o game
		 */
		
		if (hipotenusa <= bola.raio + inimigo.raio) {
			bola.posX = bola.posX - bola.velX;
			bola.posY = bola.posY - bola.velY;
			JOptionPane.showMessageDialog(null,
					"Seu tempo: " + df.format(inimigo.pontuacao) + " segundos\n\n" + "Tesouros obtidos: "
							+ inimigo.pontos + "\n\nPontuação total: " + df.format(inimigo.pontos * inimigo.pontuacao),
					"Fim de jogo", JOptionPane.CLOSED_OPTION);
			System.exit(0);
			
		}

		// Segundo inimigo
		
		int CatetoH2 = bola.posX - segundoinimigo.posX;
		int CatetoV2 = bola.posY - segundoinimigo.posY;
		double hipotenusa2 = Math.sqrt(Math.pow(CatetoH2, 2) + Math.pow(CatetoV2, 2));

		if (hipotenusa2 <= bola.raio + segundoinimigo.raio) {
			bola.posX = bola.posX - bola.velX;
			bola.posY = bola.posY - bola.velY;
			JOptionPane.showMessageDialog(null,
					"Seu tempo: " + df.format(inimigo.pontuacao) + " segundos\n\n" + "Tesouros obtidos: "
							+ inimigo.pontos + "\n\nPontuação total: " + df.format(inimigo.pontos * inimigo.pontuacao),
					"Fim de jogo", JOptionPane.CLOSED_OPTION);
			System.exit(0);
			
		}

		int CatetoTesouroH = bola.posX - tesouro.posX;
		int CatetoTesouroV = bola.posY - tesouro.posY;
		double hipotenusaTesouro = Math.sqrt(Math.pow(CatetoTesouroH, 2) + Math.pow(CatetoTesouroV, 2));

		// verifica colisão com tesouro
		if (hipotenusaTesouro <= bola.raio + tesouro.raio) {
			inimigo.pontos++;
			tesouro.posX = tesouro.aleatorio.nextInt(930);
			tesouro.posY = tesouro.aleatorio.nextInt(500);
			
		}

		int CatetoBeneficioH = bola.posX - beneficio.posX;
		int CatetoBeneficioV = bola.posY - beneficio.posY;
		double hipotenusaBeneficio = Math.sqrt(Math.pow(CatetoBeneficioH, 2) + Math.pow(CatetoBeneficioV, 2));
		
		// Verifica e realiza a ação quando pega o beneficio//bonus
		if (hipotenusaBeneficio <= bola.raio + beneficio.raio) {			
			v_padraomaior = 5;
			v_padraomenor = -5;
			beneficio.posX = Principal.LARGURA_TELA + 20;

			Timer contador_beneficio = new Timer();
			// tarefa responsável pelo volta do bonus
			TimerTask tarefa_cBeneficio = new TimerTask() {

				@Override
				public void run() {
					beneficio.raio = 20;
					beneficio.posX = beneficio.aleatorio.nextInt(Principal.LARGURA_TELA - 100);
					beneficio.posY = beneficio.aleatorio.nextInt(Principal.ALTURA_TELA - 150);

				}
			};
			
			// tarefa responsável pelo termíno do bonus
			TimerTask tarefa_cBeneficio_tempo = new TimerTask() {

				@Override
				public void run() {
					v_padraomaior = 3;
					v_padraomenor = -3;

				}
			};
			
			// conta o tempo para o bonus aparecer novamente
			contador_beneficio.schedule(tarefa_cBeneficio, 15000);
			// conta o tempo para o bonus acabar
			contador_beneficio.schedule(tarefa_cBeneficio_tempo, 7000);

		}
	}

	// METODO SOBRESCRITO ---------------------
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		AffineTransform af01 = new AffineTransform();

		af01.translate(bg.posX, bg.posY);
		g2d.drawImage(bg.img, af01, null);

		g.drawImage(imgAtual, bola.posX, bola.posY, null);
		g.drawImage(imgAtualtubarao1, inimigo.posX, inimigo.posY, null);
		g.drawImage(imgAtualtubarao2, segundoinimigo.posX, segundoinimigo.posY, null);
		g.drawImage(beneficio.img, beneficio.posX, beneficio.posY, null);
		g.drawImage(tesouro.img, tesouro.posX, tesouro.posY, null);

	}

}
