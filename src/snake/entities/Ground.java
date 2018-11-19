package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import snake.util.Global;


public class Ground {

	/* 存放石头的二维数组 */
	private boolean rocks[][] = new boolean[Global.WIDTH][Global.HEIGHT];

	/* 存放getFreePoint()方法生成的不是石头的随机的坐标 */
	private Point freePoint = new Point();

	public static final Color DEFAULT_ROCK_COLOR = Color.GRAY;
	/* 石头墙的颜色 */
	private Color rockColor = DEFAULT_ROCK_COLOR;

	public static final Color DEFAULT_GRIDDING_COLOR = Color.LIGHT_GRAY;

	/* 网格的颜色 */
	private Color griddingColor = DEFAULT_GRIDDING_COLOR;

	private Random random = new Random();

	/* 是否画网格的开关 */
	private boolean drawGridding = false;

	public Ground() {
		init();
	}

	/**
	 * 初始化地面(清空石头)
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
	 * 产生石头, 可以覆盖这个方法改变石头在地面上的布局
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
	 * 添加一块石头到指定格子坐标
	 * 
	 * @param x
	 *            格子坐标 x
	 * @param y
	 *            格子坐标 y
	 */
	public void addRock(int x, int y) {
		rocks[x][y] = true;
	}

	/**
	 * 蛇是否碰到了石头
	 * 
	 * @param p
	 * @return
	 */
	public boolean isSnakeEatRock(Snake snake) {
		return rocks[snake.getHead().x][snake.getHead().y];
	}

	/**
	 * 随机生成一个不是石头的坐标, 用于丢食物
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
	 * 得到石头的颜色
	 * 
	 * @return
	 */
	public Color getRockColor() {
		return rockColor;
	}

	/**
	 * 设置石头的颜色
	 * 
	 * @param rockColor
	 */
	public void setRockColor(Color rockColor) {
		this.rockColor = rockColor;
	}
	public void drawMe(Graphics g) {
		for (int x = 0; x < Global.WIDTH; x++)
			for (int y = 0; y < Global.HEIGHT; y++) {
				/* 画石头 */
				if (rocks[x][y]) {
					g.setColor(rockColor);
					drawRock(g, x * Global.CELL_WIDTH, y * Global.CELL_HEIGHT,
							Global.CELL_WIDTH, Global.CELL_HEIGHT);
				} else if (drawGridding) {
					/* 画网格(如果允许) */
					g.setColor(griddingColor);
					drawGridding(g, x * Global.CELL_WIDTH, y
							* Global.CELL_HEIGHT, Global.CELL_WIDTH,
							Global.CELL_HEIGHT);
				}
			}
	}

	/**
	 * 画一块石头
	 * 
	 * @param g
	 * @param x
	 *            像素坐标 x
	 * @param y
	 *            像素坐标 y
	 * @param width
	 *            宽度(单位:像素)
	 * @param height
	 *            高度(单位:像素)
	 */
	public void drawRock(Graphics g, int x, int y, int width, int height) {
		g.fill3DRect(x, y, width, height, true);
	}

	/**
	 * 画网格
	 * 
	 * @param g
	 * @param x
	 * 像素坐标 x
	 * @param y
	 * 像素坐标 y
	 * @param width
	 * 宽度(单位:像素)
	 * @param height
	 *            高度(单位:像素)
	 */
	public void drawGridding(Graphics g, int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}

	/**
	 * 得到网格的颜色
	 * 
	 * @return
	 */
	public Color getGriddingColor() {
		return griddingColor;
	}

	/**
	 * 设置网格的颜色
	 * 
	 * @param griddingColor
	 */
	public void setGriddingColor(Color griddingColor) {
		this.griddingColor = griddingColor;
	}

	/**
	 * 是否画网格
	 * 
	 * @return
	 */
	public boolean isDrawGridding() {
		return drawGridding;
	}

	/**
	 * 设置是否画网格
	 * 
	 * @param drawGridding
	 */
	public void setDrawGridding(boolean drawGridding) {
		this.drawGridding = drawGridding;
	}

}
