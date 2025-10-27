package com.example.triz;

public class UserMemo {
    private String id;
    private String target;
    private int improvingParameterId;
    private int worseningParameterId;
    private String appliedPrinciples;
    private long timestamp;
    private boolean isFavorite;

    public UserMemo(String id, String target, int improvingParameterId, int worseningParameterId,
                    String appliedPrinciples, long timestamp) {
        this.id = id;
        this.target = target;
        this.improvingParameterId = improvingParameterId;
        this.worseningParameterId = worseningParameterId;
        this.appliedPrinciples = appliedPrinciples;
        this.timestamp = timestamp;
        this.isFavorite = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getImprovingParameterId() {
        return improvingParameterId;
    }

    public void setImprovingParameterId(int improvingParameterId) {
        this.improvingParameterId = improvingParameterId;
    }

    public int getWorseningParameterId() {
        return worseningParameterId;
    }

    public void setWorseningParameterId(int worseningParameterId) {
        this.worseningParameterId = worseningParameterId;
    }

    public String getAppliedPrinciples() {
        return appliedPrinciples;
    }

    public void setAppliedPrinciples(String appliedPrinciples) {
        this.appliedPrinciples = appliedPrinciples;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
