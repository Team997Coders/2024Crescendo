package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants;


public class FeederSubsystem extends SubsystemBase {
    private final CANSparkMax feederNEO = new CANSparkMax(Constants.Indexer.FEEDER_MOTOR_ID, MotorType.kBrushless);
    public final DigitalInput feederSensor = new DigitalInput(Constants.Indexer.FEEDER_SENSOR_CHANNEL);
    public Trigger FeederTrigger = new Trigger(feederSensor::get);
    private final RelativeEncoder feederEncoder = feederNEO.getEncoder();
    public FeederSubsystem(){
        feederEncoder.setPosition(0);
        feederNEO.setInverted(Constants.Indexer.FEEDER_MOTOR_IS_INVERTED);
    }
        /**
     * Get the current status of the note sensor that sits in the feeder before the note reaches the shooter.
     * @return true if the sensor is blocked, false if not.
     */
    public boolean getSensorStatus() {
        return !this.feederSensor.get();
    }
    public void setFeederVoltage(double voltage) {
        feederNEO.setVoltage(voltage);
    }

    public double getFeederMotorVoltage(){
        return feederNEO.getEncoder().getVelocity();
    }
    public double getFeederEncoderPosition() {
        return feederEncoder.getPosition();
    }
}
