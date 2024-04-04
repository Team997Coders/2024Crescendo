package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  private final CANSparkMax climberMotor =
      new CANSparkMax(Constants.Climber.CLIMBER_MOTOR_ID, MotorType.kBrushless);
  private final RelativeEncoder climberEncoder;

  public ClimberSubsystem() {
    climberEncoder = climberMotor.getEncoder();
    climberEncoder.setPosition(0);
    climberMotor.setIdleMode(IdleMode.kBrake);
  }

  public void setMotorVoltage(double voltage) {
    climberMotor.setVoltage(voltage);
  }

  public double getEncoderRotations() {
    return climberEncoder.getPosition();
  }

  public void setBrakeMode() {
    climberMotor.setIdleMode(IdleMode.kBrake);
  }
}
