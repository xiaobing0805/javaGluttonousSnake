package snake.game;



import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import snake.controller.Controller;
import snake.entities.Food;
import snake.entities.Ground;
import snake.entities.Snake;
import snake.listener.GameListener;
import snake.util.Global;
import snake.view.GamePanel;



/**
 * 主界面, 实现了 GameListener 接口
 */
public class MainFrame extends JFrame implements GameListener {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		try {
			MainFrame frame = new MainFrame(new Controller(new Snake(),
					new Food(), new Ground(), new GamePanel(), new JLabel()));
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private final GameOptionPanel optionPanel;
	private final GamePanel gamePanel;
	
	
	private final JLabel infoLabel;
	private final Controller controller;
	private final Ground ground;
	/**
	 * Create the frame
	 */
	public MainFrame(Controller c) {
		super();
		this.controller = c;

		this.setTitle("ZL贪吃蛇ZL");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);

		int left = 10;
		optionPanel = new GameOptionPanel();

		/** =======* */
		gamePanel = c.getGamePanel();
		ground = c.getGround();
		c.getSnake();
		
	
		infoLabel = c.getGameInfoLabel() == null ? new JLabel() : c
				.getGameInfoLabel();
		c.setGameInfoLabel(infoLabel);
		
		this.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent arg0) {
				controller.pauseGame();
				if (optionPanel.getPauseButton().isEnabled())
					optionPanel.getPauseButton().setText("继续游戏");
			}
		});
		gamePanel.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				// controller.continueGame();
			}

			public void focusLost(FocusEvent arg0) {
				controller.pauseGame();
				if (optionPanel.getPauseButton().isEnabled())
					optionPanel.getPauseButton().setText("继续游戏");
			}
		});

		optionPanel.getNewGameButton().addActionListener(new ActionListener() {
			/**
			 * 开始游戏的按钮
			 */
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				if (controller.isPlaying()) {
					return;
				}

				controller.newGame();
			}
		});
		optionPanel.getStopGameButton().addActionListener(new ActionListener() {
			/**
			 * 停止游戏的按钮
			 */
			public void actionPerformed(ActionEvent e) {

				controller.stopGame();
			}
		});
		optionPanel.getPauseButton().setEnabled(false);
		optionPanel.getStopGameButton().setEnabled(false);

		optionPanel.getPauseButton().addActionListener(new ActionListener() {
			/**
			 * 暂停/继续游戏的按钮
			 */
			public void actionPerformed(ActionEvent e) {
				if (controller.isPausingGame()) {
					controller.continueGame();

				} else {
					controller.pauseGame();
				}
				if (controller.isPausingGame())
					optionPanel.getPauseButton().setText("继续游戏");
				else
					optionPanel.getPauseButton().setText("暂停游戏");
			}
		});
		// 显示得分统计，得分成绩标签及文本框
		
		
		
		
		//  显示网格控件的动作，加入动作侦听
		optionPanel.getCheckBox_drawGridding().addChangeListener(
				new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
					optionPanel.getCheckBox_drawGridding().isSelected();
					ground.setDrawGridding(
				optionPanel.getCheckBox_drawGridding().isSelected());
					}
				});
		/** ******************* */

		infoLabel.setBounds(10, 0, infoLabel.getSize().width - 10, infoLabel
				.getSize().height);
		gamePanel.setBounds(0, infoLabel.getSize().height,
				gamePanel.getSize().width, gamePanel.getSize().height);

		/**
		 * subPanel
		 */
		JPanel subPanel = new JPanel();
		subPanel.setLayout(null);
		subPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		subPanel.setFocusable(false);

		subPanel.setSize(gamePanel.getSize().width + 1,
				infoLabel.getSize().height + gamePanel.getSize().height + 1);
		subPanel.setBounds(left, 5, subPanel.getSize().width, subPanel
				.getSize().height);

		subPanel.add(infoLabel);
		subPanel.add(gamePanel);

		optionPanel.setBounds(left, subPanel.getSize().height + 10, optionPanel
				.getSize().width, optionPanel.getSize().height);

		/**
		 * 游戏操作说明一栏的
		 */
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		infoPanel.setLayout(null);
		infoPanel.setBounds(left + optionPanel.getSize().width + 5, subPanel
				.getSize().height + 10, gamePanel.getSize().width
				- optionPanel.getSize().width - 5 + 1,
				optionPanel.getSize().height);

		final JLabel infoTitleLable = new JLabel();
		infoTitleLable.setFont(new Font("黑体", Font.PLAIN, 20));
		infoTitleLable.setText(Global.TITLE_LABEL_TEXT);
		infoTitleLable.setBounds(10, 5, infoPanel.getSize().width - 10, 20);

		final JTextArea infoTextArea = new JTextArea();
		infoTextArea.setFont(new Font("宋体", Font.PLAIN, 15));
		infoTextArea.setText(Global.INFO_LABEL_TEXT);
		infoTextArea.setFocusable(false);
		infoTextArea.setBackground(this.getBackground());
		infoTextArea.setBounds(10, 30, infoPanel.getSize().width - 20,
				infoPanel.getSize().height - 50);

		infoPanel.add(infoTitleLable);
		infoPanel.add(infoTextArea);
		optionPanel.getCheckBox_drawGridding().setFocusable(false); //选中显示网格，动态面板继续
		
		this
				.setSize(
						subPanel.getSize().width > optionPanel.getSize().width ? gamePanel
								.getSize().width
								+ 2 * left + 8
								: optionPanel.getSize().width + 2 * left + 8,
						subPanel.getSize().height + 20/* 边框 */
								+ optionPanel.getSize().height + 30);
		/* 让窗口居中 */
		this.setLocation(this.getToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, this.getToolkit().getScreenSize().height
				/ 2 - this.getHeight() / 2);

		/* 添加监听器 */
		gamePanel.addKeyListener(controller);
		this.addKeyListener(controller);
		controller.addGameListener(this);

		this.getContentPane().add(subPanel);
		this.getContentPane().add(optionPanel);
		this.getContentPane().add(infoPanel);
	}

	public void gameOver() {
		// TODO Auto-generated method stub

		optionPanel.getPauseButton().setEnabled(false);

		optionPanel.getStopGameButton().setEnabled(false);
		optionPanel.getNewGameButton().setEnabled(true);
		optionPanel.getPauseButton().setText("暂停/继续");
	}

	public void gameStart() {

		// TODO Auto-generated method stub

		optionPanel.getPauseButton().setEnabled(true);
		optionPanel.getNewGameButton().setEnabled(false);
		optionPanel.getStopGameButton().setEnabled(true);
	}

	public void gameContinue() {
		// TODO Auto-generated method stub
		optionPanel.getPauseButton().setText("暂停游戏");
	}

	public void gamePause() {
		// TODO Auto-generated method stub
		optionPanel.getPauseButton().setText("继续游戏");
	}
}
