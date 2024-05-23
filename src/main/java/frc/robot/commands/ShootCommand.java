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
  private int shooterState = 0;
  /** Creates a new RunIntake. */
  public final IndexerSubsystem m_indexer;
  public final ShooterSubsystem m_shooter;
  private boolean flag_1 = true;
  private boolean flag_2 = true;

  private final int SPINUP_DELAY = 2;
  private final int SHOT_INDEX_DELAY = 2;

  public ShootCommand(IndexerSubsystem indexer, ShooterSubsystem shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_indexer = indexer;
    this.m_shooter = shooter;

    addRequirements(indexer, shooter);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Start ShootCommand");
    timer.start();
    shooterState = 0;
    flag_1 = true;
    flag_2 = true;
    if (m_shooter.isShooterOn()) {
      System.out.println("Shooter already on, skipping spinnup.");
      shooterState = 2;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(shooterState) {
      case 0: // start, spin-up shooter
        System.out.println(shooterState + " : start ShootCommand");
        timer.reset();
        m_shooter.setLeftMotorVoltage(Constants.ShooterConstants.shooterSpeed);
        shooterState = 1;
        break;
      
      case 1: // wait for shooter to reach speed (should be PID)
        if (flag_1) {
          System.out.println(shooterState + " : wait for shooter spin up.");
          flag_1 = false;
        }
        if (timer.get() > SPINUP_DELAY && shooterState == 1){
          shooterState = 2;
        }
        break;

      case 2: // turn-on the indexer to push note into shooter, max speed.
        System.out.println(shooterState + " : engage the feeder to push in the note.");
        m_indexer.setFeederVoltage(12.0);
        shooterState = 3;
        break;

      case 3: // wait for the note to be fired (exit both shooter and indexer)
        if (flag_2) {
          System.out.println(shooterState + " : wait for the shot.");
          flag_2 = false;
        }
        if (timer.get() >= (SPINUP_DELAY + SHOT_INDEX_DELAY) && shooterState == 3) {
          shooterState = 4;
        }
        break;

      case 4: // all done, stop all subsystems and mark command done (state=5)
        System.out.println(shooterState + " : all done.");
        m_indexer.stop();
        m_shooter.stop();
        shooterState = 5;
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shooterState==5;
  }
}
