package com.kbtg.bootcamp.posttest.test.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbtg.bootcamp.posttest.admin.AdminController;
import com.kbtg.bootcamp.posttest.admin.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    MockMvc mockMvc;

    @Mock
    LotteryService lotteryService;

    @BeforeEach
    void setUp(){
        AdminController adminController = new AdminController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
//                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("POST /admin/lotteries admin Create Lottery เลข 999999 จำนวน 1 ใบ ใบละ 80 บาท")
    void createLottery() throws Exception {

        String ticketNumber = "987654";
        // set up request
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto();
        lotteryRequestDto.setTicket(ticketNumber);
        lotteryRequestDto.setAmount(1);
        lotteryRequestDto.setPrice(80);

        // set up mock lotteryService
        Lottery lottery = new Lottery();
        lottery.setTicket(lotteryRequestDto.getTicket());
        lottery.setAmount(lotteryRequestDto.getAmount());
        lottery.setPrice(lotteryRequestDto.getPrice());
        when(lotteryService.createLottery(any()))
                .thenReturn(lottery);

        // run test
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(lotteryRequestDto);

        mockMvc.perform(
                        post("/admin/lotteries")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)

                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.ticket", is(ticketNumber)));

    }


}