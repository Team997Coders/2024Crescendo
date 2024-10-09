package lib;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

public class APDS9960 {
    private static final int APDS9960_I2C_ADDR = 0x39; // I2C address
    private static final int APDS9960_ENABLE = 0x00; // Enable register
    private static final int APDS9960_ATIME = 0x01; // Ambient light time register
    private static final int APDS9960_WTIME = 0x03; // Wait time register
    private static final int APDS9960_AILTITHE = 0x04; // Ambient light interrupt threshold

    private I2C i2c;

    public APDS9960() {
        i2c = new I2C(I2C.Port.kOnboard, APDS9960_I2C_ADDR);
        initSensor();
    }

    private void initSensor() {
        // Initialize the sensor
        writeByte(APDS9960_ENABLE, 0x03); // Enable the sensor
        writeByte(APDS9960_ATIME, 0xFF); // Set the gain (for example)
        writeByte(APDS9960_WTIME, 0xFF); // Set wait time
    }

    private void writeByte(int register, int value) {
        i2c.write(register, value);
    }

    private int readByte(int register) {
        byte[] buffer = new byte[1];
        i2c.read(register, 1, buffer);
        return buffer[0] & 0xFF;
    }

    public Color readColor() {
        int r = readByte(0x16); // Read red value
        int g = readByte(0x17); // Read green value
        int b = readByte(0x18); // Read blue value

        return new Color(r, g, b);
    }

    public int readProximity() {
        return readByte(0x18); // Proximity data
    }
}
