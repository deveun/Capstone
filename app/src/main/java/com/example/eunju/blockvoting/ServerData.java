package com.example.eunju.blockvoting;

public class ServerData {
    static String vote_sdate;
    static String vote_edate;
    static String vote_title;
    static String vote_content;

    String getVote_title() {
        return this.vote_title;
    }
    String getVote_sdate() {
        return this.vote_sdate;
    }
    String getVote_edate() {
        return this.vote_edate;
    }
    String getVote_content() {
        return this.vote_content;
    }

    //날짜, 제목, 내용 순(생성자)
    ServerData(String vote_sdate, String vote_edate, String vote_title, String vote_content) {
        this.vote_sdate= vote_sdate;
        this.vote_edate= vote_edate;
        this.vote_title= vote_title;
        this.vote_content= vote_content;
    }
    ServerData() {}
}