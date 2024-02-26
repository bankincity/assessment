package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lottery")
public class Lottery {

    @Id @NotNull
    @Getter @Setter
    @Column(length = 6)
    private String ticket;

    @NotNull
    @Getter @Setter
    private int price;

    @NotNull
    @Getter @Setter
    private int amount;


}
