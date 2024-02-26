package com.kbtg.bootcamp.posttest.admin;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LotteryRequestDto {


    @NotEmpty @NotNull
    @Pattern(regexp="[\\d]{6}", message="This field should contain six digits!")
    private String ticket;

    @Positive(message = "Price should be a positive value")
    @Min(value = 0, message = "price is more than 0")
    private int price;


    @NotNull(message = "Amount in your is require")
    @Positive(message = "Amount should be a positive value")
    @Min(value = 1, message = "amount is more than 1")
    private int amount;
    
}
