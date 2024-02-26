package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.BadRequestException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserTicketService userTicketService;

    public UserController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public PurchaseResponse buyLottery( @PathVariable String userId, @PathVariable  String ticketId){

        validateRequestBuyLottery(userId, ticketId);
        return userTicketService.buyLottery(userId, ticketId);
    }

    @GetMapping("/{userId}/lotteries")
    public UserTicketResultResponse getLotteriesByUserId( @PathVariable String userId){

        validateRequestUserId(userId);
        return userTicketService.getLotteryByUserId(userId);
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public TicketResultRefundResponse refundLottery( @PathVariable String userId, @PathVariable  String ticketId){

        validateRequestBuyLottery(userId, ticketId);
        return userTicketService.refundLottery(userId, ticketId);
    }


    public void validateRequestBuyLottery(String userId, String ticketId){
        // TODO Clean code
        validateRequestUserId(userId);
        validateRequestLengthAndDigit(ticketId);
    }
    public void validateRequestUserId(String userId){
        if(userId.length() != 10)
            throw new BadRequestException("userId size must be 10 character");

        if(!userId.matches("\\d+"))
            throw new BadRequestException("userId must contain character 0-9");
    }

    public void validateRequestLengthAndDigit(String ticketId){
        if(ticketId.length() != 6)
            throw new BadRequestException("ticketId size must be 6 character");

        if(!ticketId.matches("\\d+"))
            throw new BadRequestException("ticketId must contain character 0-9");
    }
}
