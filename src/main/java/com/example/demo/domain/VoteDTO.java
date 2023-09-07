package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Vote")
public class VoteDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 투표 ID

    @Column(name = "title")
    private String title; // 투표 제목

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VoteOptionDTO> options; // 투표 옵션 목록

    // 생성자, 게터 및 세터 메서드

    public VoteDTO() {
    }

    public VoteDTO(String title, List<VoteOptionDTO> options) {
        this.title = title;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VoteOptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOptionDTO> options) {
        this.options = options;
    }
}
