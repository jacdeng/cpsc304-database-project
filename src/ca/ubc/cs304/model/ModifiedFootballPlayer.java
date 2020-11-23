package ca.ubc.cs304.model;

public class ModifiedFootballPlayer {
    // Here we are making a new class, this will be used to return a list fo Modified teams instead of the result set.
    private String firstName;
    private String lastName;
    private int jerseyNum;
    private int goalsSaved;

    public int getGoalsSaved() {
        return goalsSaved;
    }

    public void setGoalsSaved(int goalsSaved) {
        this.goalsSaved = goalsSaved;
    }



    public ModifiedFootballPlayer(String firstName, String lastName, int jerseyNum, int goalsSaved) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jerseyNum = jerseyNum;
        this.goalsSaved = goalsSaved;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getJerseyNum() {
        return jerseyNum;
    }

    public void setJerseyNum(int jerseyNum) {
        this.jerseyNum = jerseyNum;
    }


}
