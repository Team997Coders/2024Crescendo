// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ClimberSubsystem;
//import frc.robot.subsystems.IndexerSubsystem;
//import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
public final class Autos {
  public static boolean run_state = false;

  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ClimberSubsystem climber, double voltage ) {
    return new ClimbCommand(climber, 1);
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
