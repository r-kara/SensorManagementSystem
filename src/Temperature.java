import java.util.Random;

public class Temperature {
    private double temperature;

    //Default constructur
    public Temperature() {
        double minTemp = -40.0;
        double maxTemp = 60.0;
        Random random = new Random();
        this.temperature = minTemp + (maxTemp - minTemp) * random.nextDouble();
    }
    
    //Public constructor when setting the temp --> same as setter (date & time necessary??)
    public Temperature(double temp) {
        this.temperature = temp;
    }

    //Getter
    public double getTemperature() {
        return this.temperature;
    }

    //Setter
    public void setTemperature(double temp) {
        this.temperature = temp;
    }
}