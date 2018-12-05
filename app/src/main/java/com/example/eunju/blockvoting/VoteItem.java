package com.example.eunju.blockvoting;

public class VoteItem {
    //int userNum;
    int voteNum;
    String userID;
    String votename;
    String votecontent;
    String vote_sdate;
    String vote_edate;

    String getuserID() { return this.userID;}

    int getvoteNum() { return this.voteNum;}

    String getName() {
        return this.votename;
    }

    String getSdate() {
        return this.vote_sdate;
    }

    String getEdate() {
        return this.vote_edate;
    }

    String getContent() { return this.votecontent;}

    VoteItem(String userID, int voteNum, String votename, String votecontent, String vote_sdate, String vote_edate)  {
        this.userID= userID;
        this.voteNum= voteNum;
        this.votename = votename;
        this.votecontent = votecontent;
        this.vote_sdate = vote_sdate;
        this.vote_edate = vote_edate;
    }
}

