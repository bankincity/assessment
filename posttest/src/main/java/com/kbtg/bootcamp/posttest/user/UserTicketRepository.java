package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {

    List<UserTicket> findByUserIdAndTicket(String userId, String ticket);

    List<UserTicket> findByUserId(String userId);
}
