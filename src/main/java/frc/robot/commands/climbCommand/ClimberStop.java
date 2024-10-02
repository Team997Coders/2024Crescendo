package frc.robot.commands.climbCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberStop extends Command {
    private ClimberSubsystem climber;

    public ClimberStop(ClimberSubsystem climber) {
        this.climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        climber.setMotorVoltage(0);
    }

    @Override
    public void end(boolean interrupted) {
        climber.setMotorVoltage(0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
