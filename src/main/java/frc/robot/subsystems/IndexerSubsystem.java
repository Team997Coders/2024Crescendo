package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
<<<<<<< HEAD
import frc.robot.Constants;
=======
import frc.robot.Constants.IntakeConstants;
>>>>>>> main



public class IndexerSubsystem extends SubsystemBase {
    private final CANSparkMax intakeNEO = new CANSparkMax(IntakeConstants.intakeMotorId, MotorType.kBrushless);
    private final CANSparkMax feederNEO = new CANSparkMax(IntakeConstants.indexMotorId, MotorType.kBrushless);

    private final RelativeEncoder feederEncoder = feederNEO.getEncoder();
    private final RelativeEncoder intakeEncoder = intakeNEO.getEncoder();

<<<<<<< HEAD
    public final DigitalInput feederSensor = new DigitalInput(Constants.Indexer.FEEDER_SENSOR_CHANNEL);
=======
    public final DigitalInput feederSensor = new DigitalInput(IntakeConstants.noteSensorId);
>>>>>>> main
    public Trigger FeederTrigger = new Trigger(feederSensor::get);
    
    public IndexerSubsystem() {
        intakeNEO.setInverted(IntakeConstants.intakeMotorReversed);
        feederNEO.setInverted(IntakeConstants.indexMotorReversed);
        
        intakeEncoder.setPosition(0);
        feederEncoder.setPosition(0);
<<<<<<< HEAD
       
        
        
=======
>>>>>>> main
    }
    /**
     * Get the current status of the note sensor that sits in the feeder before the note reaches the shooter.
     * @return true if the sensor is blocked, false if not.
     */
    public boolean getSensorStatus() {
        return !feederSensor.get();
    }
<<<<<<< HEAD
    
=======
>>>>>>> main
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
    /**
     * Simple command to run the intake motor as a test
     *
     * @return a command
     */
    public Command runIntakeCommand() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
                setIntakeVoltage(8.0);
            });
    }
}