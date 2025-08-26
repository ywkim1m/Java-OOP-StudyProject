package project;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import project.Menu;

public class GUIRestaurant implements ActionListener {

	JFrame frame;
	JButton orderBtn, payBtn, modeBtn, tableBtn;
	
	Restaurant rest = new Restaurant();
	
	int state; // main(0), 주문(1), 결제(2)
	
	// table 테이블 생성
	String colTables[] = { "테이블 ID", "수용 인원" };
	DefaultTableModel tableModel = new DefaultTableModel(colTables, 0);
	JTable table1 = new JTable(tableModel);
		
		
	// menu 테이블 생성
	String colMenus[] = { "이름", "가격" };
	DefaultTableModel menuModel = new DefaultTableModel(colMenus, 0);
	JTable table2 = new JTable(menuModel);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIRestaurant window = new GUIRestaurant();
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
	public GUIRestaurant() {
		//initialize();
		
		// 파일 복원
		try {
			FileInputStream in1 = new FileInputStream("restaurant.dat");
			ObjectInputStream in2 = new ObjectInputStream(in1);
									
			rest.readAll(in2);
									
			in2.close();
			in1.close();
					
			
			// 정보 복원	
			for (int i = 0; i < rest.tableList.size(); i++) {
				String[] arr = new String[2];
				arr[0] = rest.tableList.get(i).id;
				arr[1] = Integer.toString(rest.tableList.get(i).capacity);
				DefaultTableModel tModel = (DefaultTableModel) table1.getModel();
				tModel.addRow(arr);
			}

			for (int i = 0; i < rest.menuList.size(); i++) {
				String[] arr = new String[2];
				arr[0] = rest.menuList.get(i).name;
				arr[1] = Integer.toString(rest.menuList.get(i).cost);
				DefaultTableModel mModel = (DefaultTableModel) table2.getModel();
				mModel.addRow(arr);
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
		
		
		frame = new JFrame("매장 모드");
		//frame.setBounds(100, 100, 450, 300);
		
		// 프레임 크기 설정
		frame.setSize(900, 600);
		
		// 프레임을 화면 가운데에 배치
		frame.setLocationRelativeTo(null);
		
		// content pane 가져오기
		Container contentPane = frame.getContentPane();
				
				
		// 패널 생성
		JPanel mainPanel = new JPanel();
		// 패널 레이아웃 설정
		mainPanel.setLayout(new FlowLayout());
		
		// 패널 생성
		JPanel tablePanel = new JPanel();
		// 패널 레이아웃 설정
		tablePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		// 스크롤페인 생성
		JScrollPane scrollPane = new JScrollPane(tablePanel);
		scrollPane.setPreferredSize(new Dimension(700, 505));

		
		// 패널 생성
		JPanel sidePanel = new JPanel();
		// 패널 레이아웃 설정
		sidePanel.setLayout(new GridLayout(3, 1));
		
		// label 생성
		JLabel label = new JLabel("테이블");
		label.setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 가운데 정렬
	    label.setOpaque(true); // 불투명하게 설정
	    label.setBackground(SystemColor.controlHighlight);
	    
	    
	    // 패널 생성
	    JPanel titlePanel = new JPanel();
	    // 패널 레이아웃 설정
        titlePanel.setLayout(new BorderLayout());

        // titlePanel 크기 설정
        titlePanel.add(label, BorderLayout.CENTER);
        titlePanel.setPreferredSize(new Dimension(700, 40));

        // mainPanel에 titlePanel 추가
        mainPanel.add(titlePanel);
		
		
		
		// side 버튼 생성
		orderBtn = new JButton("주문");
		payBtn = new JButton("결제");
		modeBtn = new JButton("모드 변경");
		
		// 버튼에 이벤트 연결
		orderBtn.addActionListener(this);
		payBtn.addActionListener(this);
		modeBtn.addActionListener(this);
		
		sidePanel.add(orderBtn);
		sidePanel.add(payBtn);
		sidePanel.add(modeBtn);
		
		// tableList의 테이블을 버튼으로 추가
		for (int i = 0; i < rest.tableList.size(); i++) {
			tableBtn = new JButton(rest.tableList.get(i).id);
			tableBtn.setPreferredSize(new Dimension(115, 115));
			tablePanel.add(tableBtn, gbc);
			
			String tableId = rest.tableList.get(i).id;
			
			tableBtn.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	if (state == 1) {
		        		String id = tableId;
		        		GUIOrder guiOrder = new GUIOrder(id);
		        		guiOrder.frame.setVisible(true);
		        	}
		        	else if (state == 2) {
		        		String id = tableId;
		        		GUIPay guiPay = new GUIPay(id);
		        		guiPay.frame.setVisible(true);
		        	}
		        }
		    });
			
			gbc.gridx++;
	        if (gbc.gridx > 4) { // 열의 최대 개수 설정
	            gbc.gridx = 0;
	            gbc.gridy++;
	        }
		}
		
		//tableBtn.addActionListener(this);

		
		// scrollPane에 tablePanel을 추가
		//scrollPane.add(tablePanel);
		// mainPanel에 scrollPane을 추가
		mainPanel.add(scrollPane);
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		
		// contentPane에 패널을 추가
		contentPane.add(mainPanel, BorderLayout.CENTER);
		contentPane.add(sidePanel, BorderLayout.EAST);
		
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent w) {
		Object o = w.getSource();
		
		
		if (o == orderBtn) {
			state = 1; // 주문 상태로 변경
			
		}
		
		else if (o == payBtn) {
			state = 2; // 결제 상태로 변경
		}
		
		else if (o == modeBtn) {
			// 관리자 모드로 변경
			GUIMain guiMain = new GUIMain();
			guiMain.frame.setVisible(true);
			this.frame.setVisible(false);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/*private void initialize() {
		
	}*/

}
