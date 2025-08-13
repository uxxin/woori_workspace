package dev.syntax;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import dev.syntax.data.BookingDAO;
import dev.syntax.data.BookingRequest;
import dev.syntax.model.Room;
import dev.syntax.service.BookingService;
import dev.syntax.service.MailSender;
import dev.syntax.service.PaymentService;
import dev.syntax.service.RoomService;

class Test06 {
  
  private BookingService bookingService;
  private PaymentService paymentServiceMock;
  private RoomService roomServiceMock;
  private BookingDAO bookingDAOMock;
  private MailSender mailSenderMock;

  
  @BeforeEach
  void setup() {
    this.paymentServiceMock = mock(PaymentService.class);
    this.roomServiceMock = mock(RoomService.class);
    this.bookingDAOMock = mock(BookingDAO.class);
    this.mailSenderMock = mock(MailSender.class);
        
    this.bookingService 
      = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
    
  }
  
  
  @Test
  @DisplayName("pay()를 통해 어떤 BookingRequest와 price가 전달되어도 UnsupportedException을 던진다.")
  void should_ThrowException_When_NoRoomAvailable() {
	  
	  BookingRequest bookingRequest = BookingRequest.builder()
		        .userId("1")
		        .dateFrom(LocalDate.of(2025, Month.JANUARY, 1))
		        .dateTo(LocalDate.of(2025, Month.JANUARY, 5))
		        .guestCount(2)
		        .prepaid(true) // paymentServiceMock의 pay()를 테스트하기 위해 true로 변경
		        .build();
	  
//	  // pay()로 전달되는 인수의 구체적인 값을 정확히 모를 때 or 값이 고정되어 있지 않을 때
//	  // -> 어떤 값이 들어와도 상관없을 때 - any()를 지정
//	  when(this.paymentServiceMock.pay(bookingRequest, anyDouble()))
//	  .thenThrow(UnsupportedOperationException.class);
	  
	  // 2개 이상의 인수가 전달되는 상황에서 특정 값은 정확하게 고정시키고 싶을 경우, eq()를 통해 값을 고정
	  when(this.paymentServiceMock.pay(any(), eq(400.0)))
	  .thenThrow(UnsupportedOperationException.class);
	  
	  //when
	  Executable excutable = () -> bookingService.makeBooking(bookingRequest);
	  
  }
  
}


