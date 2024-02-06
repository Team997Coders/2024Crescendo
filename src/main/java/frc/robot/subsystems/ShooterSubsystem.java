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
        rightFlywheelNeo.setInverted(!Constants.Shooter.FLYWHEEL_MOTOR_IS_INVERTED);
        rightFlywheelNeo.follow(leftFlywheelNeo);
        shooterEncoder = leftFlywheelNeo.getEncoder();
        shooterEncoder.setPosition(0);
    }

    public boolean isShooterOn() {
        return leftFlywheelNeo.getAppliedOutput() > 0;
    }

    public void setShooterVoltage(double FlywheelVoltage) {
        leftFlywheelNeo.setVoltage(FlywheelVoltage);
    }

    public void setMotorOutput(double output) {
        leftFlywheelNeo.set(output); // between -1.0 and 1.0
    }

    // These two methods will fake out a PID controlled subsystem.
    public void enable() {
        setMotorOutput(1.0);
    }

    public void disable() {
        setMotorOutput(0);
    }

    public boolean atSetpoint() {
        return true;
    }

    // use this simple method to get the velocity characteristics
    // of the motor and flywheel combination (measure velocity) and 
    // determine the motor velocity conversion factor.
    public double getFlywheelVelocity() {
        return shooterEncoder.getVelocity();
    }
}
