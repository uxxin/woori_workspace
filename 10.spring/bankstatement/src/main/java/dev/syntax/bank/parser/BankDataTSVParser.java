package dev.syntax.bank.parser;

import java.util.List;

import dev.syntax.bank.model.BankTransaction;
import org.springframework.stereotype.Component;

@Component("tsvParser")
public class BankDataTSVParser implements BankDataParser {

	@Override
	public List<BankTransaction> parse(List<String> lines) {
		String delimeter = "\t";
		return createBankTransactions(lines, delimeter);
	}
}
