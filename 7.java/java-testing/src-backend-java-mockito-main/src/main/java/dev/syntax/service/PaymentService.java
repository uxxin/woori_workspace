package dev.syntax.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dev.syntax.data.BookingDAO;
import dev.syntax.data.BookingRequest;

public class PaymentService {

	private final Map<String, Double> payments = new HashMap<>();
	private final BookingDAO bookingDAO;
	
	public PaymentService(BookingDAO bookingDAO) {
		super();
		this.bookingDAO = bookingDAO;
	}



	public String pay(BookingRequest bookingRequest, double price) {

		if (price > 200.0 && bookingRequest.getGuestCount() < 3) {
			throw new UnsupportedOperationException("Only small payments are supported.");
		}
		String id = UUID.randomUUID().toString();
		payments.put(id, price);
		
		String bookingId = bookingDAO.save(bookingRequest);
		return id;
	}

}
