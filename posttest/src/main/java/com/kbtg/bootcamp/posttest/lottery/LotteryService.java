package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.admin.LotteryRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryService {

    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository){
        this.lotteryRepository = lotteryRepository;
    }

    public List<Lottery> getLotteryList(){

        return lotteryRepository.findAll();
    }

    public Lottery createLottery(LotteryRequestDto lotteryRequestDto){

        Lottery lottery = new Lottery();
        lottery.setTicket(lotteryRequestDto.getTicket());
        lottery.setPrice(lotteryRequestDto.getPrice());
        lottery.setAmount(lotteryRequestDto.getAmount());

        lotteryRepository.save(lottery);

        return lottery;
    }

}
