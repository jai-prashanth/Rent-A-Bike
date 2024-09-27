package com.dbmsproj.rentabike.Repository;

import com.dbmsproj.rentabike.Models.bikes;
import com.dbmsproj.rentabike.Models.bikeupdatelogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class bikeupdatelogsRepository {
    @Autowired
    private JdbcTemplate tmp;
    public static class bikeupdatelogRowMapper implements RowMapper<bikeupdatelogs> {
        @Override
        public bikeupdatelogs mapRow(ResultSet rs, int rowNum) throws SQLException {
            bikeupdatelogs bikeupdate = new bikeupdatelogs();
            bikeupdate.setLogId(rs.getInt("logId"));
            bikeupdate.setRegistration_number(rs.getString("registration_number"));
            bikeupdate.setIs_available(rs.getBoolean("is_available"));
            bikeupdate.setRate_per_hour(rs.getLong("rate_per_hour"));
            bikeupdate.setChangetime(rs.getTimestamp("changetime").toLocalDateTime());
            return bikeupdate;
        }
    }

    public List<bikeupdatelogs> getAllBikeUpdateLogs(){
        String s="SELECT * FROM RENTABIKE.bikeupdatelogs";
        return tmp.query(s,new bikeupdatelogsRepository.bikeupdatelogRowMapper());
    }

}
