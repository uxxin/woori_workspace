package dev.bank.parser;

import java.util.List;

import dev.bank.model.BankTransaction;

public class BankDataTSVParser implements BankDataParser {

	@Override
	public List<BankTransaction> parse(List<String> lines) {
		String delimeter = "\t";
		return createBankTransactions(lines, delimeter);
	}
}
