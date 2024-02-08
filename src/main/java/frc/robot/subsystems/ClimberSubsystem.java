package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkBase.IdleMode;

public class ClimberSubsystem extends SubsystemBase {

    private final CANSparkMax climberMotor;
    private final RelativeEncoder climberEncoder;

    public ClimberSubsystem() {
        climberMotor = new CANSparkMax(Constants.Climber.CLIMBER_MOTOR_ID, MotorType.kBrushless);
        climberMotor.restoreFactoryDefaults();
        climberMotor.setIdleMode(IdleMode.kBrake);
        climberMotor.setInverted(Constants.Climber.CLIMBER_MOTOR_IS_INVERTED);
        climberEncoder = climberMotor.getEncoder();
        climberEncoder.setPosition(0);
    }

    public void setMotorVoltage(double voltage) {
        climberMotor.setVoltage(voltage);
    }

    public void setMotorOutput(double ratio) {
        climberMotor.setVoltage(ratio);
    }

    public double getEncoderRotations() {
        return climberEncoder.getPosition();
    }
}
