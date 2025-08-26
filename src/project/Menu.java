package project;
import java.io.*;

public class Menu implements Serializable {
	String name; // 메뉴 이름
	int cost; // 메뉴 가격
		
	// constructor
	Menu(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	Menu() {
		
	}

	// name에 대한 getter
	String getName() {
		return this.name;
	}
	
	// price에 대한 getter
	int getPrice(){
		return this.cost;
	}
	
	// equals 재정의
	public boolean equals(Object obj) {
		return this.name == ((Menu)obj).name; // 메뉴 name이 같으면 true 리턴
	}
	
	// 메뉴 저장 함수(Data)
	public void writeMenu(DataOutputStream out) throws Exception {
		try {
			out.writeUTF(name);
			out.writeInt(cost);
		}
		catch (Exception e) {
			throw new Exception("파일로 출력 불가능");
		}
	}
	
	// 메뉴 복원 함수(Data)
	public void readMenu(DataInputStream in) throws Exception {
		try {
			this.name = in.readUTF();
			this.cost = in.readInt();
		}
		catch (Exception e) {
			throw new Exception("파일 읽기 불가능");
		}
	}
	
	
	
}
