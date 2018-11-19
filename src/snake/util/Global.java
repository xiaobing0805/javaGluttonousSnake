package snake.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Global {

	private static Properties properties = new Properties();

	private static String CONFIG_FILE = "snake.ini";

	public static final int CELL_WIDTH;   //网格的宽度

	public static final int CELL_HEIGHT; //网格的高度
	
	public static final int WIDTH;

	public static final int HEIGHT;

	/**
	 * 显示的像素宽度 (格子宽度度 , 每一格的宽度)
	 */
	public static final int CANVAS_WIDTH;
	/**
	 * 显示的像素高度 (格子高度 * 每一格的高度)
	 */
	public static final int CANVAS_HEIGHT;
	/**
	 * 蛇的初始长度, 对应的配置文件中的关键字为: init_length<BR>
	 * 范围2 至 最大宽度<BR>
	 * (单位:格) 缺省值为 2
	 */
	public static final int INIT_LENGTH;

	/**
	 * 蛇的初始速度 (单位: 毫秒/格)<BR>
	 * 缺省值为 200
	 */
	public static final int SPEED;

	/**
	 * 蛇每次加速或减速的幅度 (单位: 毫秒/格)<BR>
	 * 缺省值为 25
	 */
	public static final int SPEED_STEP;

	public static final String TITLE_LABEL_TEXT;

	public static final String INFO_LABEL_TEXT;
	private Global() {
	}
	static {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(CONFIG_FILE);
			properties.load(inputStream);
		} catch (Exception e) {
			System.out.println("没有配置文件");
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Integer temp = null;
		/* 没有设置或设置的无效时要有一个默认值 */
		WIDTH = (temp = getIntValue("width")) != null && temp <= 80
				&& temp >= 10 ? temp : 35;
		HEIGHT = (temp = getIntValue("height")) != null && temp <= 60
				&& temp >= 10 ? temp : 20;
		INIT_LENGTH = (temp = getIntValue("init_length")) != null && temp > 1
				&& temp < WIDTH ? temp : 2;
		SPEED = (temp = getIntValue("speed")) != null && temp >= 10 ? temp
				: 200;   //贪吃蛇默认移动速度
		SPEED_STEP = (temp = getIntValue("speed_step")) != null && temp >= 1 ? temp
				: 25;   //贪吃蛇调速时的幅度，每操作一下增加或者减少25毫秒每格

		//下面设置的是游戏区网格的长宽大小
		CELL_WIDTH = (temp = getIntValue("cell_width")) != null && temp > 0
				&& temp <= 100 ? temp : 16;
		CELL_HEIGHT = (temp = getIntValue("cell_height")) != null && temp > 0
				&& temp <= 100 ? temp : 20;

		CANVAS_WIDTH = WIDTH * CELL_WIDTH;
		CANVAS_HEIGHT = HEIGHT * CELL_HEIGHT;

		String tempStr = null;
		TITLE_LABEL_TEXT = (tempStr = getValue("title")) == null ? "操作说明:"
				: tempStr;
		INFO_LABEL_TEXT = (tempStr = getValue("info")) == null ? "方向键控制方向\n空格键暂停/继续\nPAGE UP(加速)\nPAGE DOWN (减速)\n速度从低到高代表难度等级加深\n作者：周磊\n学号：2010517020 "
				: tempStr;
	}
	private static Integer getIntValue(String key) {
		if (key == null)
			throw new RuntimeException("key 不能为空");
		try {
			return new Integer(properties.getProperty(key));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private static String getValue(String key) {
		try {
			return new String(properties.getProperty(key).getBytes("iso8859-1"));
		} catch (Exception e) {
			return null;
		}
	}
}
