package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * FoorballPlayer_PlaysFor relationship
 */
public class FootballPlayerModel {
    private final int licenseNum; // PRIMARY KEY
    private final int jerseyNum;
    private final String firstName;
    private final String lastName;
    private final String nationality;
    private final String dateOfBirth;

    // the following fields maybe -1, which indicates null.
    private final int goalsConceded;
    private final int goalsSaved;
    private final int bigChances;
    private final int keyPasses;
    private final int interceptions;
    private final int recoveries;
    private final int successfulTackles;
    private final int blocks;
    private final int clearances;

    private final String contractStart;
    private final String contractEnd;
    private final int teamID; // (foreign to team)

    public FootballPlayerModel(int licenceNum,
                               int jerseyNum, String firstName, String lastName, String nationality, String dateOfBirth,
                               int goalsConceded, int goalsSaved, int bigChances, int keyPasses, int interceptions,
                               int recoveries, int successfulTackles, int blocks, int clearances,
                               String contractStart, String contractEnd, int teamID) {
        this.licenseNum = licenceNum;
        this.jerseyNum = jerseyNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;

        this.goalsConceded = goalsConceded;
        this.goalsSaved = goalsSaved;
        this.bigChances = bigChances;
        this.keyPasses = keyPasses;
        this.interceptions = interceptions;
        this.recoveries = recoveries;
        this.successfulTackles = successfulTackles;
        this.blocks = blocks;
        this.clearances = clearances;

        this.contractEnd = contractEnd;
        this.contractStart = contractStart;
        this.teamID = teamID;
    }

    public int getLicenseNum() {
        return licenseNum;
    }

    public int getJerseyNum() {
        return jerseyNum;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getNationality() {
        return nationality;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }
    public int getGoalsSaved() {
        return goalsSaved;
    }
    public int getBigChances() {
        return bigChances;
    }
    public int getKeyPasses() {
        return keyPasses;
    }
    public int getInterceptions() {
        return interceptions;
    }
    public int getRecoveries() {
        return recoveries;
    }
    public int getSuccessfulTackles() {
        return successfulTackles;
    }
    public int getBlocks() {
        return blocks;
    }
    public int getClearances() {
        return clearances;
    }

    public String getContractStart() {
        return contractStart;
    }
    public String getContractEnd() {
        return contractEnd;
    }
    public int getTeamID() {
        return teamID;
    }
}
