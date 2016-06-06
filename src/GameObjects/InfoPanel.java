package GameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.plaf.metal.OceanTheme;

import Gamestate.GamestateManager;
import Main.Drawable;
import controller.DatabaseController;
import model.User;

public class InfoPanel implements Drawable {

	private int x;
	private int y;

	private int width;
	private int height;

	private DatabaseController db_c;
	private GamestateManager gsm;

	private String username;
	private int userscore;

	private String opponentname;
	private int opponentscore;
	
	private String playerTurn;
	
	private int turnNumber;
	
	private Image image;
	private Color color;

	public InfoPanel(int x, int y, int width, int height, DatabaseController db_c, GamestateManager gsm) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.db_c = db_c;
		this.gsm = gsm;
		image = Toolkit.getDefaultToolkit().getImage("Resources/infoPanelBackground.jpg");
		color = new Color(17,190,7);
		this.reloadInfoPanel();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		/*g.setColor(Color.gray);
		g.fillRect(x - 1, y + 2, width, height + 5);*/
		g.drawImage(image, x-1, y+2, width, height, null);
		g.setColor(color);
		g.setFont(new Font("Verdana", Font.BOLD, 14));
		// User
		g.drawString(username + " - " + userscore, x + 5, y + (height / 3));
		// Opponent
		g.drawString(opponentname + " - " + opponentscore, x + 5, y + (height));
		// Turn indicator
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.drawString("'" + playerTurn + "' is aan de beurt!", x + (width / 3), y + (height));
		//Turn number
		g.setFont(new Font("Verdana", Font.BOLD, 14));
		g.drawString("Beurtnummer: "+(turnNumber-1), x+(width-(width/4)), y+17);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	public void reloadInfoPanel(){
		playerTurn = gsm.getUser().getPlayerTurn();
		userscore = gsm.getUser().getUserScore();
		opponentname = gsm.getUser().getOpponentName();
		opponentscore = gsm.getUser().getOpponentScore();
		username = gsm.getUser().getChallengerName();
		turnNumber = gsm.getUser().getTurnNumber();
		db_c.closeConnection();
	}
}
