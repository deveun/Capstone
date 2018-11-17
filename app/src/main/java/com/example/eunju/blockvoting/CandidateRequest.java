package com.example.eunju.blockvoting;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CandidateRequest extends StringRequest {
    final static private String URL = "http://deveun.dothome.co.kr/Candidate.php";
    private Map<String, String> parameters;

    public CandidateRequest(int voteNum, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("voteNum", String.valueOf(voteNum));
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
