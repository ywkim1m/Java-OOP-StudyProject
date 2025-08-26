package project;

import java.util.*;

import java.awt.EventQueue;
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

import javax.swing.JFrame;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

// 익셉션 이름 수정

public class GUIMain implements ActionListener {

	JFrame frame;
	JButton tableAddBtn, tableDeleteBtn, menuAddBtn, menuDeleteBtn, saveBtn, salesBtn, modeBtn;
	JLabel name, cost, id, capacity;
	JTextField fname, fcost, fid, fcapacity;
	
	Restaurant rest = new Restaurant();
	
	
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
					GUIMain window = new GUIMain();
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
	public GUIMain() {
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
		
		
		
		
		frame = new JFrame("관리자 모드");
		//frame.setBounds(100, 100, 450, 300);
		
		// 프레임 크기 설정
		frame.setSize(1100, 600);
		
		// 프레임을 화면 가운데에 배치
		frame.setLocationRelativeTo(null);
				
		// content pane 가져오기
		Container contentPane = frame.getContentPane();
				
				
		// 패널 생성
		JPanel panel0 = new JPanel();
		// 패널 레이아웃 설정
		panel0.setLayout(new FlowLayout());
				
				
		// 패널 생성
		JPanel panel1 = new JPanel();
		// 패널 레이아웃 설정
		panel1.setLayout(new GridLayout(1, 2));


		
		
		// panel1에 테이블 추가
		panel1.add(new JScrollPane(table1));
				
				
				
				

		
		// panel1에 테이블 추가
		panel1.add(new JScrollPane(table2));
		
		
		

				
				
				
		// 패널 생성
		JPanel panel2 = new JPanel();
		// 패널 레이아웃 설정
		panel2.setLayout(null);
		// 패널 크기 설정
		panel2.setPreferredSize(new Dimension(900, 115));

		
		// table 라벨 생성
		id = new JLabel("테이블 ID");
		capacity = new JLabel("수용 인원");
		id.setBounds(10, 10, 80, 50);
		capacity.setBounds(10, 60, 80, 50);
		
		// table 텍스트 필드 생성
		fid = new JTextField();
		fcapacity = new JTextField();
		fid.setBounds(80, 20, 140, 30);
		fcapacity.setBounds(80, 70, 140, 30);
		
		// table 버튼 생성
		tableAddBtn = new JButton("테이블 추가");
		tableDeleteBtn = new JButton("테이블 삭제");
		tableAddBtn.setBounds(240, 10, 100, 100);
		tableDeleteBtn.setBounds(350, 10, 100, 100);
		
		
		// menu 라벨 생성
		name = new JLabel("메뉴 이름");
		cost = new JLabel("메뉴 가격");
		name.setBounds(460, 10, 80, 50);
		cost.setBounds(460, 60, 80, 50);
		
		// menu 텍스트 필드 생성
		fname = new JTextField();
		fcost = new JTextField();
		fname.setBounds(530, 20, 140, 30);
		fcost.setBounds(530, 70, 140, 30);
		
		// menu 버튼 생성
		menuAddBtn = new JButton("메뉴 추가");
		menuDeleteBtn = new JButton("메뉴 삭제");
		menuAddBtn.setBounds(690, 10, 100, 100);
		menuDeleteBtn.setBounds(800, 10, 100, 100);
		
		
		// side 버튼 생성
		saveBtn = new JButton("저장");
		salesBtn = new JButton("매출 확인");
		modeBtn = new JButton("모드 변경");

		
		
		// 버튼에 이벤트 연결
		tableAddBtn.addActionListener(this);
		tableDeleteBtn.addActionListener(this);
				
		panel2.add(id);
		panel2.add(fid);
		panel2.add(capacity);
		panel2.add(fcapacity);
		panel2.add(tableAddBtn);
		panel2.add(tableDeleteBtn);

				
				
		// 버튼에 이벤트 연결
		menuAddBtn.addActionListener(this);
		menuDeleteBtn.addActionListener(this);
		
		panel2.add(name);
		panel2.add(cost);
		panel2.add(fname);
		panel2.add(fcost);
		panel2.add(menuAddBtn);
		panel2.add(menuDeleteBtn);

				
				
		// side 패널 생성
		JPanel sidePanel = new JPanel();
		// side 패널 레이아웃 설정
		sidePanel.setLayout(new GridLayout(3, 1));
		
		
		// 버튼에 이벤트 연결
		saveBtn.addActionListener(this);
		salesBtn.addActionListener(this);
		modeBtn.addActionListener(this);
				
		sidePanel.add(saveBtn);
		sidePanel.add(salesBtn);
		sidePanel.add(modeBtn);
		
		
				
		// panel을 panel0에 추가
		panel0.add(panel1, BorderLayout.CENTER);
		panel0.add(panel2, BorderLayout.SOUTH);
				
				
		// panel0을 contentPane에 추가
		contentPane.add(panel0, BorderLayout.CENTER);
		contentPane.add(sidePanel, BorderLayout.EAST);
		
	
				

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent w) {
		Object o = w.getSource();
		
		if (o == tableAddBtn) {
			String arr[] = new String[2];
			arr[0] = fid.getText();
			arr[1] = fcapacity.getText();
			DefaultTableModel tModel = (DefaultTableModel) table1.getModel();
			tModel.addRow(arr);
			
			Table table = new Table(arr[0], Integer.parseInt(arr[1]), true);
			
			try {
				rest.addTable(table);
			} catch (AddException e) {
				//System.out.println(e.getMessage() + "입니다.");
				String exception3 = e.getMessage();
				GUIException guiException3 = new GUIException(exception3);
			} catch (Exception ArrayIndexOutOfBoundsException) {
				//System.out.println("테이블을 더 추가할 수 없습니다.");
				String exception4 = "테이블을 더 추가할 수 없습니다.";
				GUIException guiException4 = new GUIException(exception4);
			}
		}
		
		else if (o == tableDeleteBtn) {
			int row = table1.getSelectedRow();
			DefaultTableModel tModel = (DefaultTableModel) table1.getModel();
			tModel.removeRow(row);
			
			try {
				rest.deleteTable(Integer.toString(row));
			} catch (AddException e) {
				//System.out.println(e.getMessage() + "입니다.");
				String exception5 = e.getMessage();
				GUIException guiException5 = new GUIException(exception5);
			}
		}
		
		else if (o == menuAddBtn) {
			String arr[] = new String[2];
			arr[0] = fname.getText();
			arr[1] = fcost.getText();
			DefaultTableModel mModel = (DefaultTableModel) table2.getModel();
			mModel.addRow(arr);
			
			Menu menu = new Menu(arr[0], Integer.parseInt(arr[1]));
			
			try {
				rest.addMenu(menu);
			} catch (AddException e) {
				//System.out.println(e.getMessage() + "입니다.");
				String exception6 = e.getMessage();
				GUIException guiException6 = new GUIException(exception6);
			} catch (Exception ArrayIndexOutOfBoundsException) {
				//System.out.println("메뉴를 더 추가할 수 없습니다.");
				String exception7 = "메뉴를 더 추가할 수 없습니다.";
				GUIException guiException7 = new GUIException(exception7);
			} 
		}
		
		else if (o == menuDeleteBtn) {
			int row = table2.getSelectedRow();
			DefaultTableModel mModel = (DefaultTableModel) table2.getModel();
			mModel.removeRow(row);
			
			try {
				rest.deleteMenu(Integer.toString(row));
			} catch (AddException e) {
				//System.out.println(e.getMessage() + "입니다.");
				String exception8 = e.getMessage();
				GUIException guiException8 = new GUIException(exception8);
			}
		}
		
		else if (o == saveBtn) {
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
		
		else if (o == salesBtn) {
			int sales = rest.getSales();
			GUISales guiSales = new GUISales(sales);
		}
		
		else if (o == modeBtn) {
			// 매장 모드로 변경
			GUIRestaurant guiRestaurant = new GUIRestaurant();
			guiRestaurant.frame.setVisible(true);
			this.frame.setVisible(false);
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

	}


}
