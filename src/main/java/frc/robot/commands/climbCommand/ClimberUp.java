package frc.robot.commands.climbCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;
public class ClimberUp extends Command {
    private ClimberSubsystem climber;
    public ClimberUp(ClimberSubsystem climber) {
        this.climber = climber;
    }
    @Override
    public void initialize() {   
    }
    @Override
    public void execute() {
        climber.setMotorVoltage(Constants.ClimberConstants.climberVoltage);
    }
    @Override
    public void end(boolean interrupted) {
        climber.setMotorVoltage(0);
    }
    @Override 
    public boolean isFinished() {
        return false;
    }



}
