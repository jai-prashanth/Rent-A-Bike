package com.dbmsproj.rentabike.Models;

import lombok.*;

//@Entity
//@Table(name = "users")
@Getter
@Setter
@Data
//@AllArgsConstructor

public class User {
    //    @Id
//    private Long userId;
//    @Column(unique = true
    private Long UserId;
    private String username;
    private String password;
    private String phone;
    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private String userAddress;
    private String driversLicenseId;
    private int numberOfAccidents;
    private String Role;

    public User(String username, String password, String phone, Long UserId, String UserFirstName, String UserMiddleName,
                String UserLastName, String UserAddress, String driversLicenseId, int numberOfAccidents,String Role) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.UserId = UserId;
        this.userFirstName = UserFirstName;
        this.userMiddleName = UserMiddleName;
        this.userLastName = UserLastName;
        this.userAddress = UserAddress;
        this.driversLicenseId = driversLicenseId;
        this.numberOfAccidents = numberOfAccidents;
        this.Role=Role;
    }

    public User() {

    }

    public User(String username, String password, String phone, String userFirstName, String userMiddleName, String userLastName, String userAddress, String driversLicenseId) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.userFirstName = userFirstName;
        this.userMiddleName = userMiddleName;
        this.userLastName = userLastName;
        this.userAddress = userAddress;
        this.driversLicenseId = driversLicenseId;
        //   this.numberOfAccidents = 0;
    }

    public User(long UserId, String phone, String userFirstName, String userMiddleName, String userLastName, String userAddress, int numberOfAccidents) {
        this.UserId = UserId;
        this.phone = phone;
        this.userFirstName = userFirstName;
        this.userMiddleName = userMiddleName;
        this.userLastName = userLastName;
        this.userAddress = userAddress;
        this.numberOfAccidents = numberOfAccidents;
        }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public void setUserId(Long UserId) {
        this.UserId = UserId;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUserAddress(String UserAddress) {
        this.userAddress = UserAddress;
    }

    public void setDriversLicenseId(String driversLicenseId) {
        this.driversLicenseId = driversLicenseId;
    }

    public void setNumberOfAccidents(int numberOfAccidents) {
        this.numberOfAccidents = numberOfAccidents;
    }
    public void setRole(String Role) {
        this.Role = Role;
    }
}