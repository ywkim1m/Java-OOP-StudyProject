package project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GUIPay implements ActionListener {


	JFrame frame;
	JButton payBtn, cancelBtn;
	JLabel tableId, amount;
	
	Restaurant rest = new Restaurant();
	
	// order 테이블 생성
	String colOrders[] = { "이름", "가격", "수량", "주문 금액" };
	DefaultTableModel orderModel = new DefaultTableModel(colOrders, 0);
	JTable table = new JTable(orderModel);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIPay window = new GUIPay();
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
	public GUIPay() {}
	public GUIPay(String id) {
		//initialize();
		
		// 파일 복원
		try {
			FileInputStream in1 = new FileInputStream("restaurant.dat");
			ObjectInputStream in2 = new ObjectInputStream(in1);
											
			rest.readAll(in2);
											
			in2.close();
			in1.close();
						
			int index = rest.searchTable(id);
			
			DefaultTableModel orderModel = new DefaultTableModel(colOrders, 0);
			
			// 정보 복원	
			for (int i = 0; i < rest.tableList.get(index).orderList.size(); i++) {
				String[] arr = new String[4];
				arr[0] = rest.tableList.get(index).orderList.get(i).name;
				arr[1] = Integer.toString(rest.tableList.get(index).orderList.get(i).cost);
				arr[2] = Integer.toString(rest.tableList.get(index).orderList.get(i).orderQuantity);
				arr[3] = Integer.toString(
						rest.tableList.get(index).orderList.get(i).cost
						* rest.tableList.get(index).orderList.get(i).orderQuantity);
				//DefaultTableModel orderModel = (DefaultTableModel) table.getModel();
				orderModel.addRow(arr);
			}
			
			table.setModel(orderModel);
		}
		catch (FileNotFoundException fnfe) {
			//System.out.println("파일을 찾을 수 없습니다.");
			//System.out.println("최초 실행인 경우 1, 최초 실행이 아닌 경우 2를 입력해 주세요.");
			/*int num = scan.nextInt();
											
			switch(num) {
			case 1 :
				// 1. 최초 실행
				// 계속해서 프로그램 진행
				break;
											
			case 2 :
				// 2. 최초 실행이 아님
				System.out.println("파일을 찾을 수 없습니다. 프로그램을 종료합니다.");
				System.exit(0);
			}*/
			String exception0 = "파일을 찾을 수 없습니다.";
							
			GUIException guiException0 = new GUIException(exception0);
		}
			catch (IOException ioe) {
			//System.out.println("파일을 불러올 수 없습니다.");
			String exception1 = "파일을 불러올 수 없습니다.";
							
			GUIException guiException1 = new GUIException(exception1);
		}
		catch (Exception e) {
			//System.out.println(e.getMessage() + "입니다.");
			String exception2 = e.getMessage();
			GUIException guiException2 = new GUIException(exception2);
		}
				
				
		frame = new JFrame(id); // 이름 테이블 id로 변경
				
		// 프레임 크기 설정
		frame.setSize(600, 630);
				
		// 프레임을 화면 가운데에 배치
		frame.setLocationRelativeTo(null);
				
		// content pane 가져오기
		Container contentPane = frame.getContentPane();
		
		// 패널 생성
		JPanel mainPanel = new JPanel();
		// 패널 레이아웃 설정
		mainPanel.setLayout(new BorderLayout());
		
		// scrollPane 생성
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(450, 500));
		
		
		// 패널 생성
		JPanel sidePanel = new JPanel();
		// 패널 레이아웃 설정
		sidePanel.setLayout(null);
		sidePanel.setPreferredSize(new Dimension(450, 100));
		
		
		// 라벨 생성
		tableId = new JLabel("테이블 ID: " + id);
		tableId.setBounds(10, 10, 120, 40);
		amount = new JLabel("주문 금액: " + Integer.toString(rest.paySystem(id)));
		amount.setBounds(10, 40, 120, 40);
		
		// 버튼 생성
		payBtn = new JButton("결제");
		payBtn.setBounds(370, 40, 90, 30);
		cancelBtn = new JButton("취소");
		cancelBtn.setBounds(470, 40, 90, 30);
		
		payBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		sidePanel.add(tableId);
		sidePanel.add(amount);
		
		sidePanel.add(payBtn);
		sidePanel.add(cancelBtn);
		

		
		
		// mainPanel에 scrollPane 추가
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		// mainPanel에 sidePanel 추가
		mainPanel.add(sidePanel, BorderLayout.SOUTH);
		// contentPane에 mainPanel 추가
		contentPane.add(mainPanel);
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent w) {
		Object o = w.getSource();
		
		if (o == payBtn) {
			//int pay = rest.paySystem(id);
			frame.setVisible(false);
		}
		else if (o == cancelBtn) {
			frame.setVisible(false); // 수정
		}
	}


	/**
	 * Initialize the contents of the frame.
	 */
	/*private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
	}*/

}
