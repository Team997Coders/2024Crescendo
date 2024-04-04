package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimberSubsystem;

public class Climb extends Command {
  private ClimberSubsystem climber;
  private double voltage;

  public Climb(ClimberSubsystem climber, double voltage) {
    this.climber = climber;
    this.voltage = voltage;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    climber.setMotorVoltage(voltage);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
