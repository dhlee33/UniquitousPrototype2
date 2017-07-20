package com.example.uniquitousprototype;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YUNKYUSEOK on 2017-07-13.
 */

public class TaskResponse {
    @SerializedName("results")
    private List<Task> results;

    public void setResults(List<Task> results) {
        this.results = results;
    }

    public List<Task> getResults() {
        return results;
    }

}
