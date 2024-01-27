package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Shooter implements Subsystem{

    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(Constants.Shooter.LEFT_FLYWHEEL_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(Constants.Shooter.RIGHT_FLYWHELL_MOTOR_ID, MotorType.kBrushless);

    
    private Shooter() {
        leftFlywheelNeo.setInverted(Constants.Shooter.FLYWHEEL_MOTOR_IS_INVERTED);
    }


    public void setMotorVoltage(double voltage) {
        leftFlywheelNeo.setVoltage(voltage);
        rightFlywheelNeo.setVoltage(voltage);

    }

    public void setMotorOutput(double output) {
        leftFlywheelNeo.set(output); //between -1.0 and 1.0
        rightFlywheelNeo.set(output);
    }

    public double getLeftFlywheelEncoderPosition() {
        return leftFlywheelNeo.getEncoder().getPosition();
    }
    public double getLeftFlywheelEncoderVelocity(){
        return leftFlywheelNeo.getEncoder().getVelocity();
    }

    public double getRightFlywheelEncoderPosition() {
        return rightFlywheelNeo.getEncoder().getPosition();
    }

    public double getRightFlywheelEncoderVelocity() {
        return rightFlywheelNeo.getEncoder().getVelocity();
    }
    
    

    @Override 
    public void periodic() {
        //loggers eventually
        SmartDashboard.putNumber("", 0); 
        SmartDashboard.putNumber("", 0);

    }
}
