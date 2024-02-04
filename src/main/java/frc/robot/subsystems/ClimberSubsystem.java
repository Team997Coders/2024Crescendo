package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
    
    private CANSparkMax climberNEO = new CANSparkMax(Constants.Climber.CLIMBER_MOTOR_ID, MotorType.kBrushless);
    private RelativeEncoder climberEncoder = climberNEO.getEncoder();
    private double setPoint;

    public ClimberSubsystem() {
        climberNEO.setInverted(Constants.Climber.CLIMBER_MOTOR_IS_INVERTED);
        climberNEO.setIdleMode(IdleMode.kBrake);

        climberEncoder.setPosition(0);
    }

    public void setVoltage(double voltage) {
        climberNEO.setVoltage(voltage);


        
    }
    
    public double getEncoderRotations() {
        return climberEncoder.getPosition();
    }

    public double getEncoderVelocity() {
        return climberEncoder.getVelocity();
    }

    public double getMotorCurrent() {
        return climberNEO.getOutputCurrent();
    }
    



}
