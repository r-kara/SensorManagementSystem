import java.util.ArrayList;

/**
 * This class keeps a registry (list) of all the existing sensors
 * and insures the addition of those sensors from the registry
 */
public class SensorRegistry {
    private static ArrayList<Sensor> sensors = new ArrayList<Sensor>();

    /**
     * The method adds the sensor passed as the parameter to the registry only if it 
     * is not already present in the registry
     * @param sensor
     */
    public static void addSensor(Sensor sensor){
        if(!sensors.contains(sensor))
            sensors.add(sensor);
        else
            System.out.println("The sensor " + sensor.getSensorID() + " already exists in the registry.");;
    }

    /**
     * This methods loops through the list while looking for a sensor that has a matching ID 
     * and returns it. If no matching ID is found, the method returns null.
     * @param sensorID
     * @return
     */
    public static Sensor getSensor(int sensorID) {
        for (Sensor sensor : sensors) {
            if (sensor.getSensorID() == sensorID) {
                return sensor; // Match found
            }
        }
        return null; // No match found
    }

     /**
     * This method removes the sensor passed as a parameter from the registry only if it is present
     * in the registry
     * @param sensor
     */
    public static void removeSensor(Sensor sensor){
        if(sensors.contains(sensor))
            sensors.remove(sensor);
        else
            System.out.println("The sensor " + sensor.getSensorID() + " does not exist in the registry.");;
    }

}
