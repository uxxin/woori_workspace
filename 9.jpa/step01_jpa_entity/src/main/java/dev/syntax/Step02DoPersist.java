package dev.syntax;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

// JPA의 EntityManger를 통해 기본적인 데이터(Entity) 조작, DBMS에 영속화(INSERT)
public class Step02DoPersist {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");
        System.out.println("factory = " + factory);

        EntityManager entityManager = factory.createEntityManager();

        // 트랜잭션 처리
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); // 트랜잭션 시작, BEGIN;

        // Book이라는 모델 클래스를 생성해서 DBMS에 영속화
        // -> Book이라는 테이블을 생성하고 추가
        Book book = new Book("하루하나 금융 지식", "권정주");

        // JDBC 방식
        // Connection 객체 생성, PreparedStatement 등등

        // JPA
        entityManager.persist(book); //INSERT INTO book... 쿼리가 생성됨.
        transaction.commit(); // 트랜잭션 커밋, COMMIT;
    }

}
