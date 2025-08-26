package project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class GUIOrder implements ActionListener {

	JFrame frame;
	JButton menuBtn, orderBtn, cancelBtn;
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
					GUIOrder window = new GUIOrder();
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
	public GUIOrder() {}
	public GUIOrder(String id) {
		//initialize();
		
		// 파일 복원
		try {
			FileInputStream in1 = new FileInputStream("restaurant.dat");
			ObjectInputStream in2 = new ObjectInputStream(in1);
											
			rest.readAll(in2);
											
			in2.close();
			in1.close();
						
			int index = rest.searchTable(id);
			
			// 정보 복원	
			for (int i = 0; i < rest.tableList.get(index).orderList.size(); i++) {
				String[] arr = new String[4];
				arr[0] = rest.tableList.get(index).orderList.get(i).name;
				arr[1] = Integer.toString(rest.tableList.get(index).orderList.get(i).cost);
				arr[2] = Integer.toString(rest.tableList.get(index).orderList.get(i).orderQuantity);
				arr[3] = Integer.toString(
						rest.tableList.get(index).orderList.get(i).cost
						* rest.tableList.get(index).orderList.get(i).orderQuantity);
				DefaultTableModel orderModel = (DefaultTableModel) table.getModel();
				orderModel.addRow(arr);
			}
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
		frame.setSize(1000, 630);
				
		// 프레임을 화면 가운데에 배치
		frame.setLocationRelativeTo(null);
				
		// content pane 가져오기
		Container contentPane = frame.getContentPane();
						
						
		// 패널 생성
		JPanel mainPanel = new JPanel();
		// 패널 레이아웃 설정
		mainPanel.setLayout(new FlowLayout());
		
		// scrollPane 생성
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(450, 500));
		
		// 패널 생성
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridBagLayout());
		menuPanel.setPreferredSize(new Dimension(500, 500));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		//gbc.insets = new Insets(10, 10, 10, 10);
		
		// tableList의 테이블을 버튼으로 추가
		for (int i = 0; i < rest.menuList.size(); i++) {
			menuBtn = new JButton(rest.menuList.get(i).name);
			menuBtn.setPreferredSize(new Dimension(100, 100));
			menuPanel.add(menuBtn, gbc);
			
			String orderName = rest.menuList.get(i).name;
			
			menuBtn.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2) { // 더블 클릭
	                	
						/*
						String arr[] = new String[2];
						arr[0] = fid.getText();
						arr[1] = fcapacity.getText();
						DefaultTableModel tModel = (DefaultTableModel) table1.getModel();
						tModel.addRow(arr);
						
						Table table = new Table(arr[0], Integer.parseInt(arr[1]), true);
						*/
						
	                	int orderCost = rest.getCost(orderName);
	                	
						String[] arr = new String[4];
						arr[0] = orderName;
						arr[1] = Integer.toString(orderCost);
						arr[2] = Integer.toString(1);
						arr[3] = Integer.toString(orderCost);
						DefaultTableModel orderModel = (DefaultTableModel) table.getModel();
						orderModel.addRow(arr);
						
						
						Order order = new Order(orderName, orderCost, 1);
						rest.orderSystem(id, order);
						
						//Table table = new Table(arr[0], Integer.parseInt(arr[1]), true);
	                }
	            }
	        });
					
			gbc.gridx++;
			if (gbc.gridx > 4) { // 열의 최대 개수 설정
			gbc.gridx = 0;
			gbc.gridy++;
			}
		}
		
		
		// 패널 생성
		JPanel sidePanel = new JPanel();
		// 패널 레이아웃 설정
		sidePanel.setLayout(new BorderLayout());
		sidePanel.setPreferredSize(new Dimension(980, 100));
		
		// 패널 생성
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(null);
		leftPanel.setPreferredSize(new Dimension(450, 90));
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(null);
		rightPanel.setPreferredSize(new Dimension (500, 90));
		
		
		// 라벨 생성
		tableId = new JLabel("테이블 ID: " + id);
		amount = new JLabel("주문 금액: " + Integer.toString(rest.paySystem(id)));
		tableId.setBounds(10, 0, 120, 40);
		amount.setBounds(10, 30, 120, 40);
		
		// leftPanel에 라벨 추가
		leftPanel.add(tableId);
		leftPanel.add(amount);
		
		
		// 버튼 생성
		orderBtn = new JButton("주문");
		cancelBtn = new JButton("취소");
		orderBtn.setBounds(290, 30, 90, 30);
		cancelBtn.setBounds(390, 30, 90, 30);
		
		orderBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		// rightPanel에 버튼 추가
		rightPanel.add(orderBtn);
		rightPanel.add(cancelBtn);
		
		
		// sidePanel에 패널 추가
		sidePanel.add(leftPanel, BorderLayout.CENTER);
		sidePanel.add(rightPanel, BorderLayout.EAST);
		// mainPanel에 scrollPane 추가
		mainPanel.add(scrollPane);
		// mainPanel에 menuPanel 추가
		mainPanel.add(menuPanel);
		// mainPanel에 sidePanel 추가
		mainPanel.add(sidePanel);
		// contentPane에 mainPanel 추가
		contentPane.add(mainPanel);
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent w) {
		Object o = w.getSource();
		
		if (o == orderBtn) {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/

}
