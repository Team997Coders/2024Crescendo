// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexAndShootCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PIDShoot extends PIDCommand {
  /** Creates a new PIDShoot. */

  private final ShooterSubsystem shooter;

  public PIDShoot(ShooterSubsystem shooter, double speed) {

    super(
        // The controller that the command will use
        new PIDController(0, 0, 0),
        // This should return the measurement
        () -> shooter.getFlywheelVelocity(),
        // This should return the setpoint (can also be a constant)
        () -> speed,
        // This uses the output
        output -> shooter.setLeftMotorVoltage(output));
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    this.shooter = shooter;

    addRequirements(this.shooter);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
