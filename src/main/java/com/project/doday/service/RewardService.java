package com.project.doday.service;

import com.project.doday.domain.Member;
import com.project.doday.domain.Reward;
import com.project.doday.domain.Solution;
import com.project.doday.dto.RewardConvertReq;
import com.project.doday.dto.RewardRes;
import com.project.doday.repository.MemberRepository;
import com.project.doday.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;
    private final MemberRepository memberRepository;

    /**
     * 리워드 내역 확인하기
     */
    public List<RewardRes> getMyReward(Long memberId) {
        List<Reward> rewards = rewardRepository.findAll();
        List<RewardRes> myReward = new ArrayList<>();

        for(Reward reward : rewards){
            if(reward.getMember().getId() == memberId) {
                RewardRes rewardRes = new RewardRes(reward.getId(), reward.getCreatedDate(),
                        reward.getPrice(), reward.getType(), reward.getLocation());
                myReward.add(rewardRes);
            }
        }
        return myReward;
    }

    /**
     * 리워드 전환하기
     */
    public Long convertReward(Long memberId, RewardConvertReq rewardConvertReq) {
        Member member = memberRepository.findById(memberId).get();
        member.covertReward(rewardConvertReq.getAmount());
        return member.getNowReward();
    }
}
