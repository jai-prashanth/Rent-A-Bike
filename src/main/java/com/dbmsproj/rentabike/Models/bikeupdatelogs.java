package com.dbmsproj.rentabike.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class bikeupdatelogs {
//    logId INT AUTO_INCREMENT PRIMARY KEY,
//    registration_number VARCHAR(255),
//    is_available BOOLEAN,
//    rate_per_hour BIGINT,
//    changetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP

    @Getter
    @Setter
    private int logId;
    @Getter
    @Setter
    private String registration_number;
    @Getter
    @Setter
    private boolean is_available;
    @Getter
    @Setter
    private long rate_per_hour;
    @Getter
    @Setter
    private LocalDateTime changetime;

    public bikeupdatelogs(String registration_number, boolean is_available, long rate_per_hour, LocalDateTime changetime) {
        this.registration_number = registration_number;
        this.is_available = is_available;
        this.rate_per_hour = rate_per_hour;
        this.changetime = changetime;
    }

    public bikeupdatelogs() {
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

}
