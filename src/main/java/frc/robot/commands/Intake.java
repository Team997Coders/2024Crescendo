package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends Command {
    private IntakeSubsystem intake; 
    private double voltage;
    public Intake(IntakeSubsystem intake, double voltage){
        this.intake = intake;
        this.voltage = voltage;
    }
    @Override
    public void initialize(){
        intake.setIntakeVoltage(voltage);
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
