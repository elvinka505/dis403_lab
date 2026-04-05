package ru.itis.dis403.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.model.Hotel;
import ru.itis.dis403.model.Person;
import ru.itis.dis403.model.User;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}