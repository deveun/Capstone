package com.example.eunju.blockvoting;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class VotingRequest extends StringRequest {
    final static private String URL = "http://23.20.145.133:3000/doVote";
    private Map<String, String> parameters;

    public VotingRequest(String userID, int voteID, int candidateID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("voteID", String.valueOf(voteID));
        parameters.put("candidateID", String.valueOf(candidateID));
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
