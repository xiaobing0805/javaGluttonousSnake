package snake.game;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;



import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * ��Ϸ�����������
 * @param <JLabel>
 */
public class GameOptionPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;

	private final JButton newGameButton = new JButton(); //��ʼ��ť

	private final JButton stopGameButton = new JButton();//ֹͣ��ť

	private final JButton pauseButton = new JButton(); //��ͣ��ť
	
	private final JCheckBox checkBox_drawGridding = new JCheckBox(); //��ʾ����
	private JTextField scoreField;
	
	
	
	/**
	 * Create the panel
	 */
	public GameOptionPanel() {
		super();
		setSize(305, 185);
		setLayout(null);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		setFocusable(false);
		add(stopGameButton);
										
		stopGameButton.setText("ֹͣ��Ϸ");
										
		stopGameButton.setBounds(194, 136, 101, 23);
		stopGameButton.setFont(new Font("����", Font.PLAIN, 15));
		stopGameButton.setFocusable(false);
		add(newGameButton);
												
		newGameButton.setFont(new Font("����", Font.PLAIN, 15));
		newGameButton.setBounds(194, 25, 101, 23);
		newGameButton.setFocusable(false);
		newGameButton.setText("��ʼ��Ϸ");		
		add(pauseButton);
														
		pauseButton.setBounds(194, 79, 101, 23);
		pauseButton.setText("��ͣ��Ϸ");
		pauseButton.setFont(new Font("����", Font.PLAIN, 15));
		pauseButton.setFocusable(false);
		add(checkBox_drawGridding);
		checkBox_drawGridding.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent arg0) {
																	
				}
			});
		checkBox_drawGridding.setBounds(30, 102, 100, 30);
																
		checkBox_drawGridding.setText("��ʾ����");
		checkBox_drawGridding.setFont(new Font("����", Font.PLAIN, 15));
																
		JLabel scorelabel = new JLabel("�÷�ͳ�ƣ�");
		scorelabel.setFont(new Font("����", Font.BOLD, 16));
		scorelabel.setBounds(10, 25, 86, 23);
		add(scorelabel);
																
		scoreField = new JTextField();
		scoreField.setBounds(30, 58, 86, 23);
		add(scoreField);
		scoreField.setColumns(10);
	}

	public JFrame getFrame() { //��������
		return frame;
	}

	public void setFrame(JFrame frame) { 
		this.frame = frame;
	}
	
	public JCheckBox getCheckBox_drawGridding() {
		return checkBox_drawGridding;
	}
	
	public JButton getNewGameButton() {
		return newGameButton;
	}

	public JButton getPauseButton() {
		return pauseButton;
	}

	public JButton getStopGameButton() {
		return stopGameButton;
	}

	public Window getButton_griddingColor() {	
		return null;
	}
}
