package com.dbmsproj.rentabike.Service;

import com.dbmsproj.rentabike.Models.User;
import com.dbmsproj.rentabike.Repository.UserRepository;
import com.dbmsproj.rentabike.security.securityuserdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class userservice implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("Inside loadUserByUsername");
        User user = userRepository.getUserByUsername(username);
//        if (user == null) throw new UsernameNotFoundException("Not found");
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        if ("ADMIN".equals(user.getRole())) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        } else {
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
        return new securityuserdetails(user);
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                authorities);
    }
}