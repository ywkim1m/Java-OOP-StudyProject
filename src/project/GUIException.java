package project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIException {

	JFrame frame;
	JLabel text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIException window = new GUIException();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIException() {}
	public GUIException(String exText) {
		//initialize();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);

		
		// 프레임을 화면 가운데에 배치
		frame.setLocationRelativeTo(null);
		
		// content pane 가져오기
		Container contentPane = frame.getContentPane();
				
		// 패널 생성
		JPanel panel = new JPanel();
		// 패널 레이아웃 설정
		panel.setLayout(new BorderLayout());
		
		text = new JLabel(exText);
		panel.add(text, BorderLayout.CENTER);
		
		contentPane.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	}

}
