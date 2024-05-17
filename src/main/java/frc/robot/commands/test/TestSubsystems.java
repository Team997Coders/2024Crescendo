// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.test;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Climb;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TestSubsystems extends SequentialCommandGroup {
  private final IndexerSubsystem m_indexer;
  private final ShooterSubsystem m_shooter;
  private final ClimberSubsystem m_climber;

  /** Creates a new TestSubsystems. */
  public TestSubsystems(IndexerSubsystem indexer, ShooterSubsystem shooter, ClimberSubsystem climber) {
    this.m_indexer = indexer;
    this.m_shooter = shooter;
    this.m_climber = climber;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(m_indexer.runIntakeCommand(8));
    addCommands(new WaitCommand(2));
    addCommands(m_indexer.runIntakeCommand(-8.0));
    addCommands(new WaitCommand(2));
    addCommands(m_indexer.runIndexCommand(8.0));
    addCommands(new WaitCommand(2));
    addCommands(m_shooter.runShooterCommand(10.0));
  }
}
