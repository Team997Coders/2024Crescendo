package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkBase.IdleMode;

public class ClimberSubsystem extends SubsystemBase {

    private final CANSparkMax climberMotor = new CANSparkMax(Constants.Climber.CLIMBER_MOTOR_ID, MotorType.kBrushless);

    public ClimberSubsystem() { 
        climberMotor.setIdleMode(IdleMode.kBrake);
    }

    public void setMotorVoltage(double voltage) {
        climberMotor.setVoltage(voltage);
    }

    public double getEncoderRotations() {
        return climberMotor.getEncoder().getPosition();
    }

    public void setBrakeMode() {
        climberMotor.setIdleMode(IdleMode.kBrake);
    }


}

