package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class IndexerSubsystem extends SubsystemBase {
    private final CANSparkMax intakeNEO = new CANSparkMax(Constants.Indexer.INTAKE_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax feederNEO = new CANSparkMax(Constants.Indexer.FEEDER_MOTOR_ID, MotorType.kBrushless);

    private final RelativeEncoder feederEncoder = feederNEO.getEncoder();
    private final RelativeEncoder intakeEncoder = intakeNEO.getEncoder();

    public static final DigitalInput feederSensor = new DigitalInput(Constants.Indexer.FEEDER_SENSOR_CHANNEL);

    public IndexerSubsystem() {
        intakeNEO.setInverted(Constants.Indexer.INTAKE_MOTOR_IS_INVERTED);
        feederNEO.setInverted(Constants.Indexer.FEEDER_MOTOR_IS_INVERTED);
        
        intakeEncoder.setPosition(0);
        feederEncoder.setPosition(0);    
    }

    /**
     * Get the current status of the note sensor that sits in the feeder before the note reaches the shooter.
     * @return true if the sensor is blocked, false if not.
     */
    public boolean getSensorStatus() {
        return !feederSensor.get();
    }
    public boolean isFilled(){
        return getSensorStatus();
    }
    /**
     * Set the voltage of the intake motor
     * @param voltage
     */
    public void setIntakeVoltage(double voltage) {
        intakeNEO.setVoltage(voltage);
    }

    /**
     * Set the voltage of the feeder motor
     * @param voltage
     */
    public void setFeederVoltage(double voltage) {
        feederNEO.setVoltage(voltage);
    }

    public double getIntakeMotorVoltage(){
        return intakeNEO.getEncoder().getVelocity();
    }

    public double getFeederMotorVoltage(){
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
