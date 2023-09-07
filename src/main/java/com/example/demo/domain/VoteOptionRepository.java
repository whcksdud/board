package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteOptionRepository extends JpaRepository<VoteOptionDTO, Long> {
    // vote_id를 기반으로 특정 투표 항목의 옵션을 가져오는 메서드 예제
    List<VoteOptionDTO> findByVoteId(Long voteId);
}
