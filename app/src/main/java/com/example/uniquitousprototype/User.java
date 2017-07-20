package com.example.uniquitousprototype;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YUNKYUSEOK on 2017-07-20.
 */

public class User {
    @SerializedName("username")
    private String userName;
    @SerializedName("bills")
    private List<String> tasks;
    private String password;

    User (String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getTasks() {
        return tasks;
    }
}
