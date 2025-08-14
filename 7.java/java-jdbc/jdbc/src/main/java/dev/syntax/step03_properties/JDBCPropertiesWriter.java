package dev.syntax.step03_properties;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Properties;

public class JDBCPropertiesWriter {
    public static void main(String[] args) {
        Properties p = new Properties();

        p.setProperty("USER_NAME", "root");
        p.setProperty("PASSWORD", "1234");
        p.setProperty("DB_URL", "jdbc:mysql://localhost:3306/");
        p.setProperty("DATABASE_SCHEMA", "sakila");

        try (OutputStream stream = new FileOutputStream("src/main/resources/jdbc.properties")) {
            p.store(stream, "DB 설정 정보");
            System.out.println("jdbc.properties 파일 생성 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
