[![CI](https://github.com/Team997Coders/2024Crescendo/actions/workflows/main.yml/badge.svg)](https://github.com/Team997Coders/2024Crescendo/actions/workflows/main.yml)

# 2024Crescendo
Code Repository for Team 997 2024 Robot

# Controls (set in RobotContainer)
* // Gyro Reset: POV Up.
* // Drivetrain Lock: POV Down.
* // Intake: A
* // Shoot: B
* // Spinnup Shooter: X
* // Climber
    - UP: Right Bumper
    - Down: Left Bumper

# APDS9600 Patch
This branch has a preliminary version of a device driver for the APDS9960 color/proximitry sensor.

To create an FRC (FIRST Robotics Competition) Java device driver for the APDS9960 sensor, you’ll want to follow these steps. The APDS9960 is a gesture, ambient light, RGB, and proximity sensor that communicates over I2C. Below is a basic outline of how to implement a driver for this sensor in Java.

## ChatGPT output using prompt "create FRC java device driver for APDS9960"

### Step 1: Set Up the Project

Ensure you have a Java-based FRC project set up in WPILib. You can do this using the WPILib VSCode extension.

### Step 2: Create the APDS9960 Class

1. **Create a New Class**:
   Create a new class named `APDS9960` in your project.

2. **Define Constants and Variables**:
   Define the I2C address and any necessary register addresses.

3. **Constructor**:
   Initialize the I2C interface and configure the sensor.

### APDS9960 Class Example

Here’s an example implementation:

```java
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
```

### Step 3: Use the Driver in Your Robot Code

You can now create an instance of `APDS9960` in your robot's `Robot` class and use it to read data from the sensor.

```java
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
    private APDS9960 apds;

    @Override
    public void robotInit() {
        apds = new APDS9960();
    }

    @Override
    public void teleopPeriodic() {
        Color color = apds.readColor();
        System.out.println("Color: R=" + color.red + " G=" + color.green + " B=" + color.blue);

        int proximity = apds.readProximity();
        System.out.println("Proximity: " + proximity);
    }
}
```

### Step 4: Test Your Sensor

1. Deploy your code to the robot.
2. Connect the APDS9960 to the appropriate I2C bus on your robot.
3. Check the console output to verify that you're receiving data from the sensor.

### Notes

- Ensure you handle exceptions and errors, especially related to I2C communication.
- Fine-tune the initialization and configuration of the sensor based on your specific application needs.
- Consult the APDS9960 datasheet for additional configuration options and register definitions.

This should give you a good starting point for integrating the APDS9960 with your FRC robot!
