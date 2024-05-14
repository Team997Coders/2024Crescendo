// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.Drivebase;

public class TriPIDDrive extends Command {
  private Drivebase drivebase;
  private PIDController xPID;
  private PIDController yPID;
  private PIDController rPID;
  DoubleSupplier xPose;
  DoubleSupplier yPose;
  DoubleSupplier angle;
  double xTarget;
  double yTarget;
  double rTarget;
  double xOutput;
  double yOutput;
  double rOutput;

  /** Creates a new TriPIDDrive. */
  public TriPIDDrive(Drivebase drivebase, double xTarget, double yTarget,
      double rTarget,
      DoubleSupplier xPose, DoubleSupplier yPose, DoubleSupplier angle) {
    this.drivebase = drivebase;
    this.xTarget = xTarget;
    this.yTarget = yTarget;
    this.rTarget = rTarget;
    this.xPose = xPose;
    this.yPose = yPose;
    this.angle = angle;
    this.xPID = new PIDController(AutoConstants.XPID.p, AutoConstants.XPID.i, AutoConstants.XPID.d);
    this.yPID = new PIDController(AutoConstants.YPID.p, AutoConstants.YPID.i, AutoConstants.YPID.d);
    this.rPID = new PIDController(AutoConstants.RPID.p, AutoConstants.RPID.i, AutoConstants.RPID.d);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rPID.setTolerance(0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    xOutput = xPID.calculate(xPose.getAsDouble(), xTarget);
    yOutput = yPID.calculate(yPose.getAsDouble(), yTarget);
    rOutput = rPID.calculate(angle.getAsDouble(), rTarget);

    SmartDashboard.putNumber("X PID Output", xOutput);
    SmartDashboard.putNumber("Y PID Output", yOutput);
    SmartDashboard.putNumber("Angle PID Output", rOutput);

    drivebase.fieldOrientedDrive(
        xOutput,
        yOutput,
        // Math.copySign(Math.min(Math.abs(xOutput), 0.3), xOutput),
        // Math.copySign(Math.min(Math.abs(yOutput), 1), yOutput),
        -Math.copySign(Math.min(Math.abs(rOutput), 0.04), rOutput));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivebase.robotOrientedDrive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return xPID.atSetpoint() && yPID.atSetpoint() && rPID.atSetpoint();
  }
}
