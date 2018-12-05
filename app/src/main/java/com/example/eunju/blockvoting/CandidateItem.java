package com.example.eunju.blockvoting;

import android.graphics.Bitmap;

import java.net.URL;

public class CandidateItem {
    String userID;
    int candidateID;
    int voteID;
    String candidateName;
    String candidateInfo;
    //Bitmap image;

    String getUserID() { return this.userID;}

    int getCandidateNum() { return this.candidateID;}

    int getVoteID() { return this.voteID;}

    String getCandidateName() { return this.candidateName;}

    String getCandidateInfo() { return this.candidateInfo;}

    //Bitmap getImage() { return this.image;}

    //CandidateItem(String userID, int candidateNum, String candidateName, String candidateInfo, Bitmap image, int score)  {
    CandidateItem(String userID, int voteID, int candidateID, String candidateName, String candidateInfo)  {
        this.userID= userID;
        this.voteID= voteID;
        this.candidateName= candidateName;
        this.candidateID= candidateID;
        this.candidateInfo=candidateInfo;
        //this.image = image;
        //this.score = score;
    }
}
