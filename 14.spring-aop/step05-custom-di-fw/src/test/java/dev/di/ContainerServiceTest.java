package dev.di;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

// @Inject Annotation 테스트 코드
public class ContainerServiceTest {
  // @Inject를 사용하지 않은 BookRepository 인스턴스 가져오기 테스트
  @Test
  void getObject_BookRepository() {
    BookRepository bookRepository = ContainerService.getObject(BookRepository.class);
    assertNotNull(bookRepository);
  }

  @Test
  public void getOject_BookService() {
    BookService bookService = ContainerService.getObject(BookService.class);
    assertNotNull(bookService);
    assertNotNull(bookService.bookRepository);
  }
}
