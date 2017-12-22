package com.github.bfh.study.slb;

public class SearchPersonResponse {

    private Float maxScore;

    private Boolean allowed;

    public  SearchPersonResponse(Float maxScore, Boolean allowed) {
        this.maxScore = maxScore;
        this.allowed = allowed;
    }

    public Float getMaxScore() {
        return maxScore;
    }

    public Boolean getAllowed() {
        return allowed;
    }
}
