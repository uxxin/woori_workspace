package dev.syntax;

import jakarta.persistence.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

// 영속성 컨텍스트의 개념 및 생명주기 동작을 테스트하는 코드
public class AppTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("step01");

    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();


    @Test
    @DisplayName("Transition 상태 확인 테스트")
    void testTransient() {

        //Book 객체 생성
        Book book = new Book("하루하나 금융 지식", "권정주");

        //EntityManager를 통해 영속화 되었는지 확인
        boolean isManaged = manager.contains(book);

        assertFalse(isManaged); // "managed 상태가 아닐 것이다."
    }

    //PC-Persistence Context (영속성 컨텍스트)
    @Test
    @DisplayName("persist()를 수행할 경우, 엔티티가 영속화된다.")
    void testPersist() { //엔티티가 PC에 잘 적용되었는지?

        // Hint. 영속성 컨텍스트는 본질적으로 캐싱 동작을 수행
        // 커밋 후 다시  조회(SELECT 쿼리가 한 번 더 실행되는가?)
        transaction.begin();
        Book book = new Book("하루하나 금융 지식", "권정주");
        manager.persist(book); // 캐시에 넣었다.
        transaction.commit();

        // 저장된 것을 확인해야 하니까 조회된 엔티티와 삽입 시 적용된 엔티티 객체의 참조값 비교
        Book foundBook = manager.find(Book.class, book.getId());
        assertEquals(book, foundBook); // 실행하면 select 문은 안나옴 !! => 캐시에서 꺼내오니까 !
    }

    @Test
    @DisplayName("한 번 영속화된 엔티티는 다시 조회 시 DB가 아닌 PC에서 조회된다.")
    void testFindWithPC() {
        int id = 40;
        Book book = manager.find(Book.class, id);
        System.out.println("첫 번째로 조회한 책 = " + book);

        // 한 번 조회 후 (첫 조회할 때 PC에 캐싱) 다시 한 번 조회할 때에도 SELECT를 날리는지 확인
        // -> SELECT가 한 번만 실행됨
        Book bookTwice = manager.find(Book.class, id);
        System.out.println("두 번째로 조회한 책 = " + bookTwice);
        assertEquals(book, bookTwice);
    }


    @Test
    @DisplayName("엔티티의 값을 수정하고 트랜잭션을 commit()하면 변경 감지가 발생되어 UPDATE 쿼리가 수행된다.")
    void testUpdate() {
        // TODO: 단정문 없이 콘솔 확인 용도로 작성해도 됨, DB에서 확인
        transaction.begin();
        Book book = new Book("새로운 책2", "권정주");
        manager.persist(book);
        book.updateBookName("재밌는 책2");
        transaction.commit();
    }

    @Test
    @DisplayName("만약 엔티티를 detach할경우 필드의 값을 변경해도 UPDATE쿼리가 수행되지 않는다")
    void testDetach() {
        // TODO: 단정문 없이 콘솔 확인 용도로 작성해도 됨, DB에서 확인
        transaction.begin();
        Book book = new Book("DETACH 전 책", "양유진");
        manager.persist(book);
        System.out.println("persist 후 = " + book);
        manager.detach(book);
        book.updateBookName("DETACH 후 책");
        System.out.println("detach 후 업데이트 한 책 = " + book);
        transaction.commit();
    }

    @Test
    @DisplayName("remove를 수행할경우 PC와 DB에서 데이터가 제거된다")
    void testRemoveWithCheck() {
        transaction.begin();
        Book book = new Book("REMOVE 수행할 책", "양유진");
        manager.persist(book);
        transaction.commit(); // INSERT 확정

        // --- remove 수행 전 DB 조회 ---
        Book beforeRemove = manager.find(Book.class, book.getId()); //find()는 DB 조회
        System.out.println("remove 수행 전 조회 = " + beforeRemove);

        transaction.begin();
        manager.remove(beforeRemove); // PC에서 삭제
        transaction.commit(); // DB에서 DELETE 실행

        // --- remove 수행 후 DB 조회 ---
        Book afterRemove = manager.find(Book.class, book.getId());
        System.out.println("remove 수행 후 조회 = " + afterRemove);
    }


    @Test
    @DisplayName("flush()를 수행하면 변경 사항이 즉시 DB에 반영되지만, 트랜잭션이 롤백되면 변경 사항도 취소된다.")
    void testFlushWithRollback() {
        transaction.begin();
        Book book = new Book("Flush 테스트용 책", "양유진");
        manager.persist(book);
        Book beforeFlush = manager.find(Book.class, book.getId());
        System.out.println("flush 전 조회 = " + beforeFlush); // Flush 테스트용 책이 조회되지 않을 것이다.
        // 실제 결과: 조회는 되는데 db가 아니라 pc에서 조회
        manager.flush();
        Book afterFlush = manager.find(Book.class, book.getId());
        System.out.println("flush 후 조회 = " + afterFlush);
        // commit 안했지만 Flush 테스트용 책이 나올 것이다.
        // flush가 DB와 동기화 해주기 때문 !

        transaction.rollback(); // DB 반영된게 취소될 것이다.

        transaction.begin();
        Book afterRollback = manager.find(Book.class, book.getId());
        transaction.commit();
        System.out.println("afterRollback = " + afterRollback);

    }

    // TODO: 그 외 다른 테스트 케이스 만들어보기(팀당 최소 1~2개)

    @Test
    @DisplayName("detach된 엔티티를 merge() 하면 다시 영속 상태가 되어 변경이 DB에 반영된다")
    void testMerge() {

    }

    @Test
    @DisplayName("clear()로 영속성 컨텍스트 전체 초기화 시 엔티티 상태 확인 테스트")
    void testClear() {

        transaction.begin();

        manager.setFlushMode(FlushModeType.COMMIT);

        Book book1 = new Book("clear 테스트용 책1", "양유진");
        Book book2 = new Book("clear 테스트용 책2", "양유진");

        //<시점1>

        manager.persist(book1);
        manager.persist(book2); // PC에만 있다.

        // find 전에 자동으로 flush를 실행 => 따라서 sout으로 boolean을 출력
        Book beforeClear = manager.find(Book.class, book1.getId());
        Book beforeClear2 = manager.find(Book.class, book2.getId());
        System.out.println("clear 전 book1 (find 사용 시) = " + beforeClear);
        System.out.println("clear 전 book2 (find 사용 시) = " + beforeClear2); // PC에서 조회가 된다.

        // <시점 2>

//        System.out.println("clear 전 book1 = " + manager.contains(book1));
//        System.out.println("clear 전 book2 = " + manager.contains(book2));

        manager.clear(); // PC에 있는 전체 초기화

        // <시점 3>

        Book afterClear = manager.find(Book.class, book1.getId());
        Book afterClear2 = manager.find(Book.class, book2.getId());
        System.out.println("clear 이후 book1 (find 사용 시) = " + afterClear);
        System.out.println("clear 이후 book2 (find 사용 시) = " + afterClear2);

//        System.out.println("clear 후 book1 = " + manager.contains(book1));
//        System.out.println("clear 후 book2 = " + manager.contains(book2));
        transaction.commit();

    }

    //@Test
    // find(Book.clas, 1) - SELECT, 초기 조회니까 PC에 없어서 SELECT 수행 => SELECT를 하면 CLEAR
    // clear() - PC 초기화
    // find() - 다시 조회, PC에 없으니까 SELECT 다시 호출
}
