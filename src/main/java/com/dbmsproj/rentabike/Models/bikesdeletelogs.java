package com.dbmsproj.rentabike.Models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class bikesdeletelogs {
    @Getter
    @Setter
    private int logId;
    @Getter
    @Setter
    private String registration_number;
    @Getter
    @Setter
    private LocalDateTime deletetime;

    public bikesdeletelogs(String registration_number, LocalDateTime deletetime) {
        this.registration_number = registration_number;
        this.deletetime = deletetime;
    }

    public bikesdeletelogs() {
    }

    public void setDeletetime(LocalDateTime deletetime) {
        this.deletetime = deletetime;
    }
}
