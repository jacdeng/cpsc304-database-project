package ca.ubc.cs304.model;


/**
 * The intent for this class is to update/store information about a single
 * Penalty_Receives weak relationship to FootballPlayer
 */
public class PenaltyRecievesModel {
    private final String type; // NOT NULL
    private final String card;
    private final int duration; // NOT NULL
    private final int timestamp; // primary key
    private final int licenseNum; // primary key ((foreign))

    public PenaltyRecievesModel(String type, String card, int duration, int timestamp, int licenseNum){
        this.type = type;
        this.card = card;
        this.duration = duration;
        this.timestamp = timestamp;
        this.licenseNum = licenseNum;
    }

    public String getType(){
        return type;
    }
    public String getCard(){
        return card;
    }
    public int getTimestamp(){
        return timestamp;
    }
    public int getDuration(){
        return duration;
    }
    public int getLicenseNum(){
        return licenseNum;
    }
}
