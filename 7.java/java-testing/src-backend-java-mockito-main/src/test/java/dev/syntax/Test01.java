package dev.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.syntax.data.BookingRequest;
import dev.syntax.service.BookingService;

class Test01 {
	
	private BookingService bookingService;
	
	@BeforeEach
	void setup() {
		this.bookingService = new BookingService(null, null, null, null);
	}
	
	@Test
	  @DisplayName("적절한 예약 정보가 전달되면 룸 예약 비용을 계산할 수 있다.")
	  void should_CalculateCorrectPrice_When_CorrectInput() {
	    // BookingService.calculatePrice() 메서드를 테스트
		// -> calculatePrice()는 테스트에 있어서 내부 로직이 별도의 의존성을 가지고 있지 않음
	    // 자기 자신만의 로직으로 처리를 수행할 수 있음
	    
	    // Given
	    BookingRequest bookingRequest = BookingRequest.builder()
	        .userId("1")
	        .dateFrom(LocalDate.of(2025, Month.JANUARY, 1))
	        .dateTo(LocalDate.of(2025, Month.JANUARY, 5))
	        .guestCount(2)
	        .prepaid(false)
	        .build();
	    
	    double expected = 4 * 2 * 50.0;
	    
	    // When
	    double actual = bookingService.calculatePrice(bookingRequest);
	    
	    // Then
	    assertEquals(expected, actual);
	    
	  }

}
