package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkBase.IdleMode;

public class ClimberSubsystem extends SubsystemBase {
    private final CANSparkMax leftClimberMotor = new CANSparkMax(Constants.ClimberConstants.leftClimberMotorId,
            MotorType.kBrushless);
    private final CANSparkMax rightClimberMotor = new CANSparkMax(Constants.ClimberConstants.rightClimberMotorId,
            MotorType.kBrushless);
    private final RelativeEncoder climberEncoder;
    private final DigitalInput leftClimberLimit = new DigitalInput(Constants.ClimberConstants.leftClimberSensorId);
    private final DigitalInput rightClimberLimit = new DigitalInput(Constants.ClimberConstants.rightClimberSensorId);

    public ClimberSubsystem() {
        climberEncoder = leftClimberMotor.getEncoder();
        climberEncoder.setPosition(0);
        leftClimberMotor.setIdleMode(IdleMode.kBrake);
        rightClimberMotor.follow(leftClimberMotor);
    }

    public void setMotorVoltage(double voltage) {
        leftClimberMotor.setVoltage(voltage);
    }

    public double getEncoderRotations() {
        return climberEncoder.getPosition();
    }

    public Boolean getLeftClimberLimit() {
        return leftClimberLimit.get();
    }

    public void setBrakeMode() {
        leftClimberMotor.setIdleMode(IdleMode.kBrake);
    }
}
