// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
public final class Autos {
  public static boolean run_state = false;
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(IndexerSubsystem m_indexer, ShooterSubsystem m_shooter) {
    //return new Index(m_indexer,run_state);
    return new IndexAndShoot(2, 2, 3, m_shooter, m_indexer);
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
