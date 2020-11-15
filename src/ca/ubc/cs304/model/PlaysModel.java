package ca.ubc.cs304.model;
/**
 * The intent for this class is to update/store information about a single
 * Plays relationship, this connects match and team
 */
public class PlaysModel {
    private final int matchID; //foreign and primary
    private final int teamID; //foreign and primary

    public PlaysModel(int matchID, int teamID){
        this.matchID = matchID;
        this.teamID = teamID;
    }

    public int getMatchID() {
        return matchID;
    }
    public int getTeamID() {
        return teamID;
    }
}
