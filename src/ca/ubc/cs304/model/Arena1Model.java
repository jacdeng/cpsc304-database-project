package ca.ubc.cs304.model;
/**
 * The intent for this class is to update/store information about a single
 * Arena1 relationship, Arena's BCNF
 */
public class Arena1Model {
    private final String address; // foreign key and primary key
    private final String city;

    public Arena1Model(String address, String city){
        this.address = address;
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

}
