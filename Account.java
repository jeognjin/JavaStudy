package bank;

public class Account {
	private String ano;
	private String owner;
	private int balance;
	private String password;

	public Account(String ano, String owner, String password, int balance) {
		super();
		this.ano = ano;
		this.owner = owner;
		this.balance = balance;
		this.password = password;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "[" + owner + "님의 계좌] " + "계좌번호 : " + ano + " | 잔액 : " + balance;
	}

}
