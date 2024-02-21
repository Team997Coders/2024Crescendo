package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BarSubsystem;


public class Bar extends Command {
    
    private BarSubsystem bar;
    private double voltage;
    public Bar(BarSubsystem bar, double voltage){
        this.bar = bar;
        this.voltage = voltage;
    }
    @Override
    public void initialize() {   
    }
    @Override
    public void execute() {
        bar.setBarVoltage(voltage);
    }
    @Override
    public void end(boolean interrupted) {
    }
    @Override 
    public boolean isFinished() {
        return false;
    }
}
