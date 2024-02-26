package com.kbtg.bootcamp.posttest.admin;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketResultResponse {

    private String ticket;

    public TicketResultResponse(String ticket) {
        this.ticket = ticket;
    }
}
