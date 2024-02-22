package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkBase.IdleMode;

public class ClimberSubsystem extends SubsystemBase {
    private final CANSparkMax climberNEO = new CANSparkMax(Constants.Climber.CLIMBER_MOTOR_ID, MotorType.kBrushless);
    private final RelativeEncoder climberEncoder;
    public ClimberSubsystem() { 
        climberEncoder = climberNEO.getEncoder(); 
        climberEncoder.setPosition(0);
        climberNEO.setIdleMode(IdleMode.kBrake); 
    }
    public void setClimberVelocity(PIDController feederPID){
        feederPID.setPID(Constants.Climber.kP, Constants.Climber.kI, Constants.Climber.kD);
    }
    public void setClimberVoltage(double voltage) {
        climberNEO.setVoltage(voltage);
    }
    public double getEncoderRotations() {
        return climberEncoder.getPosition();
    }
    public void setBrakeMode() {
        climberNEO.setIdleMode(IdleMode.kBrake);
    }
    
}

