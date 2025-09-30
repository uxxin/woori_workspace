package dev.syntax.graphql.resolver;

import dev.syntax.domain.Book;
import dev.syntax.domain.Review;
import dev.syntax.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookResolver {

    private final BookService bookService;

    public BookResolver(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @QueryMapping
    public Book getBookById(@Argument Long id) {
        return bookService.getBookById(id);
    }

    @MutationMapping
    public Book addBook(@Argument String title, @Argument String author) {
        return bookService.addBook(title, author);
    }

    @MutationMapping
    public Review addReview(@Argument Long bookId, @Argument String content, @Argument int rating) {
        return bookService.addReview(bookId, content, rating);
    }
}