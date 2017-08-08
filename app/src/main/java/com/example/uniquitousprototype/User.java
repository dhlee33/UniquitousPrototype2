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
    @SerializedName("password")
    private String password;
    @SerializedName("passwordConfirm")
    private String passwordConfirm;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;

    User (String userName, String password, String passwordConfirm, String name, String email) {
        this.userName = userName;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.name = name;
        this.email = email;
    }

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
