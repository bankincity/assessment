package com.kbtg.bootcamp.posttest.lottery;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TicketResponse {

    private List<String> tickets;

    public TicketResponse(){

    }

}
