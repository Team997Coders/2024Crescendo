// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.indexAndShootCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends Command {
  /** Creates a new Shoot. */
  private final ShooterSubsystem shooter;
  private final Index indexCommand;
  private final double speed;
  private Timer timer = new Timer();

  public Shoot(ShooterSubsystem shooter, Index indexCommand, double voltage) {
    this.shooter = shooter;
    this.indexCommand = indexCommand;
    this.speed = voltage;

    addRequirements(this.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (timer.get() <= 2) {
      shooter.setLeftMotorVoltage(speed);
    } else {
    indexCommand.execute();
    shooter.setLeftMotorVoltage(speed);
    }
    
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.setLeftMotorVoltage(0);
    indexCommand.end(true);
    timer.stop();
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() >= 4;
  }
}
