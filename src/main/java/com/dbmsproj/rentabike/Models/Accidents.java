package com.dbmsproj.rentabike.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class Accidents {
    private Long accidentId;
    private String registrationNumber;
    private Long userId;
    private Date accidentDate;
    private String accidentLocation;
    private String accidentDescription;

    public Accidents(Long accidentId, String registrationNumber, Long userId, Date accidentDate, String accidentLocation, String accidentDescription) {
        this.accidentId = accidentId;
        this.registrationNumber = registrationNumber;
        this.userId = userId;
        this.accidentDate = accidentDate;
        this.accidentLocation = accidentLocation;
        this.accidentDescription = accidentDescription;
    }

    public Accidents(String registrationNumber, Long userId, Date accidentDate, String accidentLocation, String accidentDescription) {
        this.registrationNumber = registrationNumber;
        this.userId = userId;
        this.accidentDate = accidentDate;
        this.accidentLocation = accidentLocation;
        this.accidentDescription = accidentDescription;
    }

    public Accidents() {

    }

    public void setAccidentId(Long accidentId) {
        this.accidentId = accidentId;
    }
    public void setregistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setAccidentDate(Date accidentDate) {
        this.accidentDate = accidentDate;
    }
    public void setAccidentLocation(String accidentLocation) {
        this.accidentLocation = accidentLocation;
    }





}


