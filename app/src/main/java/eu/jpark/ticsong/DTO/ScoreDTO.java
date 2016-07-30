package eu.jpark.ticsong.DTO;

import java.io.Serializable;

public class ScoreDTO implements Serializable{

    private String userId;
    private int score;
    private int userLevel;

    public ScoreDTO() {
        // TODO Auto-generated constructor stub
    }

    public ScoreDTO(String userId, int score, int userLevel) {
        super();
        this.userId = userId;
        this.score = score;
        this.userLevel = userLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "Score [userId=" + userId + ", score=" + score + ", userLevel="
                + userLevel + "]";
    }
}