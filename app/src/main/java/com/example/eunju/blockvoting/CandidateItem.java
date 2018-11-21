package com.example.eunju.blockvoting;

import android.graphics.Bitmap;

import java.net.URL;

public class CandidateItem {
    int userNum;
    int candidateNum;
    String candidateName;
    String candidateInfo;
    Bitmap imageUrl;

    int getUserNum() { return this.userNum;}

    int getCandidateNum() { return this.candidateNum;}

    String getCandidateName() { return this.candidateName;}

    String getCandidateInfo() { return this.candidateInfo;}

    Bitmap getImageUrl() { return this.imageUrl;}

    CandidateItem(int userNum, int candidateNum, String candidateName, String candidateInfo, Bitmap imageUrl)  {
        this.userNum= userNum;
        this.candidateName= candidateName;
        this.candidateNum= candidateNum;
        this.candidateInfo=candidateInfo;
        this.imageUrl = imageUrl;
    }
}
