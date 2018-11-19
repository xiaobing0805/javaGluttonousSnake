package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import snake.util.Global;


public class Ground {

	/* ���ʯͷ�Ķ�ά���� */
	private boolean rocks[][] = new boolean[Global.WIDTH][Global.HEIGHT];

	/* ���getFreePoint()�������ɵĲ���ʯͷ����������� */
	private Point freePoint = new Point();

	public static final Color DEFAULT_ROCK_COLOR = Color.GRAY;
	/* ʯͷǽ����ɫ */
	private Color rockColor = DEFAULT_ROCK_COLOR;

	public static final Color DEFAULT_GRIDDING_COLOR = Color.LIGHT_GRAY;

	/* �������ɫ */
	private Color griddingColor = DEFAULT_GRIDDING_COLOR;

	private Random random = new Random();

	/* �Ƿ�����Ŀ��� */
	private boolean drawGridding = false;

	public Ground() {
		init();
	}

	/**
	 * ��ʼ������(���ʯͷ)
	 */
	public void clear() {
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++)
				rocks[x][y] = false;
	}

	public void init() {
		clear();
		generateRocks();
	}

	/**
	 * ����ʯͷ, ���Ը�����������ı�ʯͷ�ڵ����ϵĲ���
	 */
	public void generateRocks() {
		for (int x = 0; x < Global.WIDTH; x++)
			rocks[x][0] = rocks[x][Global.HEIGHT - 1] = true;
		for (int y = 0; y < Global.HEIGHT; y++)
			rocks[0][y] = rocks[Global.WIDTH - 1][y] = true;
	}

	public void generateRocks2() {

		for (int y = 0; y < 6; y++) {
			rocks[0][y] = true;
			rocks[Global.WIDTH - 1][y] = true;
			rocks[0][Global.HEIGHT - 1 - y] = true;
			rocks[Global.WIDTH - 1][Global.HEIGHT - 1 - y] = true;
		}
		for (int y = 3; y < Global.HEIGHT - 3; y++) {
			rocks[6][y] = true;
			rocks[Global.WIDTH - 10][y] = true;
		}
	}

	/**
	 * ���һ��ʯͷ��ָ����������
	 * 
	 * @param x
	 *            �������� x
	 * @param y
	 *            �������� y
	 */
	public void addRock(int x, int y) {
		rocks[x][y] = true;
	}

	/**
	 * ���Ƿ�������ʯͷ
	 * 
	 * @param p
	 * @return
	 */
	public boolean isSnakeEatRock(Snake snake) {
		return rocks[snake.getHead().x][snake.getHead().y];
	}

	/**
	 * �������һ������ʯͷ������, ���ڶ�ʳ��
	 * 
	 * @return
	 */
	public Point getFreePoint() {
		do {
			freePoint.x = random.nextInt(Global.WIDTH);
			freePoint.y = random.nextInt(Global.HEIGHT);
		} while (rocks[freePoint.x][freePoint.y]);
		return freePoint;
	}

	/**
	 * �õ�ʯͷ����ɫ
	 * 
	 * @return
	 */
	public Color getRockColor() {
		return rockColor;
	}

	/**
	 * ����ʯͷ����ɫ
	 * 
	 * @param rockColor
	 */
	public void setRockColor(Color rockColor) {
		this.rockColor = rockColor;
	}
	public void drawMe(Graphics g) {
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++) {
				/* ��ʯͷ */
				if (rocks[x][y]) {
					g.setColor(rockColor);
					drawRock(g, x * Global.CELL_WIDTH, y * Global.CELL_HEIGHT,
							Global.CELL_WIDTH, Global.CELL_HEIGHT);
				} else if (drawGridding) {
					/* ������(�������) */
					g.setColor(griddingColor);
					drawGridding(g, x * Global.CELL_WIDTH, y
							* Global.CELL_HEIGHT, Global.CELL_WIDTH,
							Global.CELL_HEIGHT);
				}
			}
	}

	/**
	 * ��һ��ʯͷ
	 * 
	 * @param g
	 * @param x
	 *            �������� x
	 * @param y
	 *            �������� y
	 * @param width
	 *            ���(��λ:����)
	 * @param height
	 *            �߶�(��λ:����)
	 */
	public void drawRock(Graphics g, int x, int y, int width, int height) {
		g.fill3DRect(x, y, width, height, true);
	}

	/**
	 * ������
	 * 
	 * @param g
	 * @param x
	 * �������� x
	 * @param y
	 * �������� y
	 * @param width
	 * ���(��λ:����)
	 * @param height
	 *            �߶�(��λ:����)
	 */
	public void drawGridding(Graphics g, int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}

	/**
	 * �õ��������ɫ
	 * 
	 * @return
	 */
	public Color getGriddingColor() {
		return griddingColor;
	}

	/**
	 * �����������ɫ
	 * 
	 * @param griddingColor
	 */
	public void setGriddingColor(Color griddingColor) {
		this.griddingColor = griddingColor;
	}

	/**
	 * �Ƿ�����
	 * 
	 * @return
	 */
	public boolean isDrawGridding() {
		return drawGridding;
	}

	/**
	 * �����Ƿ�����
	 * 
	 * @param drawGridding
	 */
	public void setDrawGridding(boolean drawGridding) {
		this.drawGridding = drawGridding;
	}

}
