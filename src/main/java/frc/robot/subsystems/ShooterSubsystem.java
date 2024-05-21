package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
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
        rightFlywheelNeo.setInverted(ShooterConstants.rightShooterMotorReversed);
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
