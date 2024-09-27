package com.dbmsproj.rentabike.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class bikes {
    private String registrationNumber;
    private String bikeModel;
    //    private String owner;
    private String bikeStatus;
    private String CBookNumber;
    private String Insurance;
    private boolean isAvailable;
    private long ratePerHour;

    public bikes(String registrationNumber, String bikeModel, String bikeStatus, String CBookNumber, String insurance, boolean isAvailable,long ratePerHour) {
        this.registrationNumber = registrationNumber;
        this.bikeModel = bikeModel;
        this.bikeStatus=bikeStatus;
        this.CBookNumber = CBookNumber;
        this.Insurance = insurance;
        this.isAvailable = isAvailable;
        this.ratePerHour=ratePerHour;
    }

    public bikes(String registrationNumber, boolean isAvailable, long ratePerHour) {
        this.registrationNumber = registrationNumber;
        this.isAvailable = isAvailable;
        this.ratePerHour=ratePerHour;
    }

    public bikes(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public String getCBookNumber() {
        return CBookNumber;
    }

    public String getInsurance() {
        return Insurance;
    }

    public String getBikeStatus() {
        return bikeStatus;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public long getRatePerHour(){ return ratePerHour; }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    public void setCBookNumber(String CBookNumber) {
        this.CBookNumber = CBookNumber;
    }

    public void setInsurance(String insurance) {
        this.Insurance = insurance;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void setBikeStatus(String bikeStatus) {
        this.bikeStatus = bikeStatus;
    }

    public void setRatePerHour(long ratePerHour){ this.ratePerHour = ratePerHour; }
}