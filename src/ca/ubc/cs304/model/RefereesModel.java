package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * Referees relationship, this connects match and referee
 */
public class RefereesModel {
    private final int matchID; //foreign and primary
    private final int licenseNum; //foreign and primary

    public RefereesModel(int matchID, int licenseNum){
        this.matchID = matchID;
        this.licenseNum = licenseNum;
    }

    public int getMatchID() {
        return matchID;
    }
    public int getLicenseNum() {
        return licenseNum;
    }
}
