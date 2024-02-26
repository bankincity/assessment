package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final LotteryService lotteryService;

    public AdminController (LotteryService lotteryService){
        this.lotteryService = lotteryService;
    }

    @PostMapping("/lotteries")
    public ResponseEntity<TicketResultResponse> createLottery(@Validated @RequestBody LotteryRequestDto lotteryRequestDto){

        Lottery lottery = lotteryService.createLottery(lotteryRequestDto);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TicketResultResponse(lottery.getTicket()));
    }
}
