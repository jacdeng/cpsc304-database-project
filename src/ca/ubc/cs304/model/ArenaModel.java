package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single
 * Arena relationship
 */
public class ArenaModel {
    private final String address;
    private final String surfaceType;
    private final int capacity;
    private final String name; // primary key

    public ArenaModel(String address, String surfaceType, int capacity, String name){
        this.address = address;
        this.surfaceType = surfaceType;
        this.capacity = capacity;
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getSurfaceType() {
        return surfaceType;
    }
}
