package project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUISales {

	JFrame frame;
	JLabel text1, text2, text3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISales window = new GUISales();
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
	public GUISales() {}
	public GUISales(int sales) {
		//initialize();
		frame = new JFrame("매출");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 프레임을 화면 가운데에 배치
		frame.setLocationRelativeTo(null);
		
		// content pane 가져오기
		Container contentPane = frame.getContentPane();
				
		// 패널 생성
		JPanel panel = new JPanel();
		// 패널 레이아웃 설정
		panel.setLayout(null);
		
		text1 = new JLabel("매출은 ");
		text2 = new JLabel(Integer.toString(sales));
		text3 = new JLabel("원입니다.");
		
		text1.setBounds(200, 60, 50, 50);
		text2.setBounds(200, 90, 50, 50);
		text3.setBounds(195, 120, 100, 50);
		
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		
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
