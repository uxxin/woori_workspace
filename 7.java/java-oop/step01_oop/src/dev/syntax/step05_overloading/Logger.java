package dev.syntax.step05_overloading;


public class Logger {

    // 단순 메시지
    public void log(String message) {
        System.out.println("[INFO] " + message);
    }

    // 예외 로그
    public void log(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
        e.printStackTrace(System.out);
    }

    // 태그와 메시지를 함께
    public void log(String tag, String message) {
        System.out.println("[" + tag + "] " + message);
    }

    // 객체 로그
    public void log(Object obj) {
        System.out.println("[DEBUG] " + obj.toString());
    }
}
