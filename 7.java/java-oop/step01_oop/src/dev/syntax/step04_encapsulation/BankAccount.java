package dev.syntax.step04_encapsulation;

public class BankAccount {
	private double balance;

	public BankAccount(double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		if(balance>0) {
			this.balance = balance;
		}
		
		else {
			System.out.println("잔액은 음수가 될 수 없습니다.");
		}
	}
	
	
}
