package com.dbmsproj.rentabike.Repository;

import com.dbmsproj.rentabike.Models.User;
import com.dbmsproj.rentabike.Models.bikes;
import com.dbmsproj.rentabike.security.SecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class bikesRepository {
    @Autowired
    private JdbcTemplate tmp;
    private SecurityServices securityServices;

    public bikesRepository(SecurityServices securityServices) {
        this.securityServices = securityServices;
    }

    public void insertBike(bikes b){
        String s="INSERT INTO RENTABIKE.bikes(registration_number,bike_model,bike_status,CBook_number,insurance,is_available, rate_per_hour) VALUES(?,?,?,?,?,?,?)";
        tmp.update(s,b.getRegistrationNumber(),b.getBikeModel(),b.getBikeStatus(),b.getCBookNumber(),b.getInsurance(),b.isAvailable(), b.getRatePerHour());
    }
    public void deleteBike(bikes b){
        String s="DELETE FROM RENTABIKE.bikes WHERE registration_number=?";
        tmp.update(s,b.getRegistrationNumber());
    }
    public void updateBike(bikes b) {
        String sql = "UPDATE RENTABIKE.bikes " +
                "SET is_available = ?, rate_per_hour = ? " +
                "WHERE registration_number = ?";

        tmp.update(sql, b.isAvailable(), b.getRatePerHour(), b.getRegistrationNumber());
    }
    public void updateBikeAvailability(String registrationNumber, boolean isAvailable) {
        String sql = "UPDATE RENTABIKE.bikes " +
                "SET is_available = ? " +
                "WHERE registration_number = ?";

        tmp.update(sql, isAvailable, registrationNumber);
    }

    public long selectRatePerHour(String registrationNumber){
        String s="SELECT ratePerHour FROM bikes WHERE registrationNumber=?";
        return tmp.queryForObject(s,long.class,registrationNumber);
    }

    public static class BikeRowMapper implements RowMapper<bikes>{
        @Override
        public bikes mapRow(ResultSet rs, int rowNum) throws SQLException {
            bikes bike = new bikes();
            bike.setRegistrationNumber(rs.getString("registration_number"));
            bike.setBikeModel(rs.getString("bike_model"));
            bike.setBikeStatus(rs.getString("bike_status"));
            bike.setCBookNumber(rs.getString("CBook_number"));
            bike.setInsurance(rs.getString("insurance"));
            bike.setAvailable(rs.getBoolean("is_available"));
            bike.setRatePerHour(rs.getLong("rate_per_hour"));
            return bike;
        }
    }

    public List<bikes> getAllAvailableBikes(){
        String s="SELECT * FROM RENTABIKE.bikes WHERE is_available=1";
        return tmp.query(s,new BikeRowMapper());
    }
    public List<bikes> getAllBikes(){
        String s="SELECT * FROM RENTABIKE.bikes";
        return tmp.query(s,new BikeRowMapper());
    }
    public long getRentperHour(String reg_no) {
        String sql = "SELECT * FROM RENTABIKE.bikes WHERE registration_number = " + "'" + reg_no + "'";
        List<bikes> bike= tmp.query(sql, new BikeRowMapper());
        try {
            return bike.get(0).getRatePerHour();
        }
        catch (Exception e) {
            return 0;
        }

    }

    public int getTotalNumberofAccidents(){
        User user=securityServices.findLoggedInUser();
        return user.getNumberOfAccidents();
    }



    public List<bikes> getAvailableBikesBetweenDates(LocalDateTime pickupDate, LocalDateTime returnDate){
        User user=securityServices.findLoggedInUser();
        int x=user.getNumberOfAccidents();
        String sql = "SELECT * FROM RENTABIKE.bikes b WHERE b.registration_number " +
                "NOT IN (" +
                "SELECT t.registration_number FROM bookings t WHERE " +
                "(t.pickup_time>= ? AND t.pickup_time<= ?) OR (t.return_time>= ? AND t.return_time<= ?) OR (t.pickup_time<=? AND t.return_time>=?) or (b.is_available=0) or (?>2)"
                +")";
        return tmp.query(sql, new BikeRowMapper(), pickupDate, returnDate, pickupDate, returnDate, pickupDate, returnDate,x);

    }

}