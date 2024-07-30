// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootSequence extends SequentialCommandGroup {
  /** Creates a new ShootSeq. */
  private ShooterSubsystem m_shooter;
  private IndexerSubsystem m_indexer;

  public ShootSequence() {
    // Start spinning up the shooter wheels...
    addCommands(Commands.runOnce(m_shooter::enable, m_shooter));
    // Wait until the shooter is at speed...
    addCommands(Commands.waitUntil(m_shooter::atSetpoint));
    // Before running the feeder to fire the note.
    addCommands(Commands.runOnce(() -> { m_indexer.setFeederVoltage(12.0); }));
    // Shoot for the specified time
    Commands.waitSeconds(Constants.AutoConstants.kAutoShootTimeSeconds);
    // Add a timeout (will end the command if, for instance, the shooter
    // never gets up to speed)
    withTimeout(Constants.AutoConstants.kAutoTimeoutSeconds)
    // When the command ends, turn off the shooter and the feeder
    .andThen(
      Commands.runOnce(
        () -> {
          m_shooter.disable();
          m_indexer.stopFeeder();
    }));
  }
}
