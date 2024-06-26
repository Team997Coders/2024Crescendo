package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(ShooterConstants.leftShootMotorID,
            MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(ShooterConstants.rightShootMotorID,
            MotorType.kBrushless);
    private RelativeEncoder shooterEncoder;

    private Timer timer = new Timer();

    public ShooterSubsystem() {
        leftFlywheelNeo.setInverted(ShooterConstants.leftShooterMotorReversed);
        rightFlywheelNeo.follow(leftFlywheelNeo, true);
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

    public void stop() {
        leftFlywheelNeo.setVoltage(0);
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
                    timer.start();
                    setLeftMotorVoltage(speed);
                }).until(() -> timer.get() >= 7);
    }

    public Command runStopCommand() {
        return runOnce(
            () -> {
                this.stop();
            }
        );
    }
}
