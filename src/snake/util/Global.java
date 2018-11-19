package snake.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Global {

	private static Properties properties = new Properties();

	private static String CONFIG_FILE = "snake.ini";

	public static final int CELL_WIDTH;   //����Ŀ��

	public static final int CELL_HEIGHT; //����ĸ߶�
	
	public static final int WIDTH;

	public static final int HEIGHT;

	/**
	 * ��ʾ�����ؿ�� (���ӿ�ȶ� , ÿһ��Ŀ��)
	 */
	public static final int CANVAS_WIDTH;
	/**
	 * ��ʾ�����ظ߶� (���Ӹ߶� * ÿһ��ĸ߶�)
	 */
	public static final int CANVAS_HEIGHT;
	/**
	 * �ߵĳ�ʼ����, ��Ӧ�������ļ��еĹؼ���Ϊ: init_length<BR>
	 * ��Χ2 �� �����<BR>
	 * (��λ:��) ȱʡֵΪ 2
	 */
	public static final int INIT_LENGTH;

	/**
	 * �ߵĳ�ʼ�ٶ� (��λ: ����/��)<BR>
	 * ȱʡֵΪ 200
	 */
	public static final int SPEED;

	/**
	 * ��ÿ�μ��ٻ���ٵķ��� (��λ: ����/��)<BR>
	 * ȱʡֵΪ 25
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
			System.out.println("û�������ļ�");
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Integer temp = null;
		/* û�����û����õ���ЧʱҪ��һ��Ĭ��ֵ */
		WIDTH = (temp = getIntValue("width")) != null && temp <= 80
				&& temp >= 10 ? temp : 35;
		HEIGHT = (temp = getIntValue("height")) != null && temp <= 60
				&& temp >= 10 ? temp : 20;
		INIT_LENGTH = (temp = getIntValue("init_length")) != null && temp > 1
				&& temp < WIDTH ? temp : 2;
		SPEED = (temp = getIntValue("speed")) != null && temp >= 10 ? temp
				: 200;   //̰����Ĭ���ƶ��ٶ�
		SPEED_STEP = (temp = getIntValue("speed_step")) != null && temp >= 1 ? temp
				: 25;   //̰���ߵ���ʱ�ķ��ȣ�ÿ����һ�����ӻ��߼���25����ÿ��

		//�������õ�����Ϸ������ĳ����С
		CELL_WIDTH = (temp = getIntValue("cell_width")) != null && temp > 0
				&& temp <= 100 ? temp : 16;
		CELL_HEIGHT = (temp = getIntValue("cell_height")) != null && temp > 0
				&& temp <= 100 ? temp : 20;

		CANVAS_WIDTH = WIDTH * CELL_WIDTH;
		CANVAS_HEIGHT = HEIGHT * CELL_HEIGHT;

		String tempStr = null;
		TITLE_LABEL_TEXT = (tempStr = getValue("title")) == null ? "����˵��:"
				: tempStr;
		INFO_LABEL_TEXT = (tempStr = getValue("info")) == null ? "��������Ʒ���\n�ո����ͣ/����\nPAGE UP(����)\nPAGE DOWN (����)\n�ٶȴӵ͵��ߴ����Ѷȵȼ�����\n���ߣ�����\nѧ�ţ�2010517020 "
				: tempStr;
	}
	private static Integer getIntValue(String key) {
		if (key == null)
			throw new RuntimeException("key ����Ϊ��");
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
