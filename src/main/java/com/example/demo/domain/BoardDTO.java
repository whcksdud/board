package com.example.demo.domain;

import jakarta.persistence.*;
import java.util.Date;

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
}
