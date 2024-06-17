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
    private final DigitalInput leftClimberSensor = new DigitalInput(Constants.ClimberConstants.leftClimberSensorId);
    private final DigitalInput rightClimberSensor = new DigitalInput(Constants.ClimberConstants.rightClimberSensorId);

    public ClimberSubsystem() {
        leftClimberMotor.restoreFactoryDefaults();
        rightClimberMotor.restoreFactoryDefaults();

        leftClimberMotor.setInverted(Constants.ClimberConstants.leftClimberMotorReversed);
        rightClimberMotor.setInverted(Constants.ClimberConstants.rightClimberMotorReversed);
        climberEncoder = leftClimberMotor.getEncoder();
        climberEncoder.setPosition(-1);
        leftClimberMotor.setIdleMode(IdleMode.kBrake);
        rightClimberMotor.setIdleMode(IdleMode.kBrake);
        rightClimberMotor.follow(leftClimberMotor, true);
        
        if (leftClimberSensor.get() | rightClimberSensor.get()){
            climberEncoder.setPosition(-1);
        }
    }

    public void setMotorVoltage(double voltage) {
        leftClimberMotor.setVoltage(voltage);
    }

    public double getEncoderPosition() {
        return climberEncoder.getPosition();
    }

    public boolean getLeftClimberSensor() {
        return leftClimberSensor.get();
    }

    public boolean getRightClimberSensor() {
        return rightClimberSensor.get();
    }

    public double getLeftVelocity() {
        return climberEncoder.getVelocity();
    }

    public boolean isClimberMoving() {
        return (getLeftVelocity() > 0.1 || getLeftVelocity() < -0.1);
    }
}
