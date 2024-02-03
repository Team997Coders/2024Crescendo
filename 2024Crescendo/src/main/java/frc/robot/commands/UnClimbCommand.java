package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimberSubsystem;

public class UnClimbCommand extends Command{
     private final ClimberSubsystem climber;
    private double voltage;

    
    public UnClimbCommand(ClimberSubsystem climber, double voltage) {
        this.climber = climber;
        this.voltage = voltage;
    }
    
    @Override
    public void initialize() {
        climber.setBrakeMode();
    }
    
    @Override
    public void execute() {
        climber.setMotorVoltage(-voltage);
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    public boolean isFinished(){
        return false;
    }
}
