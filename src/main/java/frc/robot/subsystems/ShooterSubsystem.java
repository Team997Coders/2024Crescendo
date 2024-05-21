package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
<<<<<<< HEAD
    public Boolean shooterOn = false;
    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(ShooterConstants.leftShootMotorID,
            MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(ShooterConstants.rightShootMotorID,
            MotorType.kBrushless);
    private RelativeEncoder shooterEncoder; 
=======
    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(Constants.Shooter.LEFT_FLYWHEEL_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(Constants.Shooter.RIGHT_FLYWHELL_MOTOR_ID,MotorType.kBrushless);
    private RelativeEncoder shooterEncoder;
   
>>>>>>> f5e7b85f2f2b1c2a8e2f8781b51b5a275cbabcc4
    public ShooterSubsystem() {
        leftFlywheelNeo.setInverted(ShooterConstants.leftShooterMotorReversed);
        rightFlywheelNeo.setInverted(ShooterConstants.rightShooterMotorReversed);
        rightFlywheelNeo.follow(leftFlywheelNeo);
        shooterEncoder = leftFlywheelNeo.getEncoder();
        shooterEncoder.setPosition(0);
    }
<<<<<<< HEAD
    public void setLeftMotorVoltage(double leftFlywheelVoltage) {
        leftFlywheelNeo.setVoltage(leftFlywheelVoltage);
    }
=======
 

    public void setLeftMotorVoltage(double FlywheelVoltage) {
        leftFlywheelNeo.setVoltage(FlywheelVoltage);
    }



>>>>>>> f5e7b85f2f2b1c2a8e2f8781b51b5a275cbabcc4
    public void setMotorOutput(double output) {
        leftFlywheelNeo.set(output); // between -1.0 and 1.0
    }
<<<<<<< HEAD
    public double getFlywheelPosition() {
        return shooterEncoder.getPosition();
=======
    



    public double getLeftFlywheelEncoderPosition() {
        return leftFlywheelNeo.getEncoder().getPosition();
>>>>>>> f5e7b85f2f2b1c2a8e2f8781b51b5a275cbabcc4
    }
    public double getFlywheelVelocity() {
        return shooterEncoder.getVelocity();
    }
}
