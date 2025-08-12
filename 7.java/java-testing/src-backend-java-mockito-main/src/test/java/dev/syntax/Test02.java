package dev.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.syntax.data.BookingDAO;
import dev.syntax.service.BookingService;
import dev.syntax.service.MailSender;
import dev.syntax.service.PaymentService;
import dev.syntax.service.RoomService;

class Test02 {
	
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
		
		// mock()만 사용하였을 경우, 기본으로 목 객체들이 기본형 타입의 값들을 반환함
		System.out.println(roomServiceMock.getAvailableRooms()); // 빈 배열이 나옴 []
		System.out.println(roomServiceMock.findAvailableRoomId(null)); // null
		System.out.println(roomServiceMock.getRoomCount()); // 0
	}
	
	
	@Test
	@DisplayName("현재 예약 가능한 방의 수를 조회할 수 있다.")
	void should_CountAvailablePlaces() {
		// Given
		int expected = 0;
		
		// When
		int actual = bookingService.getAvailablePlaceCount();
		
		// Then
		assertEquals(expected, actual);
	}

}
