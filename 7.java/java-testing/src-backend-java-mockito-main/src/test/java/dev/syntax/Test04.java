package dev.syntax;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.syntax.data.BookingDAO;
import dev.syntax.model.Room;
import dev.syntax.service.BookingService;
import dev.syntax.service.MailSender;
import dev.syntax.service.PaymentService;
import dev.syntax.service.RoomService;

class Test04 {

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

		this.bookingService = new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);

		// mock()만 사용하였을 경우, 기본으로 목 객체들이 기본형 타입의 값들을 반환함.
		System.out.println(roomServiceMock.getAvailableRooms()); // Mock 객체라 빈 배열이 나옴.
		System.out.println(roomServiceMock.findAvailableRoomId(null)); // null
		System.out.println(roomServiceMock.getRoomCount()); // 0
	}

	@Test
	@DisplayName("Room 1이 5실을 반환하면 예약 가능한 방은 5개이며, 0실일 경우, 예약 가능한 방은 0이다.")
	void test() {
		// Given
		when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1", 5)))
				.thenReturn(Collections.emptyList()); //thenReturn을 체이닝

		int expectedFirstCall = 5;
		int expectedSecondCall = 0;

		int actualFirst = bookingService.getAvailablePlaceCount();
		int actualSecond = bookingService.getAvailablePlaceCount();

		assertAll(() -> assertEquals(expectedFirstCall, actualFirst),
				() -> assertEquals(expectedSecondCall, actualSecond));

	}

}
