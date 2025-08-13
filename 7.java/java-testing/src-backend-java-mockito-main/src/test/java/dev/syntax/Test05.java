package dev.syntax;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import dev.syntax.data.BookingDAO;
import dev.syntax.data.BookingRequest;
import dev.syntax.exception.BusinessException;
import dev.syntax.service.BookingService;
import dev.syntax.service.MailSender;
import dev.syntax.service.PaymentService;
import dev.syntax.service.RoomService;

class Test05 {
	
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
	@DisplayName("makeBooking()을 통해 findAvailableRoomId()에서 방이 없을 경우 BusinessException을 던진다.")
	void should_ThrowException_When_NoRoomAvailable() {
		// bookingService.makeBooking() 테스트
		
		// Given		
		BookingRequest bookingRequest = BookingRequest.builder()
				.userId("1")
				.dateFrom(LocalDate.of(2025, Month.JANUARY, 1))
				.dateTo(LocalDate.of(2025, Month.JANUARY, 5))
				.guestCount(2)
				.prepaid(false)
				.build();
		
		// roomService의 findAvailableRoomId(bookingRequest)를 호출했을 때,
		when(this.roomServiceMock.findAvailableRoomId(bookingRequest))
		// BusinessException을 던졌다고 가정해라
		.thenThrow(BusinessException.class); 
		
		// When, 실제 메서드 호출
		Executable executable = () -> bookingService.makeBooking(bookingRequest);
		
		// Then
		assertThrows(BusinessException.class, executable);
	}
	
}







