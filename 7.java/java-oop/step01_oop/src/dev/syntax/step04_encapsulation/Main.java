package dev.syntax.step04_encapsulation;

public class Main {
	public static void main(String[] args) {
		BankAccount account = new BankAccount(1000);
		
		System.out.println(account.getBalance());
		
		account.setBalance(2000);
		System.out.println(account.getBalance());
		
		account.setBalance(-500);
	}
}
