package com.dbmsproj.rentabike.Service;

import com.dbmsproj.rentabike.Repository.bikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class bikesService {
    private final bikesRepository bR;
    @Autowired
    public bikesService(bikesRepository bR){
        this.bR=bR;
    }
    public long bikesRatePerHour(String registrationNumber){
        return bR.selectRatePerHour(registrationNumber);
    }
}