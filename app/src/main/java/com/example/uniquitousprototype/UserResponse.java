package com.example.uniquitousprototype;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YUNKYUSEOK on 2017-07-20.
 */

public class UserResponse {
    @SerializedName("results")
    private List<User> results;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("count")
    private int userCount;

    public List<User> getResults() {
        return results;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public int getUserCount() {
        return userCount;
    }
}
