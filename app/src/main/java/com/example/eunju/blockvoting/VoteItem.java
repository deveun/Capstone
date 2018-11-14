package com.example.eunju.blockvoting;

import java.util.Date;

public class VoteItem {
    String votename;
    String votecontent;
    String vote_sdate;
    String vote_edate;

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

    VoteItem(String votename, String votecontent, String vote_sdate, String vote_edate)  {
        this.votename = votename;
        this.votecontent = votecontent;
        this.vote_sdate = vote_sdate;
        this.vote_edate = vote_edate;
    }
}

