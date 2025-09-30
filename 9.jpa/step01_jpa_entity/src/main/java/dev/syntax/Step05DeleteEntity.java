package dev.syntax;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

// Entity 제거
public class Step05DeleteEntity {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();


        // Entity 조회 
        Book book = manager.find(Book.class, 1);
        System.out.println("book = " + book);

        transaction.begin();

            manager.remove(book); // 조회된 엔티티이자 제거할 엔티티를 전달

        transaction.commit();

    }
}
