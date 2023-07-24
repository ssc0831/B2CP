package com.group5.b2c.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group5.b2c.model.Book;
import com.group5.b2c.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
	
}
