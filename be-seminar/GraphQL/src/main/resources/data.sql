-- 1. 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS bookdb
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE bookdb;

-- 2. Book 테이블 생성
CREATE TABLE IF NOT EXISTS book (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
    );

-- 3. Review 테이블 생성
CREATE TABLE IF NOT EXISTS review (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      book_id BIGINT NOT NULL,
                                      content TEXT NOT NULL,
                                      rating INT NOT NULL,
                                      CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE
    );

-- 4. 샘플 데이터 삽입 (저자 한국인으로 변경)
INSERT INTO book (title, author) VALUES
                                     ('GraphQL 입문', '홍길동'),
                                     ('스프링 부트 레시피', '김철수');

INSERT INTO review (book_id, content, rating) VALUES
                                                  (1, '정말 쉽게 설명된 GraphQL 책', 5),
                                                  (1, '실무 적용 예제가 조금 부족했음', 3),
                                                  (2, 'Spring Boot 활용 사례가 많아서 도움됨', 4);
