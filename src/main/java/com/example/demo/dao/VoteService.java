package com.example.demo.dao;

import com.example.demo.domain.VoteOptionDTO;
import com.example.demo.domain.VoteOptionRepository;
import com.example.demo.domain.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoteOptionRepository voteOptionRepository;

    @Transactional
    public void vote(Long voteId, Long optionId) {
        // 투표 항목의 votecount 증가 처리
        VoteOptionDTO option = voteOptionRepository.findById(optionId).orElse(null);
        if (option != null) {
            option.setVoteCount(option.getVoteCount() + 1);
            voteOptionRepository.save(option);
        }
    }

    public int getTotalVotes(Long voteId) {
        // 해당 투표 항목의 총 투표 결과 계산
        List<VoteOptionDTO> options = voteOptionRepository.findByVoteId(voteId);
        int totalVotes = 0;
        for (VoteOptionDTO option : options) {
            totalVotes += option.getVoteCount();
        }
        return totalVotes;
    }

    public List<Integer> getVoteCounts(Long voteId) {
        List<VoteOptionDTO> options = voteOptionRepository.findByVoteId(voteId);
        List<Integer> voteCounts = new ArrayList<>();

        for (VoteOptionDTO option : options) {
            voteCounts.add(option.getVoteCount());
        }

        return voteCounts;
    }
}
