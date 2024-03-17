public class SensorLocationPair {
    private Sensor sensor;
    private Location location;
    
    public SensorLocationPair(Sensor sensor, Location location) {
        this.sensor = sensor;
        this.location = location;
    }

    public Sensor getSensor() {
        return sensor;
    }
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    
}
