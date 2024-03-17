import java.util.ArrayList;

public class SensorTemperaturePairTable {
    private static ArrayList<SensorTemperaturePair> STPairs = new ArrayList<SensorTemperaturePair>();

    /**
     * This method adds a new Sensor-Temperature pair object to the Arraylist.
     * @param sensor
     * @param temperature
     */
    public static void addSensorTemperaturePair(Sensor sensor, Temperature temperature) {
        SensorTemperaturePair pair = new SensorTemperaturePair(sensor, temperature);
        STPairs.add(pair);
        pair.getSensor().setDeployedStatus(true);
    }
    
    /**
     * This method returns the temperature for a given sensor.
     * @param sensor
     * @return
     */
    public static Double getTemperature(Sensor sensor) {
        for (SensorTemperaturePair pair : STPairs) {
            if (pair.getSensor().equals(sensor)) {
                return pair.getTemperature().getTemperature();
            }
        }
        return null; // Return null if no temperature is found for the given sensor
    }

    
    /**
     * This method replaces a damaged sensor by a new one.
     * @param oldSensor
     * @param newSensor
     */
    public static void replaceSensor(Sensor oldSensor, Sensor newSensor) {
        for (SensorTemperaturePair pair : STPairs) {
            if (pair.getSensor().equals(oldSensor)) {
                pair.setSensor(newSensor);
                pair.getSensor().setDeployedStatus(true);
                break;
            }
        }
    }
}
