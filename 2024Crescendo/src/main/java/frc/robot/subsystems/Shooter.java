package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Shooter implements Subsystem{

    private final CANSparkMax flywheelNEO = new CANSparkMax(Constants.Shooter.FLYWHEEL_MOTOR_ID, MotorType.kBrushless);
    
    private Shooter() {
        flywheelNEO.setInverted(Constants.Shooter.FLYWHEEL_MOTOR_IS_INVERTED);
    }


    public void setMotorVoltage(double voltage) {
        flywheelNEO.setVoltage(voltage);
    }

    public void setMotorOutput(double output) {
        flywheelNEO.set(output); //between -1.0 and 1.0
    }

    public double getFlywheelEncoderPosition() {
        return flywheelNEO.getEncoder().getPosition();
    }
    public double getFlywheelEncoderVelocity(){
        return flywheelNEO.getEncoder().getVelocity();
    }
    
    

    @Override 
    public void periodic() {
        //loggers eventually
        SmartDashboard.putNumber("", 0); 
        SmartDashboard.putNumber("", 0);

    }
}
