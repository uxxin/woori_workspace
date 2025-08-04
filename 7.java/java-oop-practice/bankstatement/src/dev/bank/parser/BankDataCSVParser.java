package dev.bank.parser;

import java.util.List;

import dev.bank.model.BankTransaction;

// BankDataCSVParser는 BankDataParser 인터페이스를 구현했음.
public class BankDataCSVParser implements BankDataParser {
	@Override
	public List<BankTransaction> parse(List<String> lines) {
		String delimeter = ",";
		return createBankTransactions(lines, delimeter);
	}
}











