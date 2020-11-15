package ca.ubc.cs304.model;
/**
 * The intent for this class is to update/store information about a single
 * Match1 relationship, Match's BCNF
 */
public class Match1Model {
    private final String arena;
    private final String homeTeam; // primary and foreign key

    public Match1Model(String arena, String homeTeam){
        this.arena = arena;
        this.homeTeam = homeTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }
    public String getArena() {
        return arena;
    }
}
