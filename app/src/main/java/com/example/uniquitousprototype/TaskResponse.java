package com.example.uniquitousprototype;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YUNKYUSEOK on 2017-07-13.
 */

public class TaskResponse {
    @SerializedName("results")
    private List<Task> results;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("count")
    private int taskCount;

    public List<Task> getResults() {
        return results;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public int getTaskCount() {
        return taskCount;
    }

}
