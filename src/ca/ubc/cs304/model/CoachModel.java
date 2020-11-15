package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * Coach relationship
 */
public class CoachModel {
    private final String nationality;
    private final String firstName;
    private final String lastName;
    private final int licenseNum; // primary key

    public CoachModel(String nationality, String firstName, String lastName, int licenseNum){
        this.nationality = nationality;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNum = licenseNum;
    }

    public int getLicenseNum() {
        return licenseNum;
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
}
