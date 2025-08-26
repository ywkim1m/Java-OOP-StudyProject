package project;

import java.io.*;

public class Order extends Menu implements Serializable{
	int orderQuantity; // 주문 수량
	
	// constructor
	Order(String name, int cost, int orderQuantity) {
		super(name, cost);
		this.orderQuantity = orderQuantity;
	}
	
	Order() {
		
	}
	
	// pay 함수
	int pay() {
		return this.cost * this.orderQuantity;
	}
	
	// equals 재정의
	public boolean equals(Object obj) {
		return this.name == ((Order)obj).name; // order name이 같으면 true 리턴
	}
	
	// 오더 저장 함수(Data)
	public void writeOrder(DataOutputStream out) throws Exception {
		try {
			out.writeUTF(name);
			out.writeInt(cost);
			out.writeInt(orderQuantity);
		}
		catch (Exception e) {
			throw new Exception("파일 출력 불가능");
		}
	}
	
	// 오더 복원 함수(Data)
	public void readOrder(DataInputStream in) throws Exception {
		try {
			this.name = in.readUTF();
			this.cost = in.readInt();
			this.orderQuantity = in.readInt();
		}
		catch (Exception e) {
			throw new Exception("파일 읽기 불가능");
		}
	}
	
	
}
