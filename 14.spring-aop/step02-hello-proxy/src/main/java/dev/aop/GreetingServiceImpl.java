package dev.aop;

public class GreetingServiceImpl implements GreetingService {

    @Override
    public void sayHello(){
        System.out.println("Greeting Service: Hello, World!!");
    }
}
