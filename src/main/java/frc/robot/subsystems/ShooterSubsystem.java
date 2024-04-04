package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  public Boolean shooterOn = false;
  private final CANSparkMax leftFlywheelNeo =
      new CANSparkMax(Constants.Shooter.LEFT_FLYWHEEL_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax rightFlywheelNeo =
      new CANSparkMax(Constants.Shooter.RIGHT_FLYWHELL_MOTOR_ID, MotorType.kBrushless);
  private RelativeEncoder shooterEncoder;

  public ShooterSubsystem() {
    leftFlywheelNeo.setInverted(Constants.Shooter.LEFT_FLYWHEEL_MOTOR_IS_INVERTED);
    rightFlywheelNeo.setInverted(Constants.Shooter.RIGHT_FLYWHEEL_MOTOR_IS_INVERTED);
    rightFlywheelNeo.follow(leftFlywheelNeo);
    shooterEncoder = leftFlywheelNeo.getEncoder();
    shooterEncoder.setPosition(0);
  }

  public void setLeftMotorVoltage(double leftFlywheelVoltage) {
    leftFlywheelNeo.setVoltage(leftFlywheelVoltage);
  }

  public void setMotorOutput(double output) {
    leftFlywheelNeo.set(output); // between -1.0 and 1.0
  }

  public double getFlywheelPosition() {
    return shooterEncoder.getPosition();
  }

  public double getFlywheelVelocity() {
    return shooterEncoder.getVelocity();
  }
}
