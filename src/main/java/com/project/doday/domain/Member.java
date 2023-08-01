package com.project.doday.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;

    private Long nowReward;
    private Long totalReward;

    public void earnReward(Long amount) {
        nowReward += amount;
        totalReward += amount;
    }

    public void covertReward(Long amount) {
        nowReward -= amount;
    }

}
