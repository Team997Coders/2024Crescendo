package frc.robot.commands.indexAndShootCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;

public class Index extends Command {

    private final IndexerSubsystem indexer;
    private final double speed;


    public Index(IndexerSubsystem indexer, double voltage) {
        this.indexer = indexer;
        this.speed = voltage;
    }
    
      @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    indexer.setFeederVoltage(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexer.setFeederVoltage(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
