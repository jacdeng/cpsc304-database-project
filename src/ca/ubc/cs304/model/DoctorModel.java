package ca.ubc.cs304.model;

import javax.print.Doc;

/**
 * The intent for this class is to update/store information about a single
 * Doctor_Treat relationship
 */
public class DoctorModel {
    private final String firstName;
    private final String lastName;
    private final String fieldOfPractice;
    private final int licenseNum; // primary key

    private final String startDate;
    private final String endDate;
    private final int teamID; // foreign key

    public DoctorModel(String firstName, String lastName, String fieldOfPractice, int licenseNum,
                       String startDate, String endDate, int teamID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.fieldOfPractice = fieldOfPractice;
        this.licenseNum = licenseNum;

        this.startDate = startDate;
        this.endDate = endDate;
        this.teamID = teamID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFieldOfPractice() {
        return fieldOfPractice;
    }

    public int getLicenseNum() {
        return licenseNum;
    }

    public int getTeamID() {
        return teamID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
