package GameObjects;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Gamestate.GamestateManager;
import Main.GUI;
import controller.DatabaseController;

public class GameOverviewInfoFrame extends JFrame {

	private GamestateManager gsm;
	private DatabaseController db_c;

	private JPanel panel;

	private int gameNumber;

	private boolean isCreated = false;

	public GameOverviewInfoFrame(GamestateManager gsm, DatabaseController db_c) {
		this.gsm = gsm;
		this.db_c = db_c;
		this.setResizable(false);
		this.setTitle("Wordfeud Competities");
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension((int) (GUI.WIDTH / 3), (int) (GUI.HEIGHT / 3)));
		this.setContentPane(panel);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("Resources/wordfeudLogo.png"));
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void loadFrame(int gameNumber) {
		this.gameNumber = gameNumber;
		if (isCreated) {
			panel.removeAll();
		}
		this.createLabel();
		this.createButton();
		if (!isCreated) {
			isCreated = true;
		}
		this.setVisible(true);
	}

	// TODO if game is created check this !
	private void createLabel() {
		Font font = new Font("Serif", Font.BOLD, 20);

		Box box = Box.createVerticalBox();
		JLabel turn = new JLabel("De beurt is aan: " + gsm.getUser().getPlayerTurn());
		turn.setFont(font);
		JLabel score = new JLabel(gsm.getUser().getChallengerName() + " - " + gsm.getUser().getUserScore() + " ~ VS ~ "
				+ gsm.getUser().getOpponentName() + " - " + gsm.getUser().getOpponentScore());
		score.setFont(font);
		box.add(score);
		box.add(new JLabel(" "));
		box.add(turn);

		this.add(box, BorderLayout.NORTH);
	}

	private void createButton() {
		JButton button = new JButton("Ga naar spel");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gsm.getUser().setGameNumber(gameNumber);
				gsm.getUser().setTurnNumber(gsm.getUser().getMaxTurnNumber());
				gsm.setGamestate(GamestateManager.playState);
				setVisible(false);
			}
		});
		this.add(button, BorderLayout.SOUTH);
	}

}
