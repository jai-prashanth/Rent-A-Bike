package com.dbmsproj.rentabike.Repository;

import com.dbmsproj.rentabike.Models.Accidents;
import com.dbmsproj.rentabike.Models.bikes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class AccidentsRepository {
    @Autowired
    private JdbcTemplate tmp;
    public void insertaccident(Accidents a){
        String s="INSERT INTO RENTABIKE.accidents(registration_number,userId,accidentDate,accidentLocation,accidentDescription) VALUES(?,?,?,?,?)";
        tmp.update(s,a.getRegistrationNumber(),a.getUserId(),a.getAccidentDate(),a.getAccidentLocation(),a.getAccidentDescription());
    }

    public static class AccidentRowMapper implements RowMapper<Accidents>{
        @Override
        public Accidents mapRow(ResultSet rs, int rowNum) throws SQLException {
            Accidents accidents = new Accidents();
            accidents.setregistrationNumber(rs.getString("registration_number"));
            accidents.setUserId(rs.getLong("userId"));
            accidents.setAccidentDate(rs.getDate("accidentDate"));
            accidents.setAccidentLocation(rs.getString("accidentLocation"));
            accidents.setAccidentDescription(rs.getString("accidentDescription"));
            return accidents;
        }
    }

    public List<Accidents> getAllAccidents() {
        String s="SELECT * FROM RENTABIKE.Accidents";
        return tmp.query(s,new AccidentsRepository.AccidentRowMapper());

    }
}
