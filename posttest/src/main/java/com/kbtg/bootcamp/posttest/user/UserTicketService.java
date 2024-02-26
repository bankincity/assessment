package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.InternalServiceException;
import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTicketService {

    private final UserTicketRepository userTicketRepository;
    private final LotteryRepository lotteryRepository;


    public UserTicketService(UserTicketRepository userTicketRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }

    public PurchaseResponse buyLottery(String userId, String ticketId){

        // check Lottery is already for buy
        Lottery lottery = lotteryRepository.findById(ticketId).orElseThrow(()
                -> new NotFoundException("can not find ticker no"+ticketId));

        // amount is enough for buy
        if(lottery.getAmount() < 1){
            throw new InternalServiceException("ticket no. "+ticketId+" is not enough");
        }

        UserTicket userTicket = submitPurchaseTransaction(userId, lottery);
        return new PurchaseResponse(userTicket.getId());
    }

    public TicketResultRefundResponse refundLottery(String userId, String ticketId){

        // get user Ticket
        List<UserTicket> userTicketList = userTicketRepository.findByUserIdAndTicket(userId, ticketId);

        // check is found data by userId and ticketId
        if(userTicketList.isEmpty())
            throw new NotFoundException("not found Ticket "+ticketId+" by userId "+userId);

        TicketResultRefundResponse ticketResultRefundResponse = null;
        // remove lottery by tranId
        for(UserTicket userTicket : userTicketList){
            userTicketRepository.delete(userTicket);

            // add amount
            Lottery lottery = lotteryRepository.findById(ticketId).orElseThrow(()
                    -> new NotFoundException("(Refund) can not find ticker no"+ticketId));

            lottery.setAmount(lottery.getAmount() + 1);
            lotteryRepository.save(lottery);

            // set up Response
            ticketResultRefundResponse = new TicketResultRefundResponse(userTicket.getTicket());
        }

        return ticketResultRefundResponse;
    }

    public UserTicketResultResponse getLotteryByUserId(String userId){

        List<UserTicket> userTicketList = userTicketRepository.findByUserId(userId);
        if(userTicketList.isEmpty())
            throw new NotFoundException("Not found lottery ticket by userId "+userId);

        List<String> tickets = userTicketList.stream()
                .map(UserTicket::getTicket)
                .toList();

        int totalPrice = calculatePrice(userTicketList);

        int totalLottery = countTotalLottery(userTicketList);

        UserTicketResultResponse userTicketResultResponse = new UserTicketResultResponse();
        userTicketResultResponse.setTickets(tickets);
        userTicketResultResponse.setCost(totalPrice);
        userTicketResultResponse.setCount(totalLottery);

        return userTicketResultResponse;

    }

    public int calculatePrice(List<UserTicket> userTickets){

        // set all id for find price
        List<String> ids = userTickets.stream()
                            .map(UserTicket::getTicket)
                            .toList();

        // find lottery by list id
        List<Lottery> lotteries = lotteryRepository.findAllById(ids);

        return lotteries.stream()
                .mapToInt(Lottery::getPrice)
                .sum();
    }

    public int countTotalLottery(List<UserTicket> userTickets){
        return userTickets.size();
    }

    @Transactional
    public UserTicket submitPurchaseTransaction(String userId, Lottery lottery){

        // decrease amount
        lottery.setAmount(lottery.getAmount() - 1);
        lotteryRepository.save(lottery);

        // insert purchase transaction
        UserTicket userTicket = new UserTicket();
        userTicket.setUserId(userId);
        userTicket.setTicket(lottery.getTicket());
        userTicketRepository.save(userTicket);

        return userTicket;
    }
}
