package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class Intake extends Command {
    private IntakeSubsystem iIntake; 
    private double voltage;
    public Intake(IntakeSubsystem Intake, double voltage){
        this.iIntake = Intake;
        this.voltage = voltage;
    }
    @Override
    public void initialize(){
        iIntake.setIntakeVoltage(voltage);
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
