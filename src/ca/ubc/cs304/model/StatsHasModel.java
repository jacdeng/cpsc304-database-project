package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * Stats_Has weak relationship to FootballPlayer
 */
public class StatsHasModel {
    private final String type; // primary key
    private final int amount;
    private final int licenseNum; // primary key (foreign)

    public StatsHasModel(String type, int amount, int licenseNum){
        this.type = type;
        this.amount = amount;
        this.licenseNum = licenseNum;
    }

    public String getType(){
        return type;
    }
    public int getAmount(){
        return amount;
    }
    public int getLicenseNum(){
        return licenseNum;
    }
}
