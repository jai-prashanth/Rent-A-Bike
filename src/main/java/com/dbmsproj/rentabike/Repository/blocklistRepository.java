package com.dbmsproj.rentabike.Repository;

import com.dbmsproj.rentabike.Models.bikeupdatelogs;
import com.dbmsproj.rentabike.Models.blocklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class blocklistRepository {
    @Autowired
    private JdbcTemplate tmp;

    public static class blocklistRowMapper implements RowMapper<blocklist> {
        @Override
        public blocklist mapRow(ResultSet rs, int rowNum) throws SQLException {
            blocklist blockList = new blocklist();
               blockList.setUserId(rs.getLong("userId"));
                blockList.setReason(rs.getString("reason"));
                blockList.setBlocktime(rs.getTimestamp("blocktime").toLocalDateTime().toString());
            return blockList;
        }
    }
    public List<blocklist> getAllBlocklist(){
        String s="SELECT * FROM RENTABIKE.blocklist";
        return tmp.query(s,new blocklistRepository.blocklistRowMapper());
    }
}
