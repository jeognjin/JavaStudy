package bank;

import java.util.Scanner;

public class BankApplication {
	private static Account[] accountArray = new Account[100];
	private static Scanner sc = new Scanner(System.in);

	// 계좌생성
	public void createAccount() {
		System.out.print("생성할 계좌번호 입력: ");
		String ano = sc.next();
		if (findAccount(ano) != null) { //중복체크
			System.out.println("계좌번호 중복. 다른번호를 입력하세요.");
		} else {
			System.out.print("계좌주의 성명: ");
			String owner = sc.next();
			System.out.print("계좌 비밀번호: ");
			String password = sc.next();
			System.out.print("초기 입금액: ");
			int balance = sc.nextInt();
			Account newAccount = new Account(ano, owner, password, balance);
			for (int i = 0; i < accountArray.length; i++) {
				if (accountArray[i] == null) { //빈자리에 입력
					accountArray[i] = newAccount;
					System.out.println("계좌가 생성되었습니다.");
					System.out.println(accountArray[i]);
					break;
				}
			}
		}
	}

	// 계좌 목록 보기
	public void accountList() {
		for (int i = 0; i < accountArray.length; i++) {
			if (accountArray[i] != null) { //값이 있을경우 toString 출력
				System.out.println(accountArray[i].toString());
			}
		}
	}

	// 에금하기
	public void deposit() {
		System.out.print("예금할 계좌번호 입력: ");
		String ano = sc.next();
		Account account = findAccount(ano); //계좌 확인
		if (account == null) {
			System.out.println("계좌가 없습니다."); 
			return;
		} else {
			System.out.print("비밀번호 입력: ");
			String password = sc.next();
			if (password.equals(account.getPassword())) { //비밀번호 확인
				System.out.print("예금액 입력: ");
				int money = sc.nextInt();
				if (money < 0) { //음수를 입력하지 않도록 체크
					System.out.println("입력이 잘못 되었습니다.");
				} else {
					account.setBalance(account.getBalance() + money);
					System.out.println("예금 완료");
					System.out.println(account.toString());
				}

			} else {
				System.out.println("비밀번호 오류. 다시 시도해 주세요.");
			}
		}
	}

	// 출금하기
	public void withdraw() {
		System.out.print("출금할 계좌번호 입력: "); 
		String ano = sc.next();
		Account account = findAccount(ano); //계좌 확인
		if (account == null) {
			System.out.println("계좌가 없습니다.");
			return;
		} else {
			System.out.print("비밀번호 입력: ");
			String password = sc.next();
			if (password.equals(account.getPassword())) { //비밀번호 확인
				System.out.print("출금액 입력: ");
				int money = sc.nextInt();
				if (money < 0) { //음수를 입력하지 않도록 체크
					System.out.println("입력이 잘못되었습니다.");
				} else {
					account.setBalance(account.getBalance() - money);
					System.out.println("출금 완료");
					System.out.println(account.toString());
				}
			} else {
				System.out.println("비밀번호 오류. 다시 시도해 주세요.");
			}
		}

	}

	// 송금하기
	public void transfer() {
		System.out.print("고객님의 계좌번호 입력: ");
		String ano = sc.next();
		Account account = findAccount(ano); //계좌 확인
		if (account == null) {
			System.out.println("계좌가 없습니다.");
			return;
		} else {
			System.out.print("비밀번호 입력: ");
			String password = sc.next();
			if (password.equals(account.getPassword())) { //비밀번호 확인
				System.out.print("송금할 금액 입력: ");
				int money = sc.nextInt();
				if ((account.getBalance() - money) < 0) { //잔액보다 큰 값은 송금할 수 없다
					System.out.println("잔액이 부족합니다.");
				} else {
					System.out.print("입금받을 계좌번호 입력: ");
					String anoGet = sc.next();
					Account accountGet = findAccount(anoGet); //입금계좌 확인
					if (findAccount(anoGet) != null) {
						account.setBalance(account.getBalance() - money); //금액 출금
						accountGet.setBalance(accountGet.getBalance() + money); //금액 입금
						System.out.println("송금완료");
						System.out.println(account.toString());
					}
				}
			} else {
				System.out.println("비밀번호 오류. 다시 시도해 주세요.");
			}
		}
	}

	//계좌찾기
	private static Account findAccount(String ano) {
		Account account = null;
		for (int i = 0; i < accountArray.length; i++) {
			if (accountArray[i] != null) {
				String dbAno = accountArray[i].getAno(); 
				if (dbAno.equals(ano)) { //array에 있는 계좌와 일치여부 확인
					account = accountArray[i]; //일치하는 array 인덱스의 값을 account에 저장
					break;
				}
			}
		}
		return account; //저장된 계좌정보를 반환
	}

}
