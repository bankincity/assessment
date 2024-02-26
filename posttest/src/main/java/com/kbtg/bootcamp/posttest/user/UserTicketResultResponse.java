package com.kbtg.bootcamp.posttest.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserTicketResultResponse {

    private List<String> tickets;

    private int count;

    private int cost;
}
