package ru.itis.dis403.service;

import org.springframework.stereotype.Service;
import ru.itis.dis403.dto.BookingDto;
import ru.itis.dis403.model.Booking;
import ru.itis.dis403.model.Person;
import ru.itis.dis403.model.User;
import ru.itis.dis403.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public BookingDto getBookingById(Long bookingId, User user) {
        // id и hotel_id
        Booking b = bookingRepository.findByIdAndHotelId(bookingId, user.getHotel().getId());

        return BookingDto.builder()
                .id(b.getId())
                .arrivaldate(b.getArrivaldate())
                .stayingdate(b.getStayingdate())
                .departuredate(b.getDeparturedate())
                .personId(b.getPerson().getId())
                .name(b.getPerson().getName())
                .build();
    }

    public BookingDto updateBooking(BookingDto bookingDto, User user) {
        Booking booking = bookingRepository.findByIdAndHotelId(bookingDto.getId(), user.getHotel().getId());

        if (booking == null) {
            throw new RuntimeException("Booking not found or access denied");
        }

        booking.setArrivaldate(bookingDto.getArrivaldate());
        booking.setStayingdate(bookingDto.getStayingdate());
        booking.setDeparturedate(bookingDto.getDeparturedate());

        Person person = booking.getPerson();
        person.setName(bookingDto.getName());

        booking = bookingRepository.save(booking);

        return BookingDto.builder()
                .id(booking.getId())
                .arrivaldate(booking.getArrivaldate())
                .stayingdate(booking.getStayingdate())
                .departuredate(booking.getDeparturedate())
                .personId(booking.getPerson().getId())
                .name(booking.getPerson().getName())
                .build();
    }
}