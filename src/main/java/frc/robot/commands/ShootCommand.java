// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends Command {

  private Timer timer = new Timer();
  /** Creates a new RunIntake. */
  public final IndexerSubsystem m_indexer;
  public final ShooterSubsystem m_shooter;

  private boolean indexOn = false;

  public ShootCommand(IndexerSubsystem indexer, ShooterSubsystem shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_indexer = indexer;
    this.m_shooter = shooter;

    addRequirements(indexer, shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    this.indexOn = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.setLeftMotorVoltage(8);
    if (timer.get() >= 2.0) {
      m_indexer.setFeederVoltage(12);
    } else if (timer.get() >= 2.5) {
      end(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Stop indexNote");
    m_indexer.stop();
    m_shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
