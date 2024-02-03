package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(Constants.Shooter.LEFT_FLYWHEEL_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(Constants.Shooter.RIGHT_FLYWHELL_MOTOR_ID,MotorType.kBrushless);
    private RelativeEncoder shooterEncoder;
    public ShooterSubsystem() {
        leftFlywheelNeo.setInverted(Constants.Shooter.FLYWHEEL_MOTOR_IS_INVERTED);
        rightFlywheelNeo.setInverted(!Constants.Shooter.FLYWHEEL_MOTOR_IS_INVERTED);
        rightFlywheelNeo.follow(leftFlywheelNeo);
        shooterEncoder = leftFlywheelNeo.getEncoder();
        shooterEncoder.setPosition(0);

    }



   



    public boolean isShooterButtonPressed(){
        return leftFlywheelNeo.getAppliedOutput() > 0;
    }



    public void setLeftMotorVoltage(double leftFlywheelVoltage) {
        leftFlywheelNeo.setVoltage(leftFlywheelVoltage);
    }



    public void setRightMotorVoltage(double rightFlywheelVoltage){
         rightFlywheelNeo.setVoltage(rightFlywheelVoltage);
    }




    public void setMotorOutput(double output) {
        leftFlywheelNeo.set(output); // between -1.0 and 1.0
        rightFlywheelNeo.set(output);
    }




    public double getLeftFlywheelEncoderPosition() {
        return leftFlywheelNeo.getEncoder().getPosition();
    }

    public double getLeftFlywheelEncoderVelocity() {
        return leftFlywheelNeo.getEncoder().getVelocity();
    }

    public double getRightFlywheelEncoderPosition() {
        return rightFlywheelNeo.getEncoder().getPosition();
    }

    public double getRightFlywheelEncoderVelocity() {
        return rightFlywheelNeo.getEncoder().getVelocity();
    }

}
