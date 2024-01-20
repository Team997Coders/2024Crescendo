package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Indexer implements Subsystem {

    private final CANSparkMax intakeNEO = new CANSparkMax(Constants.Indexer.INTAKE_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax feederNEO = new CANSparkMax(Constants.Indexer.FEEDER_MOTOR_ID, MotorType.kBrushless);

    private final DigitalInput feederSensor /* ? */ = new DigitalInput(Constants.Indexer.FEEDER_SENSOR_CHANNEL);

    private Indexer() {
        intakeNEO.setInverted(Constants.Indexer.INTAKE_MOTOR_IS_INVERTED);
        feederNEO.setInverted(Constants.Indexer.FEEDER_MOTOR_IS_INVERTED);
    }

    public boolean getSensorStatus() {
        return feederSensor.get();
    }


    public void setIntakeVoltage(double voltage) {
        intakeNEO.setVoltage(voltage);
    }

    public void setFeederVoltage(double voltage) {
        feederNEO.setVoltage(voltage);
    }


    public void setIntakeOutput(double output) {
        intakeNEO.set(output); //between -1.0 and 1
    }

    public void setFeederOutput (double output) {
        feederNEO.set(output);
    } 

    
    public double getIntakeEncoderPosition() {
        return intakeNEO.getEncoder().getPosition();
    }

    public double getFeederEncoderPosition() {
        return feederNEO.getEncoder().getPosition();
    }






    @Override
    public void periodic(){
        //loggers
        SmartDashboard.putBoolean("sensor status", getSensorStatus());
    }
}
