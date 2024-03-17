public class SensorTemperaturePair {
    private Sensor sensor;
    private Temperature temperature;

    public SensorTemperaturePair(Sensor sensor, Temperature temp) {
        this.sensor = sensor;
        this.temperature = temp;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

}
