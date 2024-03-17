import java.util.*;
import java.text.DecimalFormat;

public class Console {

    public static void main(String[] args) {
        
        //Add sensors to the SensorRegistry
        Sensor s1 = new Sensor(1, false);
        Sensor s2 = new Sensor(2, false);
        Sensor s3 = new Sensor(3, false);
        Sensor s4 = new Sensor(4, false);
        Sensor s5 = new Sensor(5, false);
        SensorRegistry.addSensor(s1);
        SensorRegistry.addSensor(s2);
        SensorRegistry.addSensor(s3);
        SensorRegistry.addSensor(s4); //not deployed
        SensorRegistry.addSensor(s5); //not deployed


        //Add locations to the LocationRegistry
        Location l1 = new Location(10,"Montreal");
        Location l2 = new Location(20,"Toronto");
        Location l3 = new Location(30,"Ottawa");
        LocationRegistry.addLocation(l1);
        LocationRegistry.addLocation(l2);
        LocationRegistry.addLocation(l3);
        
        //Create random Temperature objects
        Temperature t1 = new Temperature(10.6);
        Temperature t2 = new Temperature(-6.2);
        Temperature t3 = new Temperature(20.7);

        //Map 3 sensors to locations in the SensorLocationPairTable
        SensorLocationPairTable.addSensorLocationPair(s1, l1);
        SensorLocationPairTable.addSensorLocationPair(s2, l2);
        SensorLocationPairTable.addSensorLocationPair(s3, l3);
        //Map 3 sensors to temp in SensorTemperaturePairTable
        SensorTemperaturePairTable.addSensorTemperaturePair(s1, t1);
        SensorTemperaturePairTable.addSensorTemperaturePair(s2, t2);
        SensorTemperaturePairTable.addSensorTemperaturePair(s3, t3);


        boolean valid = true;
        int option;

        try{

            Scanner sc= new Scanner(System.in);

            System.out.println("\n\n   +++ WELCOME TO THE SENSOR MANAGEMENT APPLICATION !! +++");

            while (valid) {
                System.out.println("\n\n\nPlease enter a number from the following menu:\n");
                System.out.println("1. Deploy a sensor.");
                System.out.println("2. Replace a sensor with another one.");
                System.out.println("3. Read the temperature at a location.");
                System.out.println("4. Display all the locations and their corresponding temperatures.");
                System.out.println("5. Exit\n");            
                // System.out.println("4. Add a sensor.");
                // System.err.println("5. Add a location. ");
                
                System.out.print("Option: ");
                option = sc.nextInt();

                if (option<=5 && option>=1){

                    switch(option) {

                        case 1: {
                            System.out.print("\nEnter the ID of sensor you would like to deploy: ");
                            int sID = sc.nextInt();
                            System.out.print("\nEnter the name of the location you would like to deploy a sensor at: ");
                            //string validarion?
                            String locName = sc.next();
                            double minTemp = -40.0;
                            double maxTemp = 60.0;
                            Random random = new Random();
                            Double temp = minTemp + (maxTemp - minTemp) * random.nextDouble();
                            deploySensor(sID,locName,temp);
                        } break;

                        case 2: {
                            System.out.print("\nEnter the ID of sensor you would like to remove: ");
                            int oldSensorID = sc.nextInt();
                            System.out.print("\nEnter the ID of the replacement sensor: ");
                            int newSensorID = sc.nextInt();
                            replaceSensor(oldSensorID, newSensorID);
                        } break;

                        case 3: {
                            System.out.print("\nEnter the name of the location you would like to read the temperature from: ");
                            //Show list of locations in Registry
                            sc.nextLine();
                            String locName = sc.nextLine();
                            Double tempValue = readTemperature(locName);
                            if (tempValue != null) {
                                DecimalFormat df = new DecimalFormat("0.0");
                                System.out.println("\nThe temperature in " + locName + " is " + df.format(tempValue) + " degrees.");
                            }
                        } break;

                        case 4: {
                            System.out.println();
                            allLocationTemperaturePairs();
                        } break;

                        case 5: {
                            System.out.println("\nClosing the application.\n");
                            valid = false;
                        } break; 

                    }

                }
                else{
                    System.out.println("\nThe option selected is invalid. Please try again.\n");
                } 
            }

            sc.close();
        }catch(Exception e){
            System.out.println("An occured occured. The system will now shut down. ");
            System.exit(0);
        }

    }

    /**
     * This method deploys a sensor at the given location and records the temperature. If the sensor does not 
     * exist, it creates one with the given ID. If the location is not mapped yet, it adds it to the registry first.
     * @param sensorID
     * @param locName
     * @param temp
     */
    public static void deploySensor(int sensorID, String locName, double temp) {
        //get sensor with the sensorID
        Sensor s1 = SensorRegistry.getSensor(sensorID);
        //check deployed status
        if ((s1 != null) && (s1.getDeployedStatus() == true)) {
            System.out.println("\nThe sensor " + s1.getSensorID() + " is already deployed.\n");
            return;
            //System.out.println("\nThere is no sensor with the ID " + sensorID + ".\n");
        } else {
            //if null, create sensor and add to registry
            if (s1 == null) {
                s1 = new Sensor(sensorID, false);
                SensorRegistry.addSensor(s1);
            }
            //check that location is not already mapped (in registry)
            Location loc1 = LocationRegistry.getLocation(locName);
            if (loc1 != null) {
                //already mapped
                System.out.println("There is already a sensor deployed in " + loc1.getLocationName() + ".\n");
            } else {
                //Create location and add to registry
                double min = 0;
                double max = 100;
                int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
                loc1 = new Location(random_int, locName);
                LocationRegistry.addLocation(loc1);
                //add sl to map
                SensorLocationPairTable.addSensorLocationPair(s1,loc1);
                //add st to read
                Temperature newTemp = new Temperature(temp);
                SensorTemperaturePairTable.addSensorTemperaturePair(s1, newTemp);
                System.out.print("\nThe sensor " + sensorID + " has been deployed in " + locName + " successfully.\n");
            }
            
        }
    }

    /**
     * This method takes in a Location ID and returns the temperature at the corresponding location.
     * @param locationID
     */
    public static Double readTemperature(String locName) {       
        //get the location that has this locationID
        Location loc1 = LocationRegistry.getLocation(locName);
        if (loc1 == null) {
            System.out.println("\nThere is no location with the name " + locName + ".\n");
            return null;
        } else {
            Sensor s1 = SensorLocationPairTable.getSensor(loc1);
            if (s1 == null) {
                System.out.println("\nThere is no sensor at this location.\n");
                return null;
            } else {
                return (SensorTemperaturePairTable.getTemperature(s1));
            }
        }
    }

    //-------------------------------------------------

    /**
     * This method replaces a damaged sensor in the system by a new one. It also permanently removes the old sensor.
     * @param oldSensorID
     * @param newSensorID
     */
    public static void replaceSensor(int oldSensorID, int newSensorID) {
        //get old sensor object & check if sensor exists
        Sensor oldSensor = SensorRegistry.getSensor(oldSensorID);
        if (oldSensor == null) {
            System.out.println("\nThere is no sensor with the ID " + oldSensorID + ".\n");
            return;
        }
        //delete old sensor from registry
        SensorRegistry.removeSensor(oldSensor);
        //get new sensor object or create one
        Sensor newSensor = SensorRegistry.getSensor(newSensorID);
        if ((newSensor != null) && (newSensor.getDeployedStatus() == true)) {
            System.out.println("\nThe sensor " + newSensor.getSensorID() + " is already deployed.\n");
            return;
        }
        //if null, create sensor and add to registry
        if (newSensor == null) {
            newSensor = new Sensor(newSensorID, false);
            SensorRegistry.addSensor(newSensor);
        }
        //replace in SL table
        SensorLocationPairTable.replaceSensor(oldSensor,newSensor);
        //replace in ST table
        SensorTemperaturePairTable.replaceSensor(oldSensor,newSensor);
        System.out.print("\nThe sensor " + oldSensorID + " has been replaced successfully and removed from the system.\n");
    }

    /**
     * This method prints all locations in the system together with their corresponding temperature.
     */
    public static List<Pair<String, Double>> allLocationTemperaturePairs() {
        List<Pair<String, Double>> result = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.0");
        for (Location loc1 : SensorLocationPairTable.getAllLocations()) {
            Sensor s1 = SensorLocationPairTable.getSensor(loc1);
            Double temp = SensorTemperaturePairTable.getTemperature(s1);
            result.add(new Pair<>(loc1.getLocationName(), temp));
            // Print each pair
            System.out.println("Location: " + loc1.getLocationName() + " --> Temperature: " + df.format(temp));
        }
        return result;
    }

    /**
     * Helper inner class: Pair object holding a location name and a temperature value
     */
    private static class Pair<String,Double> {
        private String locName;
        private Double temp;

        public Pair(String locName, Double temp) {
            this.locName = locName;
            this.temp = temp;
        }

        public String getLocName() {
            return locName;
        }

        public Double getTemp() {
            return temp;
        }
    }

}
