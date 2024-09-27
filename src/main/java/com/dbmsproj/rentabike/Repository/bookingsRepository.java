package com.dbmsproj.rentabike.Repository;

import com.dbmsproj.rentabike.Models.User;
import com.dbmsproj.rentabike.Models.bookings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class bookingsRepository {
    @Autowired
    private JdbcTemplate tmp;
    @Autowired
    UserRepository userRepository;

    public void insertBooking(bookings b){
        String s="INSERT INTO bookings(customer_id,registration_number,booking_time,pickup_time,return_time,down_payment,total_payment) VALUES(?,?,?,?,?,?,?)";
        tmp.update(s,b.getCustomerId(),b.getRegistrationNumber(),b.getBookingTime(),b.getPickupTime(),b.getReturnTime(),b.getDownPayment(),b.getTotalPayment());
    }
    public void updateBooking(bookings b){
        String s="UPDATE bookings SET bookingTime=?,returnTime=? WHERE bookingId=?";
        tmp.update(s,b.getBookingTime(),b.getReturnTime(),b.getBookingId());
    }
    public void deleteBooking(Long bookingId){
        String s="DELETE FROM bookings WHERE bookingId=?";
        tmp.update(s,bookingId);
    }

    public List<bookings> getBookings(String username){
        String s = "SELECT * FROM BOOKINGS WHERE USERNAME=?";
        return tmp.query(s, new BeanPropertyRowMapper<>(bookings.class), username);
    }

    RowMapper<bookings> bookingsRowMapper = (rs,rowNum) ->{
        bookings booking = new bookings();
        booking.setBookingId(rs.getLong("booking_id"));
        booking.setCustomerId(rs.getLong("customer_id"));
        booking.setRegistrationNumber(rs.getString("registration_number"));
        booking.setBookingTime(rs.getTimestamp("booking_time").toLocalDateTime());
        booking.setPickupTime(rs.getTimestamp("pickup_time").toLocalDateTime());
        booking.setReturnTime(rs.getTimestamp("return_time").toLocalDateTime());
        booking.setTotalPayment(rs.getInt("total_payment"));
//        booking.setFeedback(rs.getString("feedback"));
        return booking;

    };


    public List<bookings> getAllBookings(){
        String sql="SELECT * FROM RENTABIKE.bookings ORDER BY booking_time DESC";
        return tmp.query(sql,bookingsRowMapper);
    }
    public List<bookings> findByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        String sql = "SELECT * FROM BOOKINGS WHERE customerId=" + user.getUserId();
        return tmp.query(sql,bookingsRowMapper);
    }
    public List<bookings> findByUserId(Long userId){
        String sql="SELECT * FROM RENTABIKE.bookings WHERE customer_id="+userId+" ORDER BY booking_time DESC";
        return tmp.query(sql,bookingsRowMapper);
    }

    public List<bookings> findByUserIdLimitOne(Long userId){
        String sql="SELECT * FROM RENTABIKE.bookings WHERE customer_id="+userId+" ORDER BY booking_time DESC LIMIT 1";
        return tmp.query(sql,bookingsRowMapper);
    }
}
//
// long x = tmp.queryforObject()