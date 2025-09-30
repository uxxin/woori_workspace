package dev.di;

public class AccountService {

  @Inject // 패키지명이 같아서 인식
  AccountRepository accountRepository;
  
  public void join() {
    System.out.println("Service.join");
    accountRepository.save(); // save()가 정상 호출됨
  }
}
