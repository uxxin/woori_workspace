package dev.syntax.bank.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import dev.syntax.bank.model.BankTransaction;
import org.springframework.stereotype.Component;

/**
 * service - 해당 프로그램에서 가장 중요한 로직을 담고 있는 패키지
 */
@Component
public class BankProcessor {
  
	//BankProcessor는 List<BankTransaction> 타입을 필요로 함
  private final List<BankTransaction> bankTransactions;
	
  // 현재 BankProcessor 객체를 생성할 수 있는 방법은 이 생성자 하나밖에 없음. 
  public BankProcessor(List<BankTransaction> bankTransactions) {
		super();
		this.bankTransactions = bankTransactions;
	}

// 전체 입출금 내역 조회
  public long calculateTotalAmount() {
    long total = 0L;

    for (BankTransaction bankTransaction : bankTransactions) {
      total += bankTransaction.getAmount(); // colmuns[2]보다 이해하기 쉬움
    }

    return total;
  }

  // 특정 월의 입출금 내역 조회
  public long calculateTotalInMonth(Month month) {
    long total = 0L;

    for (BankTransaction bankTransaction : bankTransactions) {
      LocalDate date = bankTransaction.getDate();
      if (date.getMonth() == month) {
        total += bankTransaction.getAmount();
      }
    }

    return total;
  }
}
