package com.dbmsproj.rentabike.Models;

import lombok.Getter;
import lombok.Setter;

public class blocklist {
//    userId Bigint primary key,
//    reason varchar(255) default "Number of accidents more than 3",
//    blocktime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    @Getter
    @Setter
    private Long userId;
    @Getter
    @Setter
    private String reason;
    @Getter
    @Setter
    private String blocktime;

    public blocklist(String reason, String blocktime) {
        this.reason = reason;
        this.blocktime = blocktime;
    }

    public blocklist() {
    }
}
