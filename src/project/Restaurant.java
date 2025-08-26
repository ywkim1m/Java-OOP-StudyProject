package project;

import java.io.*;
import java.util.*;

class AddException extends Exception implements Serializable {
	public AddException(String msg) {
		super(msg);
	}
}

public class Restaurant {
	ArrayList<Menu> menuList = new ArrayList<Menu>();
	ArrayList<Table> tableList = new ArrayList<Table>();
	
	int sales = 0; // 총 매출, getter 만들기
	
	// sales에 대한 getter
	int getSales() {
		return this.sales;
	}
	
	// 메뉴 추가
	void addMenu(Menu menu) throws AddException {
		int index = menuList.indexOf(menu);
		if (index == -1) {
			menuList.add(menu);
		}
		else {
			throw new AddException("존재하는 메뉴");
		}
	}
	
	// 메뉴 삭제
	void deleteMenu(Menu menu) throws AddException {
		int index = menuList.indexOf(menu);
		if (index != -1) {
			menuList.remove(index);
		}
		else {
			throw new AddException("존재하지 않는 메뉴");
		}
	}
	
	// 메뉴 삭제(String name)
	void deleteMenu(String name) throws AddException {
		int index = searchMenu(name);
		if (index != -1) {
			menuList.remove(index);
		}
		else {
			throw new AddException("존재하지 않는 메뉴");
		}
	}
	
	// 메뉴 서치 함수
	int searchMenu(String menuName) {
		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).name.equals(menuName)) {
				return i;
			}
		}
		return -1; // menuName이 존재하지 않으면 -1 리턴
	}
	
	// 테이블 추가
	void addTable(Table table) throws AddException {
		int index = tableList.indexOf(table);
		if (index == -1) {
			tableList.add(table);
		}
		else {
			throw new AddException("존재하는 테이블");
		}
	}
	
	// 테이블 삭제
	void deleteTable(Table table) throws AddException {
		int index = tableList.indexOf(table);
		if (index != -1) {
			tableList.remove(index);
		}
		else {
			throw new AddException("존재하지 않는 테이블");
		}
	}
	
	// 테이블 삭제(String id)
	void deleteTable(String tableId) throws AddException {
		int index = searchTable(tableId);
		if (index != -1) {
			tableList.remove(index);
		}
		else {
			throw new AddException("존재하지 않는 테이블");
		}
	}
	
	// 테이블 서치 함수
	int searchTable(String tableId) {
		for (int i = 0; i < tableList.size(); i++) {
			if (tableList.get(i).id.equals(tableId)) {
				return i;
			}
		}
		return -1; // tableId가 존재하지 않으면 -1 리턴
	}
	
	// 메뉴 리스트 리턴
	ArrayList<Menu> getMenu() {
		return menuList;
	}
	
	// 테이블 리스트 리턴
	ArrayList<Table> getTable() {
		return tableList;
	}
	
	// 메뉴 가격 리턴
	int getCost(String menuName) {
		int cost = menuList.get(searchMenu(menuName)).cost;
		
		return cost;
	}
	
	// 주문 함수
	void orderSystem(String id, Order order) {
		Table table = tableList.get(searchTable(id));
		table.addOrder(order);
	}
	
	// 계산 함수
	int paySystem(String id) {
		Table table = tableList.get(searchTable(id));
		int cost = table.totalPay();
		
		sales += cost; // 총 매출에 가격 더함
		
		table.state = true; // 테이블 이용 상태를 true로 변경
		
		return cost;
	}
	
	// 저장 함수(Object)
	public void saveAll(ObjectOutputStream out) throws Exception {
		try {
			out.writeObject(menuList);
			out.writeObject(tableList);
		
			for (int i = 0; i < tableList.size(); i++) {
				tableList.get(i).writeTable(out);
			}
		
			out.writeInt(sales);
		}
		catch (Exception e) {
			throw new Exception("파일 출력 불가능");
		}
	}
	
	public void readAll(ObjectInputStream in) throws Exception {
		try {
			menuList = (ArrayList <Menu>) in.readObject();
			tableList = (ArrayList <Table>) in.readObject();
			
			for (int i = 0; i < tableList.size(); i++) {
				tableList.get(i).readTable(in);
			}
			
			// 매출 복원
			sales = in.readInt();
		}
		catch (Exception e) {
			throw new Exception("파일 읽기 불가능");
		}
	}
	
	// 저장 함수(Data)
	public void saveAll(DataOutputStream out) throws Exception {
		try {
			// 메뉴 저장
			out.writeInt(menuList.size()); // 메뉴수 저장
			for (int i = 0; i < menuList.size(); i++) {
				menuList.get(i).writeMenu(out);
			}
			
			// 테이블 저장
			out.writeInt(tableList.size()); // 테이블수 저장
			for (int i = 0; i < tableList.size(); i++) {
				tableList.get(i).writeTable(out);
			}
			
			// 매출 저장
			out.writeInt(sales);
		}
		catch (Exception e) {
			throw new Exception("파일 출력 불가능");
		}
	}
	
	// 복원 함수(Data)
	public void readAll(DataInputStream in) throws Exception {
		try {
			// 메뉴 복원
			int menuCount = in.readInt();
			for (int i = 0; i < menuCount; i++) {
				Menu menu = new Menu();
				menu.readMenu(in);
				menuList.add(menu);
			}
			
			// 테이블 복원
			int tableCount = in.readInt();
			for (int i = 0; i < tableCount; i++) {
				Table table = new Table();
				table.readTable(in);
				tableList.add(table);
			}
			
			// 매출 복원
			this.sales = in.readInt();
		}
		catch (Exception e) {
			throw new Exception("파일 읽기 불가능");
		}
	}
	
}