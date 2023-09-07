package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "VoteOption")
public class VoteOptionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 투표 옵션 ID

    @Column(name = "optionText")
    private String optionText; // 투표 옵션 텍스트

    @Column(name = "voteCount")
    private Integer voteCount; // 투표받은 횟수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private VoteDTO vote; // 투표 엔터티와의 관계

    // 생성자, 게터 및 세터 메서드

    public VoteOptionDTO() {
    }

    public VoteOptionDTO(String optionText, int voteCount) {
        this.optionText = optionText;
        this.voteCount = voteCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Integer getVoteCount() {
        if (this.voteCount != null) {
            return this.voteCount;
        } else {
            return 0; // 또는 다른 기본값 설정
        }
    }


    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public VoteDTO getVote() {
        return vote;
    }

    public void setVote(VoteDTO vote) {
        this.vote = vote;
    }

    public int calculateVoteResult(VoteOptionDTO option) {
        if (option.getVoteCount() != null) {
            return option.getVoteCount();
        } else {
            return 0; // 또는 다른 기본값 설정
        }
    }

}
