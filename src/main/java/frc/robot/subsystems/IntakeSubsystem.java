package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax intakeNEO = new CANSparkMax(Constants.Intake.INTAKE_MOTOR_ID, MotorType.kBrushless);
    private final RelativeEncoder intakeEncoder = intakeNEO.getEncoder();
    public IntakeSubsystem() {
        intakeNEO.setInverted(Constants.Intake.INTAKE_MOTOR_IS_INVERTED);
        intakeEncoder.setPosition(0);
    }
    public void setIntakeVelocity(PIDController intakePID){
        intakePID.setPID(Constants.Intake.kP, Constants.Intake.kI, Constants.Intake.kD);
    }
    /**
     * Set the voltage of the intake motor
     * @param voltage
     */
    public void setIntakeVoltage(double voltage) {
        intakeNEO.setVoltage(voltage);
    }
    /**
     * Set the voltage of the feeder motor
     * @param voltage
     */
    public double getIntakeMotorVoltage(){
        return intakeNEO.getEncoder().getVelocity();
    }
 
    public double getIntakeEncoderPosition() {
        return intakeEncoder.getPosition();
    }
}