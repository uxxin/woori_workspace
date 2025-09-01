package dev.syntax.step03_xml_based_di;

import org.springframework.context.annotation.Bean;

// 비디오 테이프 클래스
public class Tape {
    private String name; // 테이프 이름
    private boolean isWorked; // 테이프가 정상 동작하는지 여부

    public Tape() {
    }

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
        System.out.println("name = " + name);
        this.name = name;
    }

    public void setWorked(boolean worked) {
        System.out.println("worked = " + worked);
        isWorked = worked;
    }

    @Override
    public String toString() {
        return "Tape{" + "name=" + name + ", isWorked=" + isWorked + '}';
    }
}
