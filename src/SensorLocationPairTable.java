import java.util.ArrayList;

public class SensorLocationPairTable {
    private static ArrayList<SensorLocationPair> SLPairs = new ArrayList<SensorLocationPair>();

    public static void addSensorLocationPair(Sensor sensor, Location location) {
        SensorLocationPair sl = new SensorLocationPair(sensor, location);
        SLPairs.add(sl);
    }

    public static Sensor getSensor(Location location) {
        for (SensorLocationPair pair : SLPairs) {
            if (pair.getLocation().equals(location)) {
                return pair.getSensor();
            }
        }
        return null; // Return null if no sensor is found for the given location
    }

    public static Location getLocation(Sensor sensor) {
        for (SensorLocationPair pair : SLPairs) {
            if (pair.getSensor().equals(sensor)) {
                return pair.getLocation();
            }
        }
        return null; // Return null if no location is found for the given sensor
    }

    /**
     * This method replaces a damaged sensor by a new one. This does not affect the Location in the pair.
     * @param oldSensor
     * @param newSensor
     */
    public static void replaceSensor(Sensor oldSensor, Sensor newSensor) {
        for (SensorLocationPair pair : SLPairs) {
            if (pair.getSensor().equals(oldSensor)) {
                pair.setSensor(newSensor);
                break;
            }
        }
    }

    /**
     * This method returns a list of all the locations in the system.
     * @return
     */
    public static ArrayList<Location> getAllLocations() {
        ArrayList<Location> allLocations = new ArrayList<>();
        for (SensorLocationPair pair : SLPairs) {
            Location loc = pair.getLocation();
            allLocations.add(loc);
        }
        return allLocations;
    }
    
}