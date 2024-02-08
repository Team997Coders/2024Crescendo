package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IndexerSubsystem extends SubsystemBase {
    private final CANSparkMax intakeNEO;
    private final CANSparkMax feederNEO;

    private final RelativeEncoder feederEncoder;
    private final RelativeEncoder intakeEncoder;

    public final DigitalInput feederSensor;

    public IndexerSubsystem() {
        intakeNEO = new CANSparkMax(Constants.Indexer.INTAKE_MOTOR_ID, MotorType.kBrushless);
        feederNEO = new CANSparkMax(Constants.Indexer.FEEDER_MOTOR_ID, MotorType.kBrushless);

        feederEncoder = feederNEO.getEncoder();
        intakeEncoder = intakeNEO.getEncoder();
        feederSensor = new DigitalInput(Constants.Indexer.FEEDER_SENSOR_CHANNEL);

        intakeNEO.setInverted(Constants.Indexer.INTAKE_MOTOR_IS_INVERTED);
        feederNEO.setInverted(Constants.Indexer.FEEDER_MOTOR_IS_INVERTED);

        intakeEncoder.setPosition(0);
        feederEncoder.setPosition(0);
    }

    /**
     * Get the current status of the note sensor that sits in the feeder before the
     * note reaches the shooter.
     * 
     * @return true if the sensor is blocked, false if not.
     */
    public boolean getSensorStatus() {
        return !feederSensor.get();
    }

    public boolean isFilled() {
        return getSensorStatus();
    }

    /**
     * Set the voltage of the intake motor
     * 
     * @param voltage
     */
    public void setIntakeVoltage(double voltage) {
        intakeNEO.setVoltage(voltage);
    }

    /**
     * Set the voltage of the feeder motor
     * 
     * @param voltage
     */
    public void setFeederVoltage(double voltage) {
        feederNEO.setVoltage(voltage);
    }

    public double getIntakeMotorVelocity() {
        return intakeNEO.getEncoder().getVelocity();
    }

    public double getFeederMotorVelocity() {
        return feederNEO.getEncoder().getVelocity();
    }

    public double getIntakeEncoderPosition() {
        return intakeEncoder.getPosition();
    }

    public double getFeederEncoderPosition() {
        return feederEncoder.getPosition();
    }

    public void startFeeder() {
        setFeederVoltage(0.4);
    }

    public void stopFeeder() {
        setFeederVoltage(0);
    }

    public void resetIntakePosition() {
        feederEncoder.setPosition(0);
    }
}
