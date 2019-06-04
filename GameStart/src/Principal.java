import java.awt.Dimension;

import javax.swing.JFrame;

public class Principal {

	public final static int LARGURA_TELA = 1024;
	public final static int ALTURA_TELA = 600;

	public Principal() {
		JFrame janela = new JFrame("");
		Game game = new Game();
		game.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		janela.getContentPane().add(game);
		// janela.setSize(LARGURA_TELA, ALTURA_TELA);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLocation(170, 65);
		janela.setVisible(true);
		janela.pack();

	}

	public static void main(String[] args) {
		new Principal();

	}

}
