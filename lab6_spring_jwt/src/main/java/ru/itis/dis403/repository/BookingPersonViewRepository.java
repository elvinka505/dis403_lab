package ru.itis.dis403.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.model.BookingPersonView;
import ru.itis.dis403.model.Hotel;
import java.util.List;

public interface BookingPersonViewRepository extends JpaRepository<BookingPersonView, Long> {
    List<BookingPersonView> findByHotelId(Long hotelId);
}