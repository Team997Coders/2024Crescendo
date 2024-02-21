package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;


public class Feeder extends Command {
    private FeederSubsystem Feeder;
    private double voltage;
    public Feeder(FeederSubsystem Feeder, double voltage){
        this.Feeder = Feeder;
        this.voltage = voltage;
    }
    @Override
    public void initialize(){
        Feeder.setFeederVoltage(voltage);
    }
    @Override
    public void execute(){
       
    }   
    @Override
    public void end(boolean interrupted){
    }
    
    @Override 
    public boolean isFinished(){
        return false;
    }
}
