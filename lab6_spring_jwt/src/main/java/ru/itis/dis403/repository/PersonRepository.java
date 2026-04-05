package ru.itis.dis403.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dis403.model.Booking;
import ru.itis.dis403.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}