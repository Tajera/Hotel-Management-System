package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingDTO;
import com.app.dto.PaymentDTO;
import com.app.service.BookingService;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {
       @Autowired
	private BookingService bookingService;

	
	
	
    @PostMapping
    public ResponseEntity<BookingDTO> createManager(@RequestBody BookingDTO bookingDTO) {
    	BookingDTO createdBooking = bookingService.addBooking(bookingDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

//	@PostMapping
//	public ResponseEntity<?> addBooking(@RequestBody BookingDTO bookingDTO) {
////		System.out.println("Received BookingDTO: " + hotelDTO.toString());
//		BookingDTO bookingSaved = bookingService.addBooking(bookingDTO);
//		return ResponseEntity.status(HttpStatus.ACCEPTED)
//				.body("successfully room booked!! details are :- " + bookingSaved.toString());
//	}

	
	
	@GetMapping("/{bookingId}")
	public ResponseEntity<?> getBookingById(@PathVariable Long bookingId) {
		return bookingService.getBookingById(bookingId)
				.map(bookingDTO -> new ResponseEntity<>(bookingDTO, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping(value = "/payment/{bookingId}")
	public ResponseEntity<PaymentDTO> addPayment(@PathVariable Long bookingId) {
		PaymentDTO addedPayment = bookingService.addedPayment(bookingId);
		return new ResponseEntity<>(addedPayment,HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<BookingDTO>> getAllBookings() {
		List<BookingDTO> bookings = bookingService.getAllBookings();
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}


	@DeleteMapping("/{bookingId}")
	public ResponseEntity<Void> deleteHotel(@PathVariable Long hotelId) {
		bookingService.deleteBooking(hotelId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
