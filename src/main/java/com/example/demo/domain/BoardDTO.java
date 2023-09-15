package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BOARD")
public class BoardDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String title;
    @Temporal(TemporalType.DATE)
    private Date writerDate;
    private String id;
    private String pw;
    private String context;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isNotice;

    /** 원글 번호 **/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer originNo;
    /** 원글(답글포함)에 대한 순서 **/
    @Column(nullable = true)
    private Integer groupOrd;
    /** 답글 계층 **/
    @Column(nullable = true)
    private Integer groupLayer;

    public void setNum(Long num) {
        this.num = num;
    }

    public Integer getOriginNo() {
        return originNo;
    }

    public void setOriginNo(Integer originNo) {
        this.originNo = originNo;
    }

    public Integer getGroupOrd() {
        return groupOrd;
    }

    public void setGroupOrd(Integer groupOrd) {
        this.groupOrd = groupOrd;
    }

    public Integer getGroupLayer() {
        return groupLayer;
    }

    public void setGroupLayer(Integer groupLayer) {
        this.groupLayer = groupLayer;
    }
    public boolean getisNotice() {
        return isNotice;
    }

    public void setIsNotice(boolean notice) {
        this.isNotice = notice;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getWriterDate() {
        return writerDate;
    }

    public void setWriterDate(Date writerDate) {
        this.writerDate = writerDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() { return pw; }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Board{" +
                ", title='" + title + '\'' +
                ", writerDate=" + writerDate +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", context='" + context + '\'' +
                '}';
    }

    public void setNum(long num) {
        this.num = num;
    }
    public Long getNum() {
        return num;
    }

    public void setNotice(boolean isNotice) {
        this.isNotice = isNotice;
    }
}
