package com.kbtg.bootcamp.posttest.test.user;

import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.user.PurchaseResponse;
import com.kbtg.bootcamp.posttest.user.UserController;
import com.kbtg.bootcamp.posttest.user.UserTicketResultResponse;
import com.kbtg.bootcamp.posttest.user.UserTicketService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    MockMvc mockMvc;

    @Mock
    UserTicketService userTicketService;

    @Mock
    LotteryRepository lotteryRepository;

    @BeforeEach
    void setUp(){
        UserController userController = new UserController(userTicketService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    @DisplayName("POST users/{userId}/lotteries/{ticketId} ซื้อลอตเตอรี่ เพื่อที่จะได้ลุ้นถูกหวย")
    void buyLotteries() throws Exception {

        long userTicketMock = 1L;
        int userTicketId = 1;
        String userIdRequest = "1234567890";
        String ticketNumberRequest = "123456";

        // set up mock lotteryService
        when(userTicketService.buyLottery(any(), any()))
                .thenReturn(new PurchaseResponse(userTicketMock));


        mockMvc.perform(
                        post("/users/"+userIdRequest+"/lotteries/"+ticketNumberRequest)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)

                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id", is(userTicketId)));

    }


    @Test
    @DisplayName("GET users/{userId}/lotteries ดูรายการลอตเตอรี่ทั้งหมดที่เคยซื้อ")
    void getMyLotteries() throws Exception {

        String userIdRequest = "1234567890";

        // set up mock lotteryService
        when(userTicketService.getLotteryByUserId(any()))
                .thenReturn(new UserTicketResultResponse());

        mockMvc.perform(
                        get("/users/"+userIdRequest+"/lotteries/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @DisplayName("DELETE users/{userId}/lotteries ขายคืนลอตเตอรี่เพื่อได้เงินคืน")
    void deleteMyLotteries() throws Exception {

        String userIdRequest = "1234567890";
        String ticketNumberRequest = "123456";

        // set up mock lotteryService
        when(userTicketService.getLotteryByUserId(any()))
                .thenReturn(new UserTicketResultResponse());

        mockMvc.perform(
                        delete("/users/"+userIdRequest+"/lotteries/"+ticketNumberRequest)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
