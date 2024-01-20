package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class Intake implements Subsystem {

    private final CANSparkMax indexerNEO = new CANSparkMax(Constants.Intake.INTAKE_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax feederNEO = new CANSparkMax(Constants.Intake.FEEDER_MOTOR_ID, MotorType.kBrushless);
}
