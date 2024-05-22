package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

/**
 * This is the subsystem for the shooter. This should really be a PID subsystem
 * to maintain the shooter wheel speed to a specific velocity. Right now it is
 * dumb and doesn't try to control the speed (open-loop)
 */
public class ShooterSubsystem extends SubsystemBase {
    public Boolean shooterOn = false;
    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(ShooterConstants.leftShootMotorID,
            MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(ShooterConstants.rightShootMotorID,
            MotorType.kBrushless);
    private RelativeEncoder shooterEncoder;

    public ShooterSubsystem() {
        leftFlywheelNeo.setInverted(ShooterConstants.leftShooterMotorReversed);
        rightFlywheelNeo.follow(leftFlywheelNeo, true);

        shooterEncoder = leftFlywheelNeo.getEncoder();
        shooterEncoder.setPosition(0);
        shooterEncoder.setVelocityConversionFactor(1.0);
    }

    /**
     * Set the output of the shooter motor (0V-12V)
     * 
     * @param leftFlywheelVoltage Motor output voltage
     */
    public void setLeftMotorVoltage(double leftFlywheelVoltage) {
        leftFlywheelNeo.setVoltage(leftFlywheelVoltage);
    }

    public void setMotorOutput(double output) {
        leftFlywheelNeo.set(output); // between -1.0 and 1.0
    }

    public double getFlywheelVelocity() {
        return shooterEncoder.getVelocity();
    }

    public boolean isShooterOn() {
        return leftFlywheelNeo.getAppliedOutput() > 1;
    }

    /**
     * Stop the shooter (coast to a stop)
     */
    public void stop() {
        leftFlywheelNeo.setVoltage(0);
    }

    /**
     * Simple command to run the shooter motor as a test
     *
     * @return a command
     */
    public Command StopShooterCommand() {
        return runOnce(
            () -> {
                this.stop();
            });
    }
}
