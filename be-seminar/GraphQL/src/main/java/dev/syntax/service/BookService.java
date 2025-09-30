package dev.syntax.service;

import dev.syntax.domain.Book;
import dev.syntax.domain.Review;
import dev.syntax.repository.BookRepository;
import dev.syntax.repository.ReviewRepository;
import dev.syntax.graphql.resolver.ReviewResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewResolver reviewResolver;

    public BookService(BookRepository bookRepository, ReviewRepository reviewRepository, ReviewResolver reviewResolver) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.reviewResolver = reviewResolver;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(String title, String author) {
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        return bookRepository.save(book);
    }

    public Review addReview(Long bookId, String content, int rating) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        Review review = Review.builder()
                .book(book)
                .content(content)
                .rating(rating)
                .build();

        Review savedReview = reviewRepository.save(review);

        reviewResolver.publishReview(savedReview);

        return savedReview;
    }

    public List<Review> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }
}