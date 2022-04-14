package bank;

import java.util.Scanner;

public class AccountEx {
	private static Scanner sc = new Scanner(System.in);
	private static BankApplication ba = new BankApplication();

	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			System.out.println("=================================================");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.송금 | 6.종료");
			System.out.println("=================================================");
			System.out.print("선택 >>> ");

			int selectNo = sc.nextInt();

			switch (selectNo) {
			case 1:
				ba.createAccount();
				break;
			case 2:
				ba.accountList();
				break;
			case 3:
				ba.deposit();
				break;
			case 4:
				ba.withdraw();
				break;
			case 5:
				ba.transfer();
				break;
			case 6:
				run = false;
				break;
			default:
				System.out.println("1~6사이의 숫자를 선택해주세요.");
			}
		}
		System.out.println("프로그램 종료");
	}
}
