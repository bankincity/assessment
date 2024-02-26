package com.kbtg.bootcamp.posttest.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user_ticket")
public class UserTicket {

    @Id @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter
    private long id;

    @Setter @Getter
    @Column(name = "user_id", length = 10)
    private String userId;

    @Setter @Getter
    private String ticket;
}
