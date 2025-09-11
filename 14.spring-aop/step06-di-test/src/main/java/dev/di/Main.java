package dev.di;

public class Main {

  public static void main(String[] args) {
     
    AccountService accountService 
      = ContainerService.getObject(AccountService.class);
    
    accountService.join();
  }

}
