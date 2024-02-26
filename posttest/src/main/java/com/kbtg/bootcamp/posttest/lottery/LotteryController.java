package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lotteries")
public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("")
    public TicketResponse getLotteryList(){
        List<Lottery> lotteryList = lotteryService.getLotteryList();

        List<String> ticketList = new ArrayList<>();
        for(Lottery lottery : lotteryList){
            ticketList.add(lottery.getTicket());
        }

        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTickets(ticketList);

        return ticketResponse;
    }

}
