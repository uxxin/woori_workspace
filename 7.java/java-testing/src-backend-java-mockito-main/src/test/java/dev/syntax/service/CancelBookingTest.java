package dev.syntax.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.syntax.data.BookingDAO;
import dev.syntax.data.BookingRequest;

class CancelBookingTest {
	
	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;

	
	@BeforeEach
	void setup() {
		// mock() - 주어진 클래스에 대한 목 객체(가짜 객체) 생성
		this.paymentServiceMock = mock(PaymentService.class);
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOMock = mock(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);
				
		this.bookingService 
			= new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
	}
	

	@Test
	@DisplayName("id가 1인 예약을 취소할 수 있다.")
	void testCancelBooking() {
	    // given
	    BookingRequest bookingRequest = BookingRequest.builder()
	            .userId("userId1")
	            .dateFrom(LocalDate.of(2025, 1, 1))
	            .dateTo(LocalDate.of(2025, 1, 5))
	            .guestCount(2)
	            .prepaid(true)
	            .build();
	    bookingRequest.setRoomId("roomA");  // 별도 설정

	    when(bookingDAOMock.get("1")).thenReturn(bookingRequest);

	    // when
	    bookingService.cancelBooking("1");

	    // then
	    verify(roomServiceMock).unbookRoom("roomA");
	    verify(bookingDAOMock).delete("1");
	}

	
	@Test
	@DisplayName("없는 아이디 취소 NullPointerException 에러 발생 테스트")
	void cancelThrowNullPointerException() {
	    
	}

}
