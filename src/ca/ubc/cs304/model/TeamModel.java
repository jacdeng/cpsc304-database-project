package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * Team_HasManages relationship
 */
public class TeamModel {
    private final int phoneNum;
    private final String website;
    private final String teamName;
    private final int teamID; // primary key

    private final String since;
    private final String arenaName; // foreign key

    private final String contractStart;
    private final String contractEnd;
    private final int coachLicenseNum; // foreign key

    public TeamModel(String teamName, int teamID, String website, int phoneNum,
                     String since, String arenaName,
                     String contractStart, String contractEnd, int coachLicenseNum){
        this.phoneNum = phoneNum;
        this.website = website;
        this.teamName = teamName;
        this.teamID = teamID;

        this.since = since;
        this.arenaName = arenaName;

        this.contractEnd = contractEnd;
        this.contractStart = contractStart;
        this.coachLicenseNum = coachLicenseNum;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public int getTeamID() {
        return teamID;
    }

    public String getArenaName() {
        return arenaName;
    }

    public int getCoachLicenseNum() {
        return coachLicenseNum;
    }

    public String getContractEnd() {
        return contractEnd;
    }

    public String getContractStart() {
        return contractStart;
    }

    public String getSince() {
        return since;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getWebsite() {
        return website;
    }
}
