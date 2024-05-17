// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootCommand extends ParallelRaceGroup {

  private ShooterSubsystem m_shooter;
  private IndexerSubsystem m_indexer;
  /** Creates a new ShootCommand. */
  public ShootCommand(ShooterSubsystem shooter, IndexerSubsystem indexer) {
    this.m_shooter = shooter;
    this.m_indexer = indexer;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      shooter.runShooterCommand(Constants.ShooterConstants.shooterSpeed));

      addCommands(new SequentialCommandGroup(new WaitCommand(4)), indexer.runIndexCommand(5));

  }
}
