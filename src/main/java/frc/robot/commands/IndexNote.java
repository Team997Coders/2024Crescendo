// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.IndexerSubsystem;

public class IndexNote extends Command {
  /** Creates a new RunIntake. */
  public final IndexerSubsystem indexer;
  private boolean indexOn = false;

  public IndexNote(IndexerSubsystem indexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.indexer = indexer;
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.indexOn = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(indexer.getSensorStatus() == false) {
      if (!this.indexOn){
        System.out.println("Start indexNote");
      }
      indexer.setIntakeVoltage(Constants.IntakeConstants.intakeSpeed);
      indexer.setFeederVoltage(Constants.IntakeConstants.indexSpeed);
      this.indexOn = true;
    } else if (indexer.getSensorStatus() == true ) {
      this.end(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Stop indexNote");
    indexer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
