package com.example.eunju.blockvoting;

public class CandidateItem {
    int userNum;
    int candidateNum;
    String candidateName;

    int getUserNum() { return this.userNum;}

    int getCandidateNum() { return this.candidateNum;}

    String getCandidateName() { return this.candidateName;}

    CandidateItem(int userNum, int candidateNum, String candidateName)  {
        this.userNum= userNum;
        this.candidateName= candidateName;
        this.candidateNum= candidateNum;
    }
}
