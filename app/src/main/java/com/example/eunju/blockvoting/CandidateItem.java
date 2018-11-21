package com.example.eunju.blockvoting;

import android.graphics.Bitmap;

import java.net.URL;

public class CandidateItem {
    int userNum;
    int candidateNum;
    int score;
    String candidateName;
    String candidateInfo;
    Bitmap image;

    int getUserNum() { return this.userNum;}

    int getCandidateNum() { return this.candidateNum;}

    int getScore() { return this.score;}

    String getCandidateName() { return this.candidateName;}

    String getCandidateInfo() { return this.candidateInfo;}

    Bitmap getImage() { return this.image;}

    CandidateItem(int userNum, int candidateNum, String candidateName, String candidateInfo, Bitmap image, int score)  {
        this.userNum= userNum;
        this.candidateName= candidateName;
        this.candidateNum= candidateNum;
        this.candidateInfo=candidateInfo;
        this.image = image;
        this.score = score;
    }
}
