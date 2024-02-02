package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(Constants.Shooter.LEFT_FLYWHEEL_MOTOR_ID,
            MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(Constants.Shooter.RIGHT_FLYWHELL_MOTOR_ID,
            MotorType.kBrushless);

    private RelativeEncoder shooterEncoder;

    public ShooterSubsystem() {
        leftFlywheelNeo.setInverted(Constants.Shooter.FLYWHEEL_MOTOR_IS_INVERTED);
        shooterEncoder.setPosition(0);
    }

    public boolean isShooterOn() {
        return leftFlywheelNeo.getAppliedOutput() > 0;
    }

    public void setShooterVoltage(double FlywheelVoltage) {
        leftFlywheelNeo.setVoltage(FlywheelVoltage);
        rightFlywheelNeo.setVoltage(FlywheelVoltage);
    }

    public void setMotorOutput(double output) {
        leftFlywheelNeo.set(output); // between -1.0 and 1.0
        rightFlywheelNeo.set(output);
    }

    public double getFlywheelPosition() {
        return shooterEncoder.getPosition();
    }

    public double getFlywheelVelocity() {
        return shooterEncoder.getVelocity();
    }
}
