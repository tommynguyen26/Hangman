import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage; 
import java.awt.event.*; 

public class Game  extends JPanel implements Runnable, KeyListener {

	private BufferedImage back; 
	private int key;
	private int SCREEN_WIDTH = 1000, SCREEN_HEIGHT = 700;
	private int CHAR_WIDTH = 100, CHAR_HEIGHT = 130, CHAR_XCOOR = 160;
	private int CHAR_YCOOR = 170;
	private int error = 0;
	private ImageIcon start, game, win, lose, character, word;
	private char screen = 'S';
	private Words w;
	private boolean reset;

	public Game() {
		new Thread(this).start();	
		this.addKeyListener(this);
		key = -1;
		start = new ImageIcon("start.png");
		game = new ImageIcon("gamescreen.png");
		win = new ImageIcon("winner.png");
		lose = new ImageIcon("lost.png");
		character = new ImageIcon("steve.png");
		word = new ImageIcon("wordbank.png");
		w = new Words();
		reset = false;
	}

	public void run() {
		try {
			while(true) {
				Thread.currentThread().sleep(30);
				repaint();
			}
		} catch(Exception e) {
		
		}
	}

	public void paint(Graphics g){

		Graphics2D twoDgraph = (Graphics2D) g; 
		if( back ==null)
			back=(BufferedImage)( (createImage(getWidth(), getHeight()))); 

		Graphics g2d = back.createGraphics();
		g2d.clearRect(0,0,getSize().width, getSize().height);
		drawGame(g2d);
		twoDgraph.drawImage(back, null, 0, 0);

	}

	public void movementChar(int yMax, Graphics g){	
		if(CHAR_YCOOR <= yMax){
			for (int i = 5; i <= yMax-CHAR_YCOOR+5; i+=5){
				CHAR_YCOOR += 5;
				g.drawImage(character.getImage(), CHAR_XCOOR, CHAR_YCOOR, CHAR_WIDTH, CHAR_HEIGHT, this);
			}
		}
		else g.drawImage(character.getImage(), CHAR_XCOOR, yMax, CHAR_WIDTH, CHAR_HEIGHT, this);
	}

	public void drawGame(Graphics g2d){
		switch (screen){
		case 'S':
			g2d.drawImage(start.getImage(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
			break;
		case 'C':
			g2d.drawImage(word.getImage(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
			break;
		case 'P':
			g2d.drawImage(game.getImage(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);	
			g2d.setColor(Color.WHITE);
			gameScreen(g2d);
			g2d.setFont(new Font("Consolas", Font.BOLD, 50));
			FontMetrics metrics = getFontMetrics(g2d.getFont());
			g2d.drawString(w.returnDisplayWord(), 719 -  metrics.stringWidth(w.returnDisplayWord())/2, SCREEN_HEIGHT/2 + 50);
			g2d.setFont(new Font("Consolas", Font.PLAIN, 30));
			g2d.drawString("" + returnRemainingError(), 550, 670);
			break;
		case 'L':
			g2d.drawImage(lose.getImage(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Consolas", Font.BOLD, 30));
			g2d.drawString("" + w.returnAnswer(), 330, 545);
			reset(g2d);
			break;
		case 'W':
			g2d.drawImage(win.getImage(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);
			reset(g2d);
			break;
		}
	}

	public void gameScreen(Graphics g2d){
		g2d.drawImage(game.getImage(), 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);

		switch(error){
		case 7 :
			movementChar(380, g2d);
			break;
		case 6: 
			movementChar(330, g2d);
			break;
		case 5:
			movementChar(315, g2d);
			break;
		case 4:
			movementChar(290, g2d);
			break;
		case 3: 
			movementChar(260, g2d);
			break;
		case 2: 
			movementChar(230, g2d);
			break;
		case 1: 
			movementChar(200, g2d);
			break;
		case 0: 
			g2d.drawImage(character.getImage(), CHAR_XCOOR, CHAR_YCOOR, CHAR_WIDTH, CHAR_HEIGHT, this);
			reset = false;
			break;
		default: 
			screen = 'L';
		}

	}

	public int returnRemainingError(){
		int remainError = 8   - error;
		return remainError;
	}

	public void reset(Graphics g2d){
		if (reset){
			error = 0;
			screen = 'S';
			w.reset();
			CHAR_WIDTH = 100;
			CHAR_HEIGHT = 130;
			CHAR_XCOOR = 180;
			CHAR_YCOOR = 170;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		key = e.getKeyCode();
		char charKey = (char)(key);
		if (screen == 'P'){
			if (w.checkChar(charKey)){	
				if (w.checkWin()){
					screen = 'W';
				}
			}
			else error++;
		}

		if (key  == 39 && screen == 'S'){
			screen = 'P';
		}

		if (key == 37 && (screen == 'L' || screen == 'W')){ 
			reset = true;
		}
		
		if (key == 40 && screen == 'S'){
			screen = 'C';
		}
		
		if (key == 38 && screen == 'C'){
			screen = 'S';
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
