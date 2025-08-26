package project;

import java.awt.*;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPayment {

	JFrame frame;
	JLabel text1, text2, text3;

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIPayment window = new GUIPayment();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public GUIPayment() {
		//initialize();
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public GUIPayment(int pay) {
		frame = new JFrame("결제");
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
		
		text1 = new JLabel("결제할 금액은 ");
		//text2 = new JLabel(Integer.toString(pay));
		text2 = new JLabel(Integer.toString(pay));
		text3 = new JLabel("원입니다.");
		
		text1.setBounds(180, 60, 100, 50);
		text2.setBounds(200, 90, 50, 50);
		text3.setBounds(195, 120, 100, 50);
		
		JButton btn1 = new JButton("결제");
		JButton btn2 = new JButton("취소");
		
		btn1.setBounds(110, 200, 100, 30);
		btn2.setBounds(230, 200, 100, 30);
		
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(btn1);
		panel.add(btn2);
		
		contentPane.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/*private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/

}
