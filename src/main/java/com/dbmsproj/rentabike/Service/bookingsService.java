package com.dbmsproj.rentabike.Service;

import com.dbmsproj.rentabike.Models.bookings;
import com.dbmsproj.rentabike.Repository.bookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class bookingsService {

    private final bookingsRepository bookingsRepository;

    @Autowired
    public bookingsService(bookingsRepository bookingRepository) {
        this.bookingsRepository = bookingRepository;
    }

    public List<bookings> getBookings(String username) {
        return bookingsRepository.findByUsername(username);
    }
}