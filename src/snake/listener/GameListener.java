package snake.listener;

/**
 * ��Ϸ������
 */
public interface GameListener {

	/**
	 * ��Ϸ��ʼ��
	 */
	void gameStart();

	/**
	 * ��Ϸ������
	 */
	void gameOver();

	/**
	 * ��Ϸ��ͣ��
	 */

	void gamePause();

	/**
	 * ��Ϸ����
	 */
	void gameContinue();
}
