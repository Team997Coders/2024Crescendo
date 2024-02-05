package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
<<<<<<< HEAD
    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(Constants.Shooter.LEFT_FLYWHEEL_MOTOR_ID,
            MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(Constants.Shooter.RIGHT_FLYWHELL_MOTOR_ID,
            MotorType.kBrushless);

    private RelativeEncoder shooterEncoder;

=======
    private final CANSparkMax leftFlywheelNeo = new CANSparkMax(Constants.Shooter.LEFT_FLYWHEEL_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax rightFlywheelNeo = new CANSparkMax(Constants.Shooter.RIGHT_FLYWHELL_MOTOR_ID,MotorType.kBrushless);
    private RelativeEncoder shooterEncoder;
    private boolean isShooterButtonPressed;
>>>>>>> climber
    public ShooterSubsystem() {
        leftFlywheelNeo.setInverted(Constants.Shooter.FLYWHEEL_MOTOR_IS_INVERTED);
        rightFlywheelNeo.setInverted(!Constants.Shooter.FLYWHEEL_MOTOR_IS_INVERTED);
        rightFlywheelNeo.follow(leftFlywheelNeo);
        shooterEncoder = leftFlywheelNeo.getEncoder();
        shooterEncoder.setPosition(0);
<<<<<<< HEAD
    }

    public boolean isShooterOn() {
        return leftFlywheelNeo.getAppliedOutput() > 0;
    }

    public void setShooterVoltage(double FlywheelVoltage) {
        leftFlywheelNeo.setVoltage(FlywheelVoltage);
=======

    }



   



    public boolean isShooterButtonPressed(){
        return isShooterButtonPressed;
    }



    public void setLeftMotorVoltage(double leftFlywheelVoltage) {
        leftFlywheelNeo.setVoltage(leftFlywheelVoltage);
    }



    public void setRightMotorVoltage(double rightFlywheelVoltage){
         rightFlywheelNeo.setVoltage(rightFlywheelVoltage);
>>>>>>> climber
    }




    public void setMotorOutput(double output) {
        leftFlywheelNeo.set(output); // between -1.0 and 1.0
    }

<<<<<<< HEAD
    public double getFlywheelPosition() {
        return shooterEncoder.getPosition();
=======



    public double getLeftFlywheelEncoderPosition() {
        return leftFlywheelNeo.getEncoder().getPosition();
>>>>>>> climber
    }

    public double getFlywheelVelocity() {
        return shooterEncoder.getVelocity();
    }
}
