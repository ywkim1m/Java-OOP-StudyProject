package project;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class GUIRest implements ActionListener {

	JFrame frame;
	JButton checkBtn, payBtn, saveBtn, modeBtn;
	JToggleButton tableBtn, orderBtn, menuBtn;
	JSpinner spinner;
	
	Restaurant rest = new Restaurant();
	
	int state; // main(0), 주문(1), 결제(2)
	String id;
	
	// table 테이블 생성
	String colTables[] = { "테이블 ID", "수용 인원" };
	DefaultTableModel tableModel = new DefaultTableModel(colTables, 0);
	JTable tTable = new JTable(tableModel);

		
	// menu 테이블 생성
	String colMenus[] = { "이름", "가격" };
	DefaultTableModel menuModel = new DefaultTableModel(colMenus, 0);
	JTable mTable = new JTable(menuModel);
	
	// order 테이블 생성
	String colOrders[] = { "이름", "수량", "주문 금액" };
	DefaultTableModel orderModel = new DefaultTableModel(colOrders, 0);
	JTable oTable = new JTable(orderModel);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIRest window = new GUIRest();
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
	public GUIRest() {
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
				DefaultTableModel tModel = (DefaultTableModel) tTable.getModel();
				tModel.addRow(arr);
				

			}

			for (int i = 0; i < rest.menuList.size(); i++) {
				String[] arr = new String[2];
				arr[0] = rest.menuList.get(i).name;
				arr[1] = Integer.toString(rest.menuList.get(i).cost);
				DefaultTableModel mModel = (DefaultTableModel) mTable.getModel();
				mModel.addRow(arr);
			}
			
			/*
			 * int index = rest.searchTable(id);
			 * 
			 * // 정보 복원 for (int i = 0; i < rest.tableList.get(index).orderList.size(); i++)
			 * { String[] arr = new String[4]; arr[0] =
			 * rest.tableList.get(index).orderList.get(i).name; arr[1] =
			 * Integer.toString(rest.tableList.get(index).orderList.get(i).orderQuantity);
			 * arr[2] = Integer.toString( rest.tableList.get(index).orderList.get(i).cost
			 * rest.tableList.get(index).orderList.get(i).orderQuantity); DefaultTableModel
			 * orderModel = (DefaultTableModel) table.getModel(); orderModel.addRow(arr);
			 */
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
		
		// 프레임 크기 설정
		frame.setSize(1100, 720);
		
		// 프레임을 화면 가운데에 배치
		frame.setLocationRelativeTo(null);
		
		// content pane 가져오기
		Container contentPane = frame.getContentPane();
				
				
		// 패널 생성
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		// 패널 생성
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		//panel1.setPreferredSize(new Dimension(360, 360));
		panel1.setBounds(720, 0, 360, 360);
		
		// label 생성
		JLabel oLabel = new JLabel("주문 내역");
		oLabel.setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 가운데 정렬
	    oLabel.setOpaque(true); // 불투명하게 설정
	    oLabel.setBackground(SystemColor.controlHighlight);
	    
	    // 패널 생성
	    JPanel orderTitle = new JPanel();
        orderTitle.setLayout(new BorderLayout());

        // tableTitle 크기 설정
        orderTitle.add(oLabel, BorderLayout.CENTER);
        orderTitle.setBounds(5, 5, 350, 40);
        
		// scrollPane 생성
		JScrollPane oScrollPane = new JScrollPane(oTable);
		oScrollPane.setBounds(5, 50, 350, 305);

        // mainPanel에 tableTitle 추가
        panel1.add(orderTitle);
        panel1.add(oScrollPane);

		

		// 패널 생성
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 0, 720, 360);
		
		// label 생성
		JLabel tLabel = new JLabel("테이블");
		tLabel.setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 가운데 정렬
	    tLabel.setOpaque(true); // 불투명하게 설정
	    tLabel.setBackground(SystemColor.controlHighlight);
	    
	    // 패널 생성
	    JPanel tableTitle = new JPanel();
        tableTitle.setLayout(new BorderLayout());

        // tableTitle 크기 설정
        tableTitle.add(tLabel, BorderLayout.CENTER);
        tableTitle.setBounds(5, 5, 710, 40);
        
		// 패널 생성
		JPanel tablePanel = new JPanel();
		// 패널 레이아웃 설정
		tablePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);
		
		//tablePanel.setBounds(5, 15, 710, 650);
		
		// 스크롤페인 생성
		JScrollPane tScrollPane = new JScrollPane(tablePanel);
		tScrollPane.setBounds(5, 50, 710, 305);
		
		// tableList의 테이블을 버튼으로 추가
		for (int i = 0; i < rest.tableList.size(); i++) {
			tableBtn = new JToggleButton(rest.tableList.get(i).id);
			tableBtn.setText("<html>" + rest.tableList.get(i).id + "<br>"
					+ rest.tableList.get(i).capacity + "명<br>"
					+ rest.tableList.get(i).state + "</html>");
			tableBtn.setPreferredSize(new Dimension(115, 115));
        	tableBtn.setBackground(Color.WHITE);
			//tableBtn.setBackground(Color.BLUE);
			tablePanel.add(tableBtn, gbc);
			
			String tableId = rest.tableList.get(i).id;
			
			tableBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	String id = tableId;
	            	tLabel.setText(id);
	            	
	    			//tableBtn.setBackground(Color.RED);
	            }
		    });
			
			tableBtn.addActionListener(this);
			
			gbc.gridx++;
	        if (gbc.gridx > 5) { // 열의 최대 개수 설정
	            gbc.gridx = 0;
	            gbc.gridy++;
	        }
		}

        // mainPanel에 tableTitle 추가
        panel2.add(tableTitle);
        panel2.add(tScrollPane);
        //panel2.add(tablePanel);
		
		
		
		// 패널 생성
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(0, 360, 720, 360);
		
		// label 생성
		JLabel mLabel = new JLabel("메뉴");
		mLabel.setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 가운데 정렬
	    mLabel.setOpaque(true); // 불투명하게 설정
	    mLabel.setBackground(SystemColor.controlHighlight);
	    
	    // 패널 생성
	    JPanel menuTitle = new JPanel();
        menuTitle.setLayout(new BorderLayout());

        // tableTitle 크기 설정
        menuTitle.add(mLabel, BorderLayout.CENTER);
        menuTitle.setBounds(5, 5, 710, 40);
        
		// 패널 생성
		JPanel menuPanel = new JPanel();
		// 패널 레이아웃 설정
		menuPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		gbc1.insets = new Insets(0, 0, 0, 0);
		
		// 스크롤페인 생성
		JScrollPane mScrollPane = new JScrollPane(menuPanel);
		mScrollPane.setBounds(5, 50, 710, 210);
		
		// tableList의 테이블을 버튼으로 추가
		for (int i = 0; i < rest.menuList.size(); i++) {
			menuBtn = new JToggleButton(rest.menuList.get(i).name);
			menuBtn.setText("<html>" + rest.menuList.get(i).name + "<br>"
					+ rest.menuList.get(i).cost + "원</html>");
			menuBtn.setPreferredSize(new Dimension(115, 115));
        	menuBtn.setBackground(Color.WHITE);
			
			menuPanel.add(menuBtn, gbc1);
			
			String orderName = rest.menuList.get(i).name;
			
			menuBtn.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2) { // 더블 클릭
						
	                	int orderCost = rest.getCost(orderName);
	                	
						String[] arr = new String[4];
						arr[0] = orderName;
						arr[1] = Integer.toString(1);
						arr[2] = Integer.toString(orderCost);
						DefaultTableModel orderModel = (DefaultTableModel) oTable.getModel();
						orderModel.addRow(arr);
						
						
						Order order = new Order(orderName, orderCost, 1);
						rest.orderSystem(id, order);
	                }
	            }
	        });
					
			gbc1.gridx++;
			if (gbc1.gridx > 5) { // 열의 최대 개수 설정
				gbc1.gridx = 0;
				gbc1.gridy++;
			}
		}
		
		// 라벨
		JLabel orderLabel = new JLabel("수량");
		orderLabel.setBounds(10, 275, 100, 20);
		
		// 스피너
		SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
		spinner = new JSpinner(spinnerModel);
		spinner.setBounds(50, 275, 100, 25);
		
		// 버튼
		orderBtn = new JToggleButton("주문");
		orderBtn.setBounds(615, 270, 100, 40);
		
        // mainPanel에 tableTitle 추가
        panel3.add(menuTitle);
        panel3.add(mScrollPane);
        panel3.add(orderLabel);
        panel3.add(spinner);
        panel3.add(orderBtn);
		
		
		
		// 패널 생성
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(720, 360, 360, 360);
		
		// label 생성
		JLabel sLabel = new JLabel("합계: " + "원");
		sLabel.setHorizontalAlignment(SwingConstants.RIGHT); // 텍스트 오른쪽 정렬
	    sLabel.setOpaque(true); // 불투명하게 설정
	    //sLabel.setBackground(SystemColor.controlHighlight);
	    
	    // 패널 생성
	    JPanel sumTitle = new JPanel();
        sumTitle.setLayout(new BorderLayout());

        // tableTitle 크기 설정
        sumTitle.add(sLabel, BorderLayout.CENTER);
        sumTitle.setBounds(0, 0, 350, 40);
        
        // 버튼 생성
        checkBtn = new JButton("체크인");
        checkBtn.setBounds(10, 50, 340, 50);
        
        payBtn = new JButton("결제");
        payBtn.setBounds(10, 110, 340, 50);
        
        saveBtn = new JButton("저장");
        saveBtn.setBounds(10, 200, 340, 50);
        
        modeBtn = new JButton("관리자모드");
        modeBtn.setBounds(10, 260, 340, 50);

        panel4.add(sumTitle);
        panel4.add(checkBtn);
        panel4.add(payBtn);
        panel4.add(saveBtn);
        panel4.add(modeBtn);
        
        checkBtn.addActionListener(this);
        payBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        modeBtn.addActionListener(this);
		
		
		
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		
		contentPane.add(mainPanel);
	}
	
	public void actionPerformed(ActionEvent w) {
		Object o = w.getSource();
		
		if (o == saveBtn) {
			try (FileOutputStream out1 = new FileOutputStream("restaurant.dat");
				     ObjectOutputStream out2 = new ObjectOutputStream(out1)) {
				//out2.writeObject(rest);
				rest.saveAll(out2);
			}
			catch (FileNotFoundException fnfe) {
				//System.out.println("파일이 존재하지 않습니다.");
				String exception9 = "파일이 존재하지 않습니다.";
				GUIException guiException9 = new GUIException(exception9);
			}
			catch (IOException ioe) {
				//System.out.println("파일로 출력할 수 없습니다.");
				String exception10 = "파일로 출력할 수 없습니다.";
				GUIException guiException10 = new GUIException(exception10);
			}
			catch (Exception e) {
				//System.out.println(e.getMessage() + "입니다.");
				String exception11 = e.getMessage();
				GUIException guiException11 = new GUIException(exception11);
			}
		}
		else if (o == modeBtn) {
			// 관리자 모드로 변경
			GUIMain guiMain = new GUIMain();
			guiMain.frame.setVisible(true);
			this.frame.setVisible(false);
		}
		
		else if (o == tableBtn) {
			tableBtn.setBackground(Color.RED);
			//this.frame.repaint();
		}
		
		else if (o == checkBtn) {
			int index = rest.tableList.indexOf(id);
			//rest.tableList.get(index).state = false;
		}
		
		else if (o == payBtn) {		
			//GUIPayment guiPayment = new GUIPayment(rest.paySystem(id));
			GUIPayment guiPayment = new GUIPayment(10000);
			guiPayment.frame.setVisible(true);
			this.frame.setVisible(false);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/*
	 * private void initialize() { frame = new JFrame(); frame.setBounds(100, 100,
	 * 450, 300); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); }
	 */

}
