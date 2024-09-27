package com.dbmsproj.rentabike.Repository;

import com.dbmsproj.rentabike.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public class UserRepository {
    private  JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void AddUser(User user){
        System.out.println("Inside AddUser");
        System.out.println(user.toString());
        String sql_query = "INSERT INTO users(username,password,phone,UserFirstName,UserMiddleName,UserLastName,UserAddress,driversLicenseId) VALUES (?,?,?,?,?,?,?,?)";
        try {
            jdbcTemplate.update(sql_query,
                    user.getUsername(),
                    user.getPassword(),
                    user.getPhone(),
                    user.getUserFirstName(),
                    user.getUserMiddleName(),
                    user.getUserLastName(),
                    user.getUserAddress(),
                    user.getDriversLicenseId()
                    // user.getNumberOfAccidents()
            );
            System.out.println("User Added");
        }
        catch (Exception e){
            System.out.println( "Could not add user with the given details, there already exists user with the same username or driving license number please try again");
//            throw e;
        }
    }

    private static final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getLong("UserId"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setphone(rs.getString("phone"));
        user.setUserFirstName(rs.getString("UserFirstName"));
        user.setUserMiddleName(rs.getString("UserMiddleName"));
        user.setUserLastName(rs.getString("UserLastName"));
        user.setUserAddress(rs.getString("UserAddress"));
        user.setDriversLicenseId(rs.getString("DriversLicenseId"));
        user.setNumberOfAccidents(rs.getInt("NumberOfAccidents"));
        user.setRole(rs.getString("Role"));

        return user;
    };

    private static final RowMapper<User> userViewRowMapper = (rs, rowNum) -> {

        // System.out.println("Inside userViewRowMapper");
        User user = new User();
        user.setUserId(rs.getLong("UserId"));
        user.setphone(rs.getString("phone"));
        user.setUserFirstName(rs.getString("UserFirstName"));
        user.setUserMiddleName(rs.getString("UserMiddleName"));
        user.setUserLastName(rs.getString("UserLastName"));
        user.setUserAddress(rs.getString("UserAddress"));
        user.setNumberOfAccidents(rs.getInt("NumberOfAccidents"));

        return user;
    };


    public User getUserByUsername(String username) {
        System.out.println("Inside getUserByUsername");
        String sql = "Select * from users where username = " + "'"+username+"'";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper);
        }
        catch (Exception e){
            System.out.println("No user found with the given username");
            throw e;
        }
    }
    public List<User> getAllUsers(){
        String sql="SELECT * FROM usersview";
        return jdbcTemplate.query(sql,userViewRowMapper);
    }
}