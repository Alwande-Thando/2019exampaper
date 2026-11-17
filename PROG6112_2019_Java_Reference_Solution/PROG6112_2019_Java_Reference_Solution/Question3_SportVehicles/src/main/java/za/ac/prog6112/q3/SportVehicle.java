package za.ac.prog6112.q3;

/** Holds the four fields supplied for a sport vehicle. */
public class SportVehicle {
    private final String name;
    private final double price;
    private final double zeroToHundredSeconds;
    private final String engineSize;

    public SportVehicle(String name, double price, double zeroToHundredSeconds, String engineSize) {
        this.name = name;
        this.price = price;
        this.zeroToHundredSeconds = zeroToHundredSeconds;
        this.engineSize = engineSize;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getZeroToHundredSeconds() { return zeroToHundredSeconds; }
    public String getEngineSize() { return engineSize; }

    public String[] toDisplayLines() {
        return new String[] {
            "Vehicle: " + name,
            String.format("Price: R%,.2f", price),
            String.format("0–100: %.1f seconds", zeroToHundredSeconds),
            "Engine: " + engineSize
        };
    }

    @Override
    public String toString() { return name; }
}
