package ca.ubc.cs304.model;
/**
 * The intent for this class is to update/store information about a single
 * Match relationship
 */
public class MatchModel {
    private final String homeTeam;
    private final String awayTeam;
    private final String score;
    private final String date;
    private int matchID; //primary key

    public MatchModel(String homeTeam, String awayTeam, String score, String date, int matchID){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
        this.date = date;
        this.matchID = matchID;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getMatchID() {
        return matchID;
    }

    public String getDate() {
        return date;
    }

    public String getScore() {
        return score;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }
}
