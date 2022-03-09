package labor2.Exercise3;

public class Vehicle {
    private String model;
    private String type;
    private int speed;
    private char fuelType;
    private static int numberOfVehicles = 0;

    public Vehicle(String model, String type, int speed, char fuelType) {
        this.model = model;
        this.type = type;
        this.speed = speed;
        this.fuelType = fuelType;
        numberOfVehicles++;
    }

    @Override
    public String toString() {
        return getModel() + " " + getType() + " speed " + getSpeed() + " fuel type " + getFuelType();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public char getFuelType() {
        return fuelType;
    }

    public void setFuelType(char fuelType) {
        this.fuelType = fuelType;
    }

    public static void main(String[] args) {
        Vehicle myVehicle = new Vehicle("Dacia", "Logan", 150, 'D');
        Vehicle brotherVehicle = new Vehicle("Tesla", "Model S", 300, 'D');

        if (myVehicle.equals(brotherVehicle)) {
            System.out.println("The vehicles are equal!");
        } else {
            System.out.println("The vehicles are not equal!");
        }
    }
}
