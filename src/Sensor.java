public class Sensor {
    private int sensorID;
    private boolean deployedStatus;

    public Sensor(int sensorID, boolean deployedStatus){
        this.sensorID = sensorID;
        this.deployedStatus = deployedStatus;
    }

    public int getSensorID() {
        return sensorID;
    }

    public void setsensorID(int sensorID){
        this.sensorID = sensorID;
    }

    public boolean getDeployedStatus(){
        return deployedStatus;
    }
    
    public void setDeployedStatus(boolean deployedStatus) {
        this.deployedStatus = deployedStatus;
    }
}