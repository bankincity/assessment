package com.kbtg.bootcamp.posttest.test.lottery;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryController;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LotteryControllerTest {

    MockMvc mockMvc;

    @Mock
    LotteryService lotteryService;

    @Mock
    LotteryRepository lotteryRepository;

    @BeforeEach
    void setUp(){
        LotteryController lotteryController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController)
                .build();
    }

    @Test
    @DisplayName("GET /lotteries รายการลอตเตอรี่ทั้งหมด เพิื่อจะได้เลือกซื้อ")
    void getLotteries() throws Exception {

        // set up mock lotteryService
        String ticketNumber = "987654";
        int amount = 1;
        int price = 80;

        when(lotteryService.getLotteryList())
                .thenReturn(getSetupLotteries(ticketNumber, amount, price));


        mockMvc.perform(get("/lotteries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets[0]", is(ticketNumber)));

    }

    List<Lottery> getSetupLotteries(String tickName, int amount, int price){
        List<Lottery> lotteries = new ArrayList<>();

        Lottery lottery = new Lottery();
        lottery.setTicket(tickName);
        lottery.setAmount(amount);
        lottery.setPrice(price);
        lotteries.add(lottery);

        return lotteries;
    }
}
