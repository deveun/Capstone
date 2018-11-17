package com.example.eunju.blockvoting;

public class CandidateItem {
    int candidateNum;
    String candidateName;

    int getCandidateNum() { return this.candidateNum;}

    String getCandidateName() { return this.candidateName;}

    CandidateItem(int candidateNum, String candidateName)  {
        this.candidateName= candidateName;
        this.candidateNum= candidateNum;
    }
}
