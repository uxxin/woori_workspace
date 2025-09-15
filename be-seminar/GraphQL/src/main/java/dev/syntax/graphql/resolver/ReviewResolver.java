package dev.syntax.graphql.resolver;

import dev.syntax.domain.Review;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Controller
public class ReviewResolver {

    private final Sinks.Many<Review> reviewSink = Sinks.many().multicast().onBackpressureBuffer();

    public void publishReview(Review review) {
        System.out.println("[publishReview] Publishing review id=" + review.getId() +
                ", bookId=" + (review.getBook() != null ? review.getBook().getId() : "null") +
                ", content=" + review.getContent());
        reviewSink.tryEmitNext(review);
    }

    @SubscriptionMapping
    public Publisher<Review> reviewAdded(@Argument Long bookId) {
        System.out.println("[reviewAdded] Subscription called for bookId=" + bookId);

        return reviewSink.asFlux()
                .filter(r -> {
                    Long rBookId = r.getBook() != null ? r.getBook().getId() : null;
                    boolean matches = bookId.equals(rBookId);
                    System.out.println("[reviewAdded.filter] reviewId=" + r.getId() +
                            ", reviewBookId=" + rBookId + ", matches=" + matches);
                    return matches;
                });
    }
}
