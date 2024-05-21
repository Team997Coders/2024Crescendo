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
public class ShootCommand extends SequentialCommandGroup {
  private final ShooterSubsystem m_shooter;
  private final IndexerSubsystem m_indexer;
  private final double m_speed;

  /** Creates a new ShootCommand. */
  public ShootCommand(ShooterSubsystem shooter, IndexerSubsystem indexer, double speed) {
    this.m_shooter = shooter;
    this.m_indexer = indexer;
    this.m_speed = speed;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands( m_shooter.spinupShooterCommand(Constants.ShooterConstants.shooterSpeed));
    addCommands( new WaitCommand(5). until(() -> (m_shooter.getFlywheelVelocity() >= speed)));
    addCommands( m_indexer.runIndexCommand(5).withTimeout(3.0));
  }
}
