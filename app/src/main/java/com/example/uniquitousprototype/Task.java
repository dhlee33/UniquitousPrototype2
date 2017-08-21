package com.example.uniquitousprototype;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YUNKYUSEOK on 2017-07-13.
 */

public class Task {
    @SerializedName("title")
    private String title;
    @SerializedName("text")
    private String content;
    @SerializedName("category")
    private String category;
    @SerializedName("extraCost")
    private int cost;
    @SerializedName("reward")
    private int reward;
    @SerializedName("id")
    private int id;

    Task(String title,String content, String category, int cost, int reward) {
        this.content = content;
        this.category = category;
        this.cost = cost;
        this.reward = reward;
        this.title = title;
    }

    Task(String content, String category, int cost, int reward, int id) {
        this.content = content;
        this.category = category;
        this.cost = cost;
        this.reward = reward;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public int getCost() {
        return cost;
    }

    public int getReward() {
        return reward;
    }

    public int getId() {
        return id;
    }

}
