package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * Injury_Has weak relationship to FootballPlayer
 */
public class InjuryHasModel {
    private final String type; // primary key
    private final int timeStamp; // NOT NULL
    private final int licenseNum; // primary key (foreign)

    public InjuryHasModel(String type, int timeStamp, int licenseNum){
        this.type = type;
        this.timeStamp = timeStamp;
        this.licenseNum = licenseNum;
    }

    public String getType(){
        return type;
    }
    public int getTimeStamp(){
        return timeStamp;
    }
    public int getLicenseNum(){
        return licenseNum;
    }
}
