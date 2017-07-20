package com.example.uniquitousprototype;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YUNKYUSEOK on 2017-07-20.
 */

public class LoginUser {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}
