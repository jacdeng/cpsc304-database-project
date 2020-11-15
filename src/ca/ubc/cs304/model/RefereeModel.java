package ca.ubc.cs304.model;
/**
 * The intent for this class is to update/store information about a single
 * Referee relationship
 */
public class RefereeModel {
    private final String city;
    private final String firstName;
    private final String lastName;
    private final int licenseNum;

    public RefereeModel(String firstName, String lastName, String city, int licenseNum){
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
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

    public String getCity() {
        return city;
    }
}
