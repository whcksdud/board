package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VoteController {

    @Autowired
    private VoteService voteService; // 투표 서비스 클래스를 주입받아 사용

    @PostMapping("/vote")
    @ResponseBody
    public String vote(@RequestParam("num") String num, @RequestParam("optionId") String optionText) {
        try {
            // num과 optionId를 사용하여 해당 투표 항목의 votecount 증가 처리
            voteService.vote(Long.parseLong(num), Long.parseLong(optionText));
            int totalVotes = voteService.getTotalVotes(Long.parseLong(num));
            return String.valueOf(totalVotes);
        } catch (Exception e) {
            return "오류 발생: " + e.getMessage();
        }
    }

    @GetMapping("/getVoteCounts")
    @ResponseBody
    public List<Integer> getVoteCounts(@RequestParam("num") Long num) {
        // 특정 투표 항목의 각 옵션 득표 수를 가져오는 서비스 메서드 호출
        List<Integer> voteCounts = voteService.getVoteCounts(num);
        return voteCounts;
    }
}
