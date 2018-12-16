package com.example.eunju.blockvoting;

import android.graphics.Bitmap;

import java.net.URL;

public class CandidateItem {
    String userID;
    String candidateID;
    String voteID;
    String candidateName;
    String candidateInfo;
    Bitmap image;
    int score;

    String getUserID() { return this.userID;}

    String getCandidateNum() { return this.candidateID;}

    String getVoteID() { return this.voteID;}

    String getCandidateName() { return this.candidateName;}

    String getCandidateInfo() { return this.candidateInfo;}

    Bitmap getImage() { return this.image;}

    int getScore() { return this.score;}

    //CandidateItem(String userID, int candidateNum, String candidateName, String candidateInfo, Bitmap image, int score)  {
    CandidateItem(String userID, String voteID, String candidateID, String candidateName, String candidateInfo, Bitmap image)  {
        this.userID= userID;
        this.voteID= voteID;
        this.candidateName= candidateName;
        this.candidateID= candidateID;
        this.candidateInfo=candidateInfo;
        this.image = image;
        //this.score = score;
    }

    CandidateItem(String userID, String voteID, String candidateID, String candidateName, String candidateInfo, Bitmap image, int score)  {
        this.userID= userID;
        this.voteID= voteID;
        this.candidateName= candidateName;
        this.candidateID= candidateID;
        this.candidateInfo=candidateInfo;
        this.image = image;
        this.score = score;
    }
}
