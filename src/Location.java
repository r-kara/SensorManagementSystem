public class Location {
    private int locationID;
    private String locationName; //make it a string (locationName)

    //constructor
    public Location(int locID, String locName) {
        this.locationID = locID;
        this.locationName = locName;
    }

    /**
     * gets location ID
     * @return locationID
     */
    public int getLocationID() {
        return locationID;
    }

    /**
     * sets location ID
     * @param locationID
     */
    public void setLocationID(int locID) {
        this.locationID = locID;
    }

    /**
     * gets location name
     * @return locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * sets location name
     * @param locationName
     */
    public void setLocationName(String locName) {
        this.locationName = locName;
    }
}