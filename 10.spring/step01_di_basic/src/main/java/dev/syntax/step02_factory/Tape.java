package dev.syntax.step02_factory;

// 비디오 테이프 클래스
public class Tape {
    private String name; // 테이프 이름
    private boolean isWorked; // 테이프가 정상 동작하는지 여부

    public Tape(String name, boolean isWorked) {
        this.name = name;
        this.isWorked = isWorked;
    }

    public String getName() {
        return name;
    }

    public boolean isWorked() {
        return isWorked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorked(boolean worked) {
        isWorked = worked;
    }
}
