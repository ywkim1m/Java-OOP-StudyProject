package project;

import java.io.*;
import java.util.*;

public class Table implements Serializable {
	String id; // 테이블 id
	int capacity; // 수용 가능 인원수
	boolean state = true; // 이용 가능 여부(T/F)
	
	ArrayList<Order> orderList = new ArrayList<Order>(); // 테이블당 메뉴
	
	// constructor
	Table(String id, int capacity, boolean state) {
		this.id = id;
		this.capacity = capacity;
		this.state = state;
	}
	
	Table() {
		
	}
	
	// id에 대한 getter
	String getId() {
		return this.id;
	}
	
	// capacity에 대한 getter
	int getCapacity() {
		return this.capacity;
	}
	
	// state에 대한 getter
	boolean getState() {
		return this.state;
	}
	
	// 서치 함수 (order)
	int searchOrder(String orderName) {
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).name.equals(orderName)) {
				return i;
			}
		}
		return -1; // menuName이 존재하지 않으면 -1 리턴
	}
	
	// 주문 함수
	void addOrder(Order order) {
		int index = searchOrder(order.name);
		if (index == -1) { // 리스트에 추가하려는 주문이 없으면
			orderList.add(order); // orders 배열에 order 추가
		}
		else {
			// 이미 존재하는 order일 때 수량 더함
			orderList.get(index).orderQuantity += order.orderQuantity;
		}
		this.state = false;
	}
	
	// 계산 함수
	int totalPay() {
		int cost = 0;
		for (int i = 0; i < orderList.size(); i++) {
			cost += orderList.get(i).pay();
			// 오더의 수량을 0으로 초기화
			orderList.get(i).orderQuantity = 0;
		}
		return cost; // 가격 리턴
	}
	
	// equals 재정의
	public boolean equals(Object obj) {
		return this.id == ((Table)obj).id; // 테이블 id가 같으면 true 리턴
	}
	
	// 테이블 저장 함수(Object)
	public void writeTable(ObjectOutputStream out) throws Exception {
		//out.writeInt(orderList.size());
		out.writeObject(orderList);
	}
	
	// 테이블 복원 함수(Object)
	public void readTable(ObjectInputStream in) throws Exception {
		try {
			orderList = (ArrayList<Order>) in.readObject();
		}
		catch (Exception e) {
			throw new Exception("파일 읽기 불가능");
		}
	}
	
	// 테이블 저장 함수(Data)
	public void writeTable(DataOutputStream out) throws Exception {
		try {
			out.writeUTF(id);
			out.writeInt(capacity);
			out.writeBoolean(state);
			
			out.writeInt(orderList.size()); // 오더수 출력
			for (int i = 0; i < orderList.size(); i++) {
				orderList.get(i).writeOrder(out);
			}
		}
		catch (Exception e) {
			throw new Exception("파일 출력 불가능");
		}
	}
	
	// 테이블 복원 함수(Data)
	public void readTable(DataInputStream in) throws Exception {
		try {
			this.id = in.readUTF();
			this.capacity = in.readInt();
			this.state = in.readBoolean();
			
			int orderCount = in.readInt();
			for (int i = 0; i < orderCount; i++) {
				Order order = new Order();
				order.readOrder(in);
				orderList.add(order);
			}
		}
		catch (Exception e) {
			throw new Exception("파일 읽기 불가능");
		}
	}
	
	// setTable()
	/*public Boolean setTable() {
		return false;
	}
	
	// resetTable()
	public Boolean resetTable() {
		if (orderList.size() == 0) {
			return true;
		}
		return false;
	}*/
}
