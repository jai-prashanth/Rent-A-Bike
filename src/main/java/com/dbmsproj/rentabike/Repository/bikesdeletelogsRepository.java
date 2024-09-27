package com.dbmsproj.rentabike.Repository;

import com.dbmsproj.rentabike.Models.bikesdeletelogs;
import com.dbmsproj.rentabike.Models.bikeupdatelogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class bikesdeletelogsRepository {
    @Autowired
    private JdbcTemplate tmp;
    public static class bikesdeletelogsRowMapper implements RowMapper<bikesdeletelogs> {
        @Override
        public bikesdeletelogs mapRow(ResultSet rs, int rowNum) throws SQLException {
            bikesdeletelogs bikesdeletelogs = new bikesdeletelogs();
            bikesdeletelogs.setLogId(rs.getInt("logId"));
            bikesdeletelogs.setRegistration_number(rs.getString("registration_number"));
            bikesdeletelogs.setDeletetime(rs.getTimestamp("deletetime").toLocalDateTime());
            return bikesdeletelogs;
        }
    }
    public List<bikesdeletelogs> getAllBikesDeleteLogs(){
        String s="SELECT * FROM RENTABIKE.bikesdeletelogs";
        return tmp.query(s,new bikesdeletelogsRepository.bikesdeletelogsRowMapper());
    }
}
