package project;
import java.util.*;
import java.io.*;

public class User implements Serializable {
	public static void main(String args[]) {
		Restaurant rest = new Restaurant();
		
		Scanner scan = new Scanner(System.in);
		
		// 정보 복구
		try {
			FileInputStream in1 = new FileInputStream("restaurant.dat");
			ObjectInputStream in2 = new ObjectInputStream(in1);
			
			rest.readAll(in2);
			
			in2.close();
			in1.close();
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("파일을 찾을 수 없습니다.");
			System.out.println("최초 실행인 경우 1, 최초 실행이 아닌 경우 2를 입력해 주세요.");
			int num = scan.nextInt();
			
			switch(num) {
			case 1 :
				// 1. 최초 실행
				// 계속해서 프로그램 진행
				break;
			
			case 2 :
				// 2. 최초 실행이 아님
				System.out.println("파일을 찾을 수 없습니다. 프로그램을 종료합니다.");
				System.exit(0);
			}
		}
		catch (IOException ioe) {
			System.out.println("파일을 불러올 수 없습니다.");
		}
		catch (Exception e) {
			System.out.println(e.getMessage() + "입니다.");
		}
		
		while(true) {
			System.out.println("모드를 설정해 주세요.");
			System.out.println("1. 관리자");
			System.out.println("2. 매니저");
			System.out.println("3. 종료");
			
			int mode = scan.nextInt(); // 번호 입력받음
			
			switch(mode) {
			// 1. 관리자
			case 1:
				System.out.println("모드를 설정해 주세요");
				System.out.println("1. 메뉴 추가");
				System.out.println("2. 메뉴 삭제");
				System.out.println("3. 테이블 추가");
				System.out.println("4. 테이블 삭제");
				System.out.println("5. 정보 저장");
				System.out.println("6. 매출 확인");
				System.out.println("7. 종료");
				
				int modeF = scan.nextInt();
				switch(modeF) {
				case 1: // 메뉴 추가
					try {
						System.out.println("추가할 메뉴 이름을 입력하세요: ");
						String menuName = scan.next();
						System.out.println("가격을 입력하세요: ");
						int menuCost = scan.nextInt();
						
						Menu menu = new Menu(menuName, menuCost);
						rest.addMenu(menu);
						System.out.println(menuName + "을(를) 메뉴에 추가하였습니다.");
						
						break;
					} catch (AddException e) {
						System.out.println(e.getMessage() + "입니다.");
					} catch (Exception ArrayIndexOutOfBoundsException) {
						System.out.println("메뉴를 더 추가할 수 없습니다.");
					} 
					continue;
				
				case 2: // 메뉴 삭제
					try {
						System.out.println("삭제할 메뉴 이름을 입력하세요: ");
						String deleteMenuName = scan.next();
			
						rest.deleteMenu(deleteMenuName);
						System.out.println(deleteMenuName + "을(를) 메뉴에서 삭제하였습니다.");
						break;
					} catch (AddException e) {
						System.out.println(e.getMessage() + "입니다.");
					}
					continue;
					
				case 3:
					try {
						// 테이블 추가
						System.out.println("테이블 id를 입력하세요: ");
						String tableId = scan.next();
						
						System.out.println("인원수를 입력하세요: ");
						int number = scan.nextInt();
						
						Table table = new Table(tableId, number, true);
						rest.addTable(table);

						System.out.println(tableId + " 테이블을 추가하였습니다.");
						break;
					} catch (AddException e) {
						System.out.println(e.getMessage() + "입니다.");
					} catch (Exception ArrayIndexOutOfBoundsException) {
						System.out.println("테이블을 더 추가할 수 없습니다.");
					}
					continue;
					
				case 4:
					// 테이블 삭제
					try {
						System.out.println("삭제할 테이블 id를 입력하세요: ");
						String deleteTableId = scan.next();
						
						rest.deleteTable(deleteTableId);
						System.out.println(deleteTableId + " 테이블을 삭제하였습니다.");
						break;
					} catch (AddException e) {
						System.out.println(e.getMessage() + "입니다.");
					}
					continue;
					
				case 5:
					// 정보 저장
					/*try {
						FileOutputStream out1 = new FileOutputStream("restaurant.dat");
						ObjectOutputStream out2 = new ObjectOutputStream(out1);
						
						//rest.saveAll(out2, rest);
						out2.writeObject(rest);
						
						out2.close();
						out1.close();
					}*/
					try (FileOutputStream out1 = new FileOutputStream("restaurant.dat");
						     ObjectOutputStream out2 = new ObjectOutputStream(out1)) {
						//out2.writeObject(rest);
						rest.saveAll(out2);
					}
					catch (FileNotFoundException fnfe) {
						System.out.println("파일이 존재하지 않습니다.");
					}
					catch (IOException ioe) {
						System.out.println("파일로 출력할 수 없습니다.");
					}
					catch (Exception e) {
						System.out.println(e.getMessage() + "입니다.");
					}
					
					continue;
					
				case 6:
					// 매출 확인
					int sales = rest.getSales();
					System.out.println(sales + " 원");
					continue;
					
				case 7:
					// 종료
					break;
				}
				break;
			
			// 2. 매니저	
			case 2:
				System.out.println("모드를 설정해 주세요");
				System.out.println("1. 메뉴 출력");
				System.out.println("2. 테이블 출력");
				System.out.println("3. 주문");
				System.out.println("4. 결제");
				System.out.println("5. 종료");
				
				int modeS = scan.nextInt();
				
				switch(modeS) {
				case 1:
					ArrayList<Menu> menuList = rest.getMenu();
					for (int i = 0; i < menuList.size(); i++) {
						System.out.println("이름: " + menuList.get(i).name +
								", 가격: " + menuList.get(i).cost);
					}
					continue;
					
				case 2:
					ArrayList<Table> tableList = rest.getTable();
					for (int i = 0; i < tableList.size(); i++) {
						System.out.println("ID: " + tableList.get(i).id +
								", 인원: " + tableList.get(i).capacity +
								", 이용 가능 여부: " + tableList.get(i).state);
					}
					continue;
					
				case 3:
					// 3. 주문
					try {
						System.out.println("테이블 id를 입력하세요: ");
					
						String orderId = scan.next();
					
						System.out.println("주문할 메뉴를 입력하세요: ");
						String orderName = scan.next();
					
						System.out.println("수량을 입력하세요: ");
						int orderNumber = scan.nextInt();
					
						int orderCost = rest.getCost(orderName);
						Order order = new Order(orderName, orderCost, orderNumber);
						rest.orderSystem(orderId, order);
					}
					catch (Exception e) {
						System.out.println("잘못된 입력입니다.");
					}
					continue;
					
				case 4:
					// 4. 결제
					System.out.println("테이블 id를 입력하세요: ");
					String payId = scan.next();
					
					int pay = rest.paySystem(payId);
					System.out.println(pay + "원을 결제하였습니다.");
					
					continue;
					
				case 5:
					// 5. 종료
					break;
				}
			// 3. 종료
			case 3:
				System.exit(0);
			}
		}
	}
}
