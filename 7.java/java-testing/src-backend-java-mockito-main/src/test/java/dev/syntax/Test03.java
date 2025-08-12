package dev.syntax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.syntax.data.BookingDAO;
import dev.syntax.model.Room;
import dev.syntax.service.BookingService;
import dev.syntax.service.MailSender;
import dev.syntax.service.PaymentService;
import dev.syntax.service.RoomService;

class Test03 {
	
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
//		System.out.println(roomServiceMock.getAvailableRooms()); // 빈 배열이 나옴 []
//		System.out.println(roomServiceMock.findAvailableRoomId(null)); // null
//		System.out.println(roomServiceMock.getRoomCount()); // 0
	}
	
	
	@Test
	@DisplayName("만약 Room 1이 2개의 방이 남았을 경우, 현재 예약 가능한 방은 2로 반환될 것이다.")
	void should_CountAvailablePlaces() {
		// when().thenReturn()
		
		// Given
		when(this.roomServiceMock.getAvailableRooms()) // getAvailableRooms()를 호출하면,
		.thenReturn(Collections.singletonList(new Room("Room 1", 2)));// Room1이 2개 있다고 응답해
		
		int expected = 2;
		
		// When
		int actual = bookingService.getAvailablePlaceCount();
		
		// Then
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("Room 1이 2실, Room 2가 5실 남았을 경우, 총 예약 가능한 방은 7이다.")
	void should_CountAvailablePlaces_When_MultipleRoomAvailable() { // 예약 가능한 방 카운트
		
		// Given
		List<Room> rooms = Arrays.asList(new Room("Room 1", 2), new Room("Room 2", 5));
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(rooms);
		
		int expected = 7;
		
		// When
		int actual = bookingService.getAvailablePlaceCount();

		// Then
		assertEquals(expected, actual);
	}

}