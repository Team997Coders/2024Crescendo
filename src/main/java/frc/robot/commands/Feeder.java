package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;


public class Feeder extends Command {
    private FeederSubsystem feeder;
    private double voltage;
    public Feeder(FeederSubsystem feeder, double voltage){
        this.feeder = feeder;
        this.voltage = voltage;
    }
    @Override
    public void initialize(){
        feeder.setFeederVoltage(voltage);
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
