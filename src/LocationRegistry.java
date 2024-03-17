import java.util.ArrayList;

/**
 * This class keeps a registry (list) of all the existing locations
 * and insures the addition of those locations as well
 */
public class LocationRegistry {
    private static ArrayList<Location> locations = new ArrayList<Location>();

    /**
     * This method adds a location to the location registry
     * @param location the location to add
     */
    public static void addLocation(Location location) {
        if(!locations.contains(location))
            locations.add(location);
        else
        System.out.println("The location " + location.getLocationName() + " with location ID " + location.getLocationID() + " already exists in the registry.");
    }

    /**
     * This methods loops through the list while looking for a location that has a matching ID 
     * and returns it. If no matching ID is found, the method returns null.
     * @param locationID
     * @return
     */
    public static Location getLocation(String locName) {
        for (Location location : locations) {
            if (location.getLocationName().equals(locName)) {
                return location; // Match found
            }
        }
        return null; // No match found
    }
    
}