package com.example.eunju.blockvoting;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ResultRequest extends StringRequest {
    final static private String URL = "http://23.20.145.133:3000/seeballots";
    private Map<String, String> parameters;

    public ResultRequest(int voteNum, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("voteID", String.valueOf(voteNum));
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}