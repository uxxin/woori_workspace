package dev.syntax.bank.parser;

import java.util.List;

import dev.syntax.bank.model.BankTransaction;
import org.springframework.stereotype.Component;

// BankDataCSVParser는 BankDataParser 인터페이스를 구현했음.
@Component("csvParser")
public class BankDataCSVParser implements BankDataParser {
    @Override
    public List<BankTransaction> parse(List<String> lines) {
        String delimeter = ",";
        return createBankTransactions(lines, delimeter);
    }
}













