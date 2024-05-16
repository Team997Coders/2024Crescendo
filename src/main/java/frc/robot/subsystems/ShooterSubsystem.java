package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
    public Boolean shooterOn = false;
    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(ShooterConstants.leftShootMotorID,
            MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(ShooterConstants.rightShootMotorID,
            MotorType.kBrushless);
    private RelativeEncoder shooterEncoder;

    public ShooterSubsystem() {
        leftFlywheelNeo.setInverted(ShooterConstants.leftShooterMotorReversed);
        rightFlywheelNeo.follow(leftFlywheelNeo);
        shooterEncoder = leftFlywheelNeo.getEncoder();
        shooterEncoder.setPosition(0);
        shooterEncoder.setVelocityConversionFactor(1.0);
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

            /**
     * Simple command to run the shooter motor as a test
     *
     * @return a command
     */
    public Command runShooterCommand(double speed) {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
                () -> {
                    setLeftMotorVoltage(speed);
                }).withTimeout(5);
    }
}
