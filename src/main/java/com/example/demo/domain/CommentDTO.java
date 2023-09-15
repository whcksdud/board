package com.example.demo.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Comment")
public class CommentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Long num; // 자동으로 증가되는 정수

    @Column(name = "boardid")
    private Long boardId; // PK

    @Column(name = "writeDate")
    private Date writeDate; // 작성일

    @Column(name = "commentContent")
    private String commentContent; // 내용

    // 생성자, 게터(getter) 및 세터(setter) 메서드 등은 필요에 따라 추가할 수 있습니다.

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

}