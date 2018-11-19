package snake.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import snake.entities.Food;
import snake.entities.Ground;
import snake.entities.Snake;
import snake.listener.GameListener;
import snake.listener.SnakeListener;
import snake.util.Global;
import snake.view.GamePanel;


@SuppressWarnings("unused")
public class Controller extends KeyAdapter implements SnakeListener {

	/* ���� */
	private Ground ground;

	/* �� */
	private Snake snake;

	/* ʳ�� */
	private Food food;

	/* ��ʾ */
	private GamePanel gamePanel;

	/* ��ʾ��Ϣ */
	private JLabel gameInfoLabel;

	private boolean playing;

	private int map;

	/* ������������ */
	private Set<GameListener> listeners = new HashSet<GameListener>();

	/**
	 //������̰�ť����
	 * UP: �ı��ߵ��ƶ�����Ϊ����<BR>
	 * DOWN: �ı��ߵ��ƶ�����Ϊ����<BR>
	 * LEFT: �ı��ߵ��ƶ�����Ϊ���� <BR>
	 * RIGHT: �ı��ߵ��ƶ�����Ϊ����<BR>
	 * SPACE: ��ͣ/����<BR>
	 * PAGE UP: �ӿ��ߵ��ƶ��ٶ�<BR>
	 * PAGE DOWN: �����ߵ��ƶ��ٶ�<BR>
	 * Y: ���¿�ʼ��Ϸ
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() != KeyEvent.VK_Y && !playing)
			return;
		// TODO Auto-generated method stub
		/* ���ݰ�����ͬ, ���߸ı䲻ͬ�ķ��� */
		switch (e.getKeyCode()) {

		/* ����� �� */
		case KeyEvent.VK_UP:
			if (snake.isPause()) {
				snake.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			snake.changeDirection(Snake.UP);
			break;
		/* ����� �� */
		case KeyEvent.VK_DOWN:
			if (snake.isPause()) {
				snake.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			snake.changeDirection(Snake.DOWN);
			break;
		/* ����� �� */
		case KeyEvent.VK_LEFT:
			if (snake.isPause()) {
				snake.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			snake.changeDirection(Snake.LEFT);
			break;
		/* ����� �� */
		case KeyEvent.VK_RIGHT:
			if (snake.isPause()) {
				snake.changePause();
				for (GameListener l : listeners)
					l.gameContinue();
			}
			snake.changeDirection(Snake.RIGHT);
			break;
		/* �س���ո� (��ͣ) */
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			snake.changePause();
			/* === */
			for (GameListener l : listeners)
				if (snake.isPause())
					l.gamePause();
				else
					l.gameContinue();
			break;
		/* PAGE_UP ���� */
		case KeyEvent.VK_PAGE_UP:
			snake.speedUp();
			break;
		/* PAGE_DOWN ���� */
		case KeyEvent.VK_PAGE_DOWN:
			snake.speedDown();
			break;
		/* ��ĸ�� Y (���¿�ʼ��Ϸ) */
		case KeyEvent.VK_Y:
			if (!isPlaying())
				newGame();
			break;
		}

		/* ������ʾ */
		if (gamePanel != null)
			gamePanel.redisplay(ground, snake, food);
		/* ������ʾ */
		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}

	/**
	 * ����Snake ������ snakeMoved �¼�<BR>
	 */
	public void snakeMoved() {

		/* �ж��Ƿ�Ե�ʳ�� */
		if (food != null && food.isSnakeEatFood(snake)) {
			/* �Ե�ʳ���, ����������, �������������һ��ʳ�� */
			snake.eatFood();
			food.setLocation(ground == null ? food.getNew() : ground
					.getFreePoint());

		}
		else if (ground != null && ground.isSnakeEatRock(snake)) {
			//�������ײ�����ܵ�ʯͷǽ�ͽ�����Ϸ
			JOptionPane.showMessageDialog(null, "ײ��ǽ�ڣ���Ϸ������","��Ϸ����",JOptionPane.INFORMATION_MESSAGE);
			stopGame();  //������ʾ
		}
		/* ����߳Ե��Լ�������, �ͽ�����Ϸ */
		if (snake.isEatBody())
			{JOptionPane.showMessageDialog(null, "ײ�����壬��Ϸ������","��Ϸ����",JOptionPane.INFORMATION_MESSAGE);
			stopGame();   
			}
		if (gamePanel != null)
			gamePanel.redisplay(ground, snake, food);
		/* ������ʾ */
		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}
		    
	/**
	 * ������ʾ����
	 * @param drawGridding 
	 */
	public void drawGridding(boolean drawGridding){
		if(playing)
		snake.setPause(true);
		
	}
	/**
	 * ��ʼһ������Ϸ
	 */
	public void newGame() {

		if (ground != null) {
			switch (map) {
			case 2:
				ground.clear();
				ground.generateRocks2();
				break;
			default:
				ground.init();
				break;
			}
		}
		playing = true;

		snake.reNew();
		for (GameListener l : listeners)
			l.gameStart();
	}

	/**
	 * ������Ϸ
	 */
	public void stopGame() {
		if (playing) {
			playing = false;
			snake.dead();
			for (GameListener l : listeners)
				l.gameOver();
		}
	}

	/**
	 * ��ͣ��Ϸ
	 */
	public void pauseGame() {
		snake.setPause(true);
		for (GameListener l : listeners)
			l.gamePause();
	}

	/**
	 * ������Ϸ
	 */
	public void continueGame() {
		snake.setPause(false);
		for (GameListener l : listeners)
			l.gameContinue();
	}
	public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
		/* �ȶ�һ��ʳ�� */
		if (ground != null && food != null)
			food.setLocation(ground.getFreePoint());
		/* ע������� */
		this.snake.addSnakeListener(this);
	}
	public Controller(Snake snake, Food food, Ground ground,
			GamePanel gamePanel, JLabel gameInfoLabel) {

		this(snake, food, ground, gamePanel);
		this.setGameInfoLabel(gameInfoLabel);

		if (gameInfoLabel != null)
			gameInfoLabel.setText(getNewInfo());
	}

	/**
	 * �õ����µ���ʾ��Ϣ
	 * 
	 * @return �ߵ�������Ϣ
	 */
	//��ǰ��Ϸ�ٶȣ�����ʵ�����ʵʱ��ʾ̰�����ƶ����ٶ�
	public String getNewInfo() {
		if (!snake.isLive())
			return " ";// " ��ʾ: �� Y ��ʼ����Ϸ";
		else
			return new StringBuffer().append("�ٶ���ʾ: ").append("�ٶ� ").append(
					snake.getSpeed()).toString()
					+ " ����/��";
	}

	/**
	 * ��Ӽ�����
	 */
	public synchronized void addGameListener(GameListener l) {
		if (l != null)
			this.listeners.add(l);
	}

	/**
	 * �Ƴ�������
	 */
	public synchronized void removeGameListener(GameListener l) {
		if (l != null)
			this.listeners.remove(l);
	}

	/**
	 * �õ��ߵ�����
	 * 
	 * @return
	 */
	public Snake getSnake() {
		return this.snake;
	}

	/**
	 * �õ�ʳ�������
	 * 
	 * @return
	 */
	public Food getFood() {
		return this.food;
	}

	/**
	 * �õ����ε�����
	 * 
	 * @return
	 */
	public Ground getGround() {
		return this.ground;
	}

	public void snakeEatFood() {
		// TODO Auto-generated method stub
		System.out.println("�Ե�ʳ��!");
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	/**
	 * ����GamePanel
	 * 
	 * @param gamePanel
	 */
	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public JLabel getGameInfoLabel() {
		return gameInfoLabel;
	}

	public void setGameInfoLabel(JLabel gameInfoLabel) {
		this.gameInfoLabel = gameInfoLabel;
		this.gameInfoLabel.setSize(Global.WIDTH * Global.CELL_WIDTH, 20);
		this.gameInfoLabel.setFont(new Font("����", Font.PLAIN, 15));
		gameInfoLabel.setText(this.getNewInfo());
	}

	public void setGround(Ground ground) {
		this.ground = ground;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public boolean isPausingGame() {
		// TODO Auto-generated method stub
		return snake.isPause();
	}

}
